package cl.usach.infraestructura.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.usach.diinf.revalora.usuario.entities.Usuario;

public class Util {

	 public static HttpSession getSession() {
	        return (HttpSession)
	          FacesContext.
	          getCurrentInstance().
	          getExternalContext().
	          getSession(false);
	      }
	       
	      public static HttpServletRequest getRequest() {
	       return (HttpServletRequest) FacesContext.
	          getCurrentInstance().
	          getExternalContext().getRequest();
	      }
	 
	      public static Usuario getUserName()
	      {
	        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	        return  (Usuario)session.getAttribute("user");
	      }
	       
	      public static String getUserId()
	      {
	        HttpSession session = getSession();
	        if ( session != null )
	            return (String) session.getAttribute("userid");
	        else
	            return null;
	      }
}
