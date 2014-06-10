package cl.usach.diinf.revalora.correo.mb;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import cl.usach.diinf.revalora.correo.MailServiceBean;
/**
* 
* @author	Sergio Reyes
* @updated	Alvaro Maldonado
* @version  1.0 
*/
@Named
@RequestScoped
public class EnvioBean {
	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	static Logger logger = Logger.getLogger(EnvioBean.class);
	

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	
	public UploadedFile getFile() {
		return file;
	}

	
	public void setFile(UploadedFile file) {
		this.file = file;
	} 

	
	/**
     * Metodo que indica que se descarta un correo en borrador
     * @return
     * @since 1.0
     */
    public String volver(){
    	statusMessage = "Mensaje descartado";
    	return "index";
    }
	
    /**
     * Metodo que guarda un correo en borrador
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public String saveToDraft() throws MessagingException, IOException{
    	statusMessage = "Mensaje Guardado como Borrador";
    	MailServiceBean.enviarDraft(recipient, subject, message, archivo, cc, bcc);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo Guardado en Borradores"));
    	return "enviar";
    }
    
    /**
	 * Metodo que envia un correo
	 * @return
	 * @throws IOException
	 */
	public String send() throws IOException {
        statusMessage = "Mensaje Enviado";
        
        try {
        	
        	logger.debug("[EnvioBean] Antes de llamar a mailService " + recipient);
        	logger.debug("[EnvioBean] Antes de llamar a mailService " + subject);
        	logger.debug("[EnvioBean] Antes de llamar a mailService " + message);
        	logger.debug("[EnvioBean] Antes de llamar a mailService " + archivo);
        	logger.debug("[EnvioBean] Antes de llamar a mailService " + cc);
        	logger.debug("[EnvioBean] Antes de llamar a mailService " + bcc);
        	
        	
        	archivo = "//home//ihc//workspace//archivo.txt";
        	
        	
            MailServiceBean.sendMessage(recipient, subject, message, archivo, cc, bcc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo Enviado"));
        } 
        catch(MessagingException ex) {  
            statusMessage = ex.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se pudo enviar el correo"));
        }
        
        return "enviar";  // redisplay page with status message
    }
	
    

    
    private String recipient;
    private String subject;
    private String message;
    private String statusMessage = "";
    
    private String archivo;
    private String cc;
    private String bcc;
    
    private UploadedFile file;
    

}
