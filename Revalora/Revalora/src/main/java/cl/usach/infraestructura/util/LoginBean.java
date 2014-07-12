package cl.usach.infraestructura.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import cl.usach.diinf.revalora.usuario.entities.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String password;
	private String message, uname;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String loginProject() {
		Usuario result = Usuario.getUser(uname, password);
		if (result != null) {
			HttpSession session = Util.getSession();
			session.setAttribute("user", result);
			return "index.xhtml";
		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Login Invalido!", "Por favor intente nuevamente!"));
			return "login.xhtml";
		}
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "login.xhtml";
	}
}