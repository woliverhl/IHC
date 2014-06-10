package cl.usach.diinf.revalora.correo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

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
public class BorradoresBeans implements Serializable{

	/**
	 * Numero de version
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	static Logger logger = Logger.getLogger(BorradoresBeans.class);
	/**
	 * Tabla de valores de donde se rescatan parametros de conexion 
	 * @since 1.0
	 */
	private static String tablaValores = "correo.parametros";
	private List<CorreoTO> correoSmall;
    private String statusMessage = "";
    private static Session mailSession;
//    private static final String USER = "ihcgrupo3";     // Must be valid user in d.umn.edu domain, e.g. "smit0012"
//    private static final String PASSWORD = "grupo32014"; // Must be valid password for smit0012
    private static final String USER = TablaValores.getValor(tablaValores, "correo", "user");
	private static final String PASSWORD = TablaValores.getValor(tablaValores, "correo", "pass");
    private CorreoTO selectedCorreo;
	
    /**
     * Inyeccion del BandejaCorreoModelMB.
     */
    @Inject
    private BandejaCorreoModelMB bandejaCorreoModelMB;
    
	public List<CorreoTO> getCorreoSmall(){
		return this.correoSmall;
	}
	
    public String getStatusMessage() {
		return statusMessage;
	} 


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	 

	public CorreoTO getSelectedCorreo() {
		return selectedCorreo;
	}


	public void setSelectedCorreo(CorreoTO selectedCorreo) {
		this.selectedCorreo = selectedCorreo;
	} 




	 
	/**
	 * Metodo encargado de la obtencion de correos en borrador
	 * @since 1.0
	 */ 	  
@PostConstruct 
	public void  borradoresBeans() {
		

	logger.debug("[borradoresBeans] borradores - ");

	
	try {
		
		correoSmall = bandejaCorreoModelMB.getCorreosBorradores();
		
		logger.debug("[borradoresBeans] correoSmall:" + correoSmall.size());

		

	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
}
