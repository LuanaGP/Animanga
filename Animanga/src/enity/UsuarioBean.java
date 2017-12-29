package enity;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;

import enity.EntityManagerUtil;
import enity.Usuario;
import util.CriptografiaUtils;

@ManagedBean
public class UsuarioBean {

	private static final Logger log = Logger.getLogger(UsuarioBean.class);

	private EntityManager entityManager;

	private Usuario usuario = new Usuario();

	private Long id;

	private List<Usuario> list;

	String senha = "";
// Cadastrar Usuario //
	public String persist() {
		log.info("Cadastrando usuário: " + usuario.getNome());
// Criptografia da senha //
		try {
			senha = CriptografiaUtils.md5(usuario.getSenha());

			EntityTransaction transacao = getEntityManager().getTransaction();

			transacao.begin();
			usuario.setTipo("padrao");
			usuario.setSenha(senha);
			getEntityManager().persist(usuario);
			transacao.commit();

			newInstance();
			list = null;

			return "sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "falhou";
		}
	}
// Atualizar Usuario //
	public String update() {
		log.info("Alterando usuario: " + usuario.getNome());
		try {
			EntityTransaction transacao = getEntityManager().getTransaction();
			usuario.setSenha(senha);
			transacao.begin();
			getEntityManager().merge(usuario);
			transacao.commit();

			newInstance();

			return "sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "falhou";
		}
	}
// Remover Usuario //
	public String remove() {
		log.info("Removendo usuario: " + usuario.getNome());
		try {
			EntityTransaction transacao = getEntityManager().getTransaction();

			transacao.begin();
			getEntityManager().remove(usuario);
			transacao.commit();

			newInstance();
			list = null;

			return "sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "falhou";
		}
	}

	@SuppressWarnings("unchecked")
	public Usuario getUsuario(Usuario usuario) {
		Usuario user = new Usuario();
		if (list == null) {
			log.info("Buscando lista de usuarios");
			String senha = usuario.getSenha();
			String nome = usuario.getNome();
			list = getEntityManager().createQuery("FROM Usuario WHERE nome = "+nome+" AND senha ="+senha).getResultList();
			for(Usuario u:list){
				user = u;
			}
		}

		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getList() {
		if (list == null) {
			log.info("Buscando lista de usuarios");
			list = getEntityManager().createQuery("FROM Usuario").getResultList();
		}

		return list;
	}

	public EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = EntityManagerUtil.getInstance().createEntityManager();
		}

		return entityManager;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setId(Long id) {
		this.id = id;
		if (id != null) {
			usuario = getEntityManager().find(Usuario.class, id);
		}
	}

	public Long getId() {
		return id;
	}

	public void newInstance() {
		usuario = new Usuario();
	}
}