package perguntas;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;


import enity.EntityManagerUtil;
import perguntas.Perguntas;
@ManagedBean
public class PerguntasBean {
 
  private static final Logger log = Logger.getLogger(PerguntasBean.class);
 
  private EntityManager entityManager;
 
  private Perguntas perguntas = new Perguntas();
 
  private Long id;
 
  private List<Perguntas> list;
 // Cadastrar Pergunta //
  public String persist() {
    log.info("Cadastrando pergunta: " + perguntas.getNome());
    try {
      EntityTransaction transacao = getEntityManager().getTransaction();
 
      transacao.begin();
      getEntityManager().persist(perguntas);
      transacao.commit();
 
      newInstance();
      list = null;
 
      return "sucesso";
    } catch (Exception e) {
      e.printStackTrace();
      return "falhou";
    }
  }
 // Atualizar Pergunta //
  public String update() {
    log.info("Alterando pergunta: " + perguntas.getNome());
    try {
      EntityTransaction transacao = getEntityManager().getTransaction();
 
      transacao.begin();
      getEntityManager().merge(perguntas);
      transacao.commit();
 
      newInstance();
 
      return "sucesso";
    } catch (Exception e) {
      e.printStackTrace();
      return "falhou";
    }
  }
 // Remover Pergunta //
  public String remove() {
    log.info("Removendo pergunta: " + perguntas.getNome());
    try {
      EntityTransaction transacao = getEntityManager().getTransaction();
 
      transacao.begin();
      getEntityManager().remove(perguntas);
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
  public List getList() {
    if (list == null) {
      log.info("Buscando lista de perguntas");
      list = getEntityManager().createQuery("FROM Perguntas")
        .getResultList();
    }
 
    return list;
  }
 
  public EntityManager getEntityManager() {
    if (entityManager == null) {
      entityManager = EntityManagerUtil.getInstance().createEntityManager();
    }
 
    return entityManager;
  }
 
  public void setPerguntas(Perguntas perguntas) {
    this.perguntas = perguntas;
  }
 
  public Perguntas getPerguntas() {
    return perguntas;
  }
 
  public void setId(Long id) {
    this.id = id;
    if (id != null) {
      perguntas = getEntityManager().find(Perguntas.class, id);
    }
  }
 
  public Long getId() {
    return id;
  }
 
  public void newInstance() {
    perguntas = new Perguntas();
  }
}