package Login;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import enity.Usuario;
import enity.UsuarioBean;
import util.ValidatorUtil;

@ManagedBean (name = "loginBean")
public class LoginMBean {
	Usuario usu = new Usuario();
	private Usuario usuario;
	private UsuarioBean userBean;

	boolean validarLogin() throws IOException {
		if (usuario == null
				|| (ValidatorUtil.isEmpty(usuario.getEmail()) && ValidatorUtil.isEmpty(usuario.getSenha()))) {
			addMsgError("Usu�rio/senha n�o informados.");
			return false;
		}
		if (ValidatorUtil.isEmpty(usuario.getEmail())) {
			addMsgError("Usu�rio: campo obrigat�rio n�o informado.");
			return false;
		}

		if (ValidatorUtil.isEmpty(usuario.getSenha())) {
			addMsgError("Senha: campo obrigat�rio n�o informado.");
			return false;
		}
		logar() ;
		return true;

	}

	// Logar no sistema
	public String logar() throws IOException {
		this.usuario = userBean.getUsuario(this.usuario); // usar o
																// usuario na
																// varivel de
																// sess�o
		if (this.usuario != null) { // verificar se houve algum retorno da
									// pesquisa no banco
			return verificarTipoUsuario(usuario);
		} else if (this.usuario == null) {
			FacesMessage message = new FacesMessage("Login inv�lido");			
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Login inv�lido", null));
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");			
		} else {
			FacesMessage message = new FacesMessage("Senha inv�lido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha inv�lida", null));
		}
		this.usuario = new Usuario();
		return "login.xhtml";
	}

	private String verificarTipoUsuario(Usuario user) {
		if (user.getTipo().equals("padrao")) {
			return "usuario.xhtml?faces-redirect=true";
		} else {
			return "login.xhtml?faces-redirect=true";
		}
	}

	private void addMsgError(String string) {
		// TODO Auto-generated method stub

	}
}
