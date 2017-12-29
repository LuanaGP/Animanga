package anime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Anime {
	@Id
	@GeneratedValue
private Long animeid;
	@Column(nullable = false)
private String nome;
	@Column(nullable = false)
private String genero;
	@Column(nullable = false)
private String diretor;
public Long getAnimeid() {
	return animeid;
}
public void setAnimeid(Long animeid) {
	this.animeid = animeid;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getGenero() {
	return genero;
}
public void setGenero(String genero) {
	this.genero = genero;
}
public String getDiretor() {
	return diretor;
}
public void setDiretor(String diretor) {
	this.diretor = diretor;
}




}