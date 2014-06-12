package cl.usach.diinf.revalora.correo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.apache.log4j.Logger;

import cl.usach.diinf.revalora.correo.to.CorreoTO;
import cl.usach.infraestructura.util.TablaValores;
/**
* 
* @author	Sergio Reyes
* @updated	Alvaro Maldonado
* @version  1.0 
*/
@ManagedBean
@RequestScoped
public class RecibidosBean implements Serializable {

	/**
	 * Variable que representa el numero de versi—n
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	public static Logger logger = Logger.getLogger(RecibidosBean.class);

	private static String tablaValores = "correo.parametros";

	private static Session mailSession;
	
	private static final String USER = TablaValores.getValor(tablaValores, "correo", "user");
	
	private static final String PASSWORD = TablaValores.getValor(tablaValores, "correo", "pass");
	
	private List<CorreoTO> correoSmall;

	private String statusMessage = "";

	private CorreoTO selectedCorreo;
	
	/**
     * Inyeccion del BandejaCorreoModelMB.
     */
    @Inject
    private BandejaCorreoModelMB bandejaCorreoModelMB;
    
    
    /**
	 * Metodo encargado de la obtencion de correos recibidos
	 * @since 1.0
	 */ 
	@PostConstruct
	public void recibidosModelMB() {

		logger.debug("[RecibidosBean] recibidos - ");

		
		try {
			
			correoSmall = bandejaCorreoModelMB.getCorreoRecibidos();
			
			logger.debug("[RecibidosBean] correoSmall:" + correoSmall.size());

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<CorreoTO> getCorreoSmall() {
		return this.correoSmall;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public CorreoTO getSelectedCorreo() {
		System.out.println("Contenido Seleccionado: " + selectedCorreo);
		return selectedCorreo;
	}

	public void setSelectedCorreo(CorreoTO selectedCorreo) {
		System.out.println("Set Contenido Seleccionado: " + selectedCorreo);
		this.selectedCorreo = selectedCorreo;
	}
}
