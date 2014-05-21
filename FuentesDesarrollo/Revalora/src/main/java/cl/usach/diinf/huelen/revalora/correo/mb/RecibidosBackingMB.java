package cl.usach.diinf.huelen.revalora.correo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.mail.Flags;
import javax.mail.Folder; 
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm; 

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.correo.to.Correo;


@ManagedBean
@SessionScoped 
public class RecibidosBackingMB implements Serializable{

	/**
	 * Indica el numero de serial version
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RecibidosBackingMB.class);
	 
	private List<Correo> correoSmall;
    private String statusMessage = "";
    private static Session mailSession;
    private static final String USER = "ihcgrupo3";     // Must be valid user in d.umn.edu domain, e.g. "smit0012"
    private static final String PASSWORD = "grupo32014"; // Must be valid password for smit0012
    private Correo selectedCorreo;
	
	
	public List<Correo> getCorreoSmall(){
		return this.correoSmall;
	}
	
    public String getStatusMessage() {
		return statusMessage;
	} 


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	 

	public Correo getSelectedCorreo() {
		return selectedCorreo;
	}


	public void setSelectedCorreo(Correo selectedCorreo) {
		this.selectedCorreo = selectedCorreo;
	} 


@PostConstruct 
	public void  RecibidosBeans2() {
		
		logger.debug("[RecibidosBeans] getCorreosRecibidos - ");

//		ArrayList<Correo> correoSmall = new ArrayList<>();
		/*********************/
		correoSmall = new ArrayList<Correo>();
		
		Properties props = new Properties();
		props.setProperty("mail.imap.starttls.enable", "true");
		props.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback ", "true");
		props.setProperty("mail.imap.port", "993");
		props.setProperty("mail.imap.socketFactory.port", "993");
		mailSession = Session.getInstance(props);

		try {
			// Se obtiene el Store y el Folder, para poder leer el correo.
			Store store = mailSession.getStore("imaps");

			store.connect("imap.gmail.com", USER, PASSWORD);
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
  
			// *mostrar los no leidos
			FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
			Message[] mensajesSinLeer = folder.search(ft);
			System.out.println("Correos sin leer: " + mensajesSinLeer.length);
			// List<Correo> correos = new ArrayList<Correo>();
			int ind = 0;
			for (Message message : mensajesSinLeer) {
				Correo correo = new Correo();
				correo.setFrom(mensajesSinLeer[ind].getFrom()[0].toString());
				correo.setSubject(mensajesSinLeer[ind].getSubject());
				correo.setFecha(mensajesSinLeer[ind].getSentDate().toString());
				correo.setText(mensajesSinLeer[ind].getContent().toString());
				correo.setNumero(ind); 
				logger.debug("getCorreosRecibidos - From (sin leer "+ ind +")  " + mensajesSinLeer[ind].getFrom()[0].toString());
//				logger.debug("getCorreosRecibidos - Asunto (sin leer "+ ind +")" + mensajesSinLeer[ind].getSubject());
//				logger.debug("getCorreosRecibidos - Fecha (sin leer "+ ind +") " + mensajesSinLeer[ind].getSentDate().toString());
//				logger.debug("getCorreosRecibidos - Texto (sin leer "+ ind +") " + mensajesSinLeer[ind].getContent().toString());
				ind++;
				correoSmall.add(correo);
			}
 
			folder.close(false);
			store.close();

			/*************************/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
