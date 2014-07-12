package cl.usach.diinf.revalora.login;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import cl.usach.diinf.revalora.usuario.entities.Usuario;
import cl.usach.infraestructura.util.Util;

/**
 * MB para obtener datos de usuario
 *
 * @author Oliver Hidalgo
 */
@Named
@RequestScoped
public class HeaderMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private Usuario usuario;

    @PostConstruct
    public void init() throws IOException{
    	this.setUsuario(Util.getUserName());
    	if (this.getUsuario() == null){
    		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    		 ec.redirect("/Revalora/login.jsf");
    	}
    }
   	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
