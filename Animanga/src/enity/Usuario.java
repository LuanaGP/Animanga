package enity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
	private Long usuarioid;

	private String nome;
	private String endereço;
	private String email;
	private String tipo;
	private String senha;

	public Usuario() {
	}

	public Long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(Long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario(Long usuarioid, String nome, String endereço, String email, String tipo, String senha) {
		super();
		this.usuarioid = usuarioid;
		this.nome = nome;
		this.endereço = endereço;
		this.email = email;
		this.tipo = tipo;
		this.senha = senha;
	}

}