package perguntas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Perguntas {
	@Id
	@GeneratedValue
private Long perguntasid;
	@Column(nullable = false)
private String nome;
	@Column(nullable = false)
private String assunto;
	@Column(nullable = false)
private String perguntas;


public Long getPerguntasid() {
return perguntasid;
}
public void setPerguntasid(Long perguntasid) {
this.perguntasid = perguntasid;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getAssunto() {
	return assunto;
}
public void setAssunto(String assunto) {
	this.assunto = assunto;
}
public String getPerguntas() {
	return perguntas;
}
public void setPerguntas(String perguntas) {
	this.perguntas = perguntas;
}


}