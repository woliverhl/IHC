package cl.usach.diinf.revalora.correo.mb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import cl.usach.diinf.huelen.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.revalora.correo.MailServiceBean;
import cl.usach.diinf.revalora.persona.PersonaBean;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;

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
	
	private String modelo;
	private List<PersonaDTO> personasGrupo;
	private Map<String, String> modelos = new HashMap<String, String>();
	private String text;
    private String recipient = "";
    private String subject;
    private String message;
    private String statusMessage = "";
    private String archivo;
    private String cc;
    private String bcc;
    private UploadedFile file;
    
    @EJB
	PersonaBean bean;

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
    	MailServiceBean.enviarDraft(recipient, subject, text, archivo, cc, bcc);
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
        	
        	
            MailServiceBean.sendMessage(recipient, subject, text, archivo, cc, bcc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo Enviado"));
        } 
        catch(MessagingException ex) {  
            statusMessage = ex.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se pudo enviar el correo"));
        }
        
        return "enviar";  // redisplay page with status message
    }
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



	public String getModelo() {
		return modelo;
	}


		public void setModelo(String modelo) {
			this.modelo = modelo;
		}


	public Map<String, String> getModelos() {
		return modelos;
	}


	public void setModelos(Map<String, String> modelos) {
		this.modelos = modelos;
	}

	public String getStatusMessage() {
		return statusMessage;
	}


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	
	public void onHandleKeyEvent() {
		if(modelo !=null && !modelo.equals(""))
            text = modelo;
        else
            text = "";
		
      System.out.println((modelo));
	}
	

	@PostConstruct
	public void init(){
		modelos = new HashMap<String, String>();

		Properties prop = new Properties();
		InputStream input = null;
		try{
			String filename = "mensajes.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("No se puede encontrar el archivo " + filename);
				return;
			}
			
			prop.load(input);

			Enumeration<?> e = prop.propertyNames();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				System.out.println("Key : " + key + ", Value : " + value);
				modelos.put(key, value);
			}


		}catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}
	
	public void obtenerCorreosPorIdGrupo(GrupoDTO g) {
		System.out.println("antes");

		try {
			logger.info("antes de llamar bean.obtenerPersonas()");
			personasGrupo = bean.obtenerPersonasPorIdGrupo(g.getId());
			int i=0;
			if (personasGrupo.size() > 0) {
				for (PersonaDTO p : personasGrupo) {
					i++;
					System.out.println(p.getCorreo());
					if(!"null".equalsIgnoreCase(p.getCorreo())){
						if(i==personasGrupo.size()){
							recipient += p.getCorreo();
						}else{
							recipient += p.getCorreo() + ", ";
						}						
					}
				}
			} else {
				recipient = "";
			}

			System.out.println(recipient);
		} catch (Exception e) {
			logger.error("Error al PersonaIDAO.obtenerPersonas():" + e);
			System.out.println(e.getMessage());
		}

	}

	
    

}
