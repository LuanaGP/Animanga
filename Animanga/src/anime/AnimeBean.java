package anime;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;


import enity.EntityManagerUtil;
import anime.Anime;
@ManagedBean
public class AnimeBean {
 
  private static final Logger log = Logger.getLogger(AnimeBean.class);
 
  private EntityManager entityManager;
 
  private Anime anime = new Anime();
 
  private Long id;
 
  private List<Anime> list;
 // Cadastrar Anime //
  public String persist() {
    log.info("Cadastrando anime: " + anime.getNome());
    try {
      EntityTransaction transacao = getEntityManager().getTransaction();
 
      transacao.begin();
      getEntityManager().persist(anime);
      transacao.commit();
 
      newInstance();
      list = null;
 
      return "sucesso";
    } catch (Exception e) {
      e.printStackTrace();
      return "falhou";
    }
  }
 // Atualizar Anime //
  public String update() {
    log.info("Alterando anime: " + anime.getNome());
    try {
      EntityTransaction transacao = getEntityManager().getTransaction();
 
      transacao.begin();
      getEntityManager().merge(anime);
      transacao.commit();
 
      newInstance();
 
      return "sucesso";
    } catch (Exception e) {
      e.printStackTrace();
      return "falhou";
    }
  }
// Remover Anime //
  public String remove() {
    log.info("Removendo anime: " + anime.getNome());
    try {
      EntityTransaction transacao = getEntityManager().getTransaction();
 
      transacao.begin();
      getEntityManager().remove(anime);
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
      log.info("Buscando lista de anime");
      list = getEntityManager().createQuery("FROM Anime")
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
 
  public void setAnime(Anime anime) {
    this.anime = anime;
  }
 
  public Anime getAnime() {
    return anime;
  }
 
  public void setId(Long id) {
    this.id = id;
    if (id != null) {
      anime = getEntityManager().find(Anime.class, id);
    }
  }
 
  public Long getId() {
    return id;
  }
 
  public void newInstance() {
    anime = new Anime();
  }
}