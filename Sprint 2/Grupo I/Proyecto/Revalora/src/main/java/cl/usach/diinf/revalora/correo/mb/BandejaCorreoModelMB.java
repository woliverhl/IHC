package cl.usach.diinf.revalora.correo.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

import cl.usach.diinf.revalora.correo.to.CorreoTO;
import cl.usach.infraestructura.util.TablaValores;

@ManagedBean
@SessionScoped
public class BandejaCorreoModelMB implements Serializable {

	/**
	 * Variable que representa el numero de versión
	 */
	private static final long serialVersionUID = 2L;
	private static Logger logger = Logger.getLogger(BandejaCorreoModelMB.class);
	private static String tablaValores = "correo.parametros";
	private Session mailSession;
	private static final String USER = TablaValores.getValor(tablaValores, "correo", "user");
	private static final String PASSWORD = TablaValores.getValor(tablaValores, "correo", "pass");
	private List<CorreoTO> correosRecibidos;
	private List<CorreoTO> correosEnviados;
	private List<CorreoTO> correosBorradores;
	private boolean textIsHtml = false;

	private void correoEnviados() {

		if (logger.isInfoEnabled()) {
			logger.info("[BandejaCorreoModelMB][correoEnviados] Inicio Metodo");
			logger.info("[BandejaCorreoModelMB][correoEnviados] Instanciar Properties");
		}
		correosEnviados = new ArrayList<CorreoTO>();

		instanciarProperties();

		try {
			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoEnviados] Comienza apertura de carpeta");
				logger.info("[BandejaCorreoModelMB][correoEnviados] Usuario: " + USER);
			}

			Properties props = new Properties();
			props.setProperty("mail.store.protocol", "imaps");

			mailSession = Session.getInstance(props, null);
			Store store = mailSession.getStore();
			store.connect(TablaValores.getValor(tablaValores, "correo", "servidor"), USER, PASSWORD);
			Folder folder = store.getFolder("[Gmail]/Sent Mail");
			folder.open(Folder.READ_ONLY);
			Message[] listaMensajes = folder.getMessages();

			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoEnviados] Carpeta abierta");
				logger.info("[BandejaCorreoModelMB][correoEnviados] mensajesEnviados: " + listaMensajes.length);
			}
			int ind = 1;
			for (Message message : listaMensajes) {
				CorreoTO correo = new CorreoTO();
				correo.setFrom(message.getFrom()[0].toString());
				correo.setSubject(message.getSubject());
				correo.setFecha(message.getSentDate().toString());
				correo.setText(message.getContent().toString());
				correo.setNumero(ind);
				correosEnviados.add(correo);
				ind++;
			}

			folder.close(false);
			store.close();

			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoEnviados] Fin");
			}

		} catch (Exception e) {
			logger.warn("[BandejaCorreoModelMB][correoEnviados] ERROR:", e);
			e.printStackTrace();
		}
	}

	private void correoRecibidos() {

		if (logger.isInfoEnabled()) {
			logger.info("[BandejaCorreoModelMB][correoRecibidos] Inicio Metodo");
			logger.info("[BandejaCorreoModelMB][correoRecibidos] Instanciar Properties");
		}
		correosRecibidos = new ArrayList<CorreoTO>();

		instanciarProperties();

		try {
			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoRecibidos] Comienza apertura de carpeta");
				logger.info("[BandejaCorreoModelMB][correoRecibidos] Usuario: " + USER);
			}

			Properties props = new Properties();
			props.setProperty("mail.store.protocol", "imaps");

			mailSession = Session.getInstance(props, null);

			// Store store =
			// mailSession.getStore(TablaValores.getValor(tablaValores,
			// "correo", "servidorshort"));

			Store store = mailSession.getStore();
			store.connect(TablaValores.getValor(tablaValores, "correo", "servidor"), USER, PASSWORD);
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			// FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
			Message[] listaMensajes = folder.getMessages();// folder.search(ft);

			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoRecibidos] Carpeta abierta");
				logger.info("[BandejaCorreoModelMB][correoRecibidos] mensajesEnviados: " + listaMensajes.length);
			}
			int ind = 1;
			for (Message message : listaMensajes) {
				CorreoTO correo = new CorreoTO();
				correo.setFrom(message.getFrom()[0].toString());
				correo.setSubject(message.getSubject());
				correo.setFecha(message.getSentDate().toString());
//				correo.setText(getText(message));message.getContent().toString();
				correo.setText(message.getContent().toString());
				correo.setNumero(ind);
				correosRecibidos.add(correo);
				ind++;
			}

			folder.close(false);
			store.close();

			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoRecibidos] Fin");
			}

		} catch (Exception e) {
			logger.warn("[BandejaCorreoModelMB][correoRecibidos] ERROR:", e);
			e.printStackTrace();
		}
	}

	private void correoBorradores() {

		if (logger.isInfoEnabled()) {
			logger.info("[BandejaCorreoModelMB][correoBorradores] Inicio Metodo");
			logger.info("[BandejaCorreoModelMB][correoBorradores] Instanciar Properties");
		}
		correosBorradores = new ArrayList<CorreoTO>();

		instanciarProperties();

		try {
			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoBorradores] Comienza apertura de carpeta");
				logger.info("[BandejaCorreoModelMB][correoBorradores] Usuario: " + USER);
			}

			Properties props = new Properties();
			props.setProperty("mail.store.protocol", "imaps");

			mailSession = Session.getInstance(props, null);
			Store store = mailSession.getStore();
			store.connect(TablaValores.getValor(tablaValores, "correo", "servidor"), USER, PASSWORD);

			Folder[] f = store.getDefaultFolder().list();
			for (Folder fd : f) {
				System.out.println(">> " + fd.getName());
			}

			Folder folder = store.getFolder("[Gmail]/Drafts");
			folder.open(Folder.READ_ONLY);
			Message[] listaMensajes = folder.getMessages();

			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoBorradores] Carpeta abierta");
				logger.info("[BandejaCorreoModelMB][correoBorradores] mensajesEnviados: " + listaMensajes.length);
			}
			int ind = 1;
			for (Message message : listaMensajes) {
				CorreoTO correo = new CorreoTO();
				correo.setFrom(message.getFrom()[0].toString());
				correo.setSubject(message.getSubject());
				correo.setFecha(message.getSentDate().toString());
				correo.setText(message.getContent().toString());
				correo.setNumero(ind);
				correosBorradores.add(correo);
				ind++;
			}

			folder.close(false);
			store.close();

			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][correoBorradores] Fin");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.warn("[BandejaCorreoModelMB][correoBorradores] ERROR:" + e.getMessage());
		}
	}

	private void instanciarProperties() {

		if (logger.isInfoEnabled()) {
			logger.info("[BandejaCorreoModelMB][instanciarProperties] Inicio Metodo");
		}

		if (mailSession != null) {
			if (logger.isInfoEnabled()) {
				logger.info("[BandejaCorreoModelMB][instanciarProperties] mailSession :" + mailSession.toString());
				logger.info("[BandejaCorreoModelMB][instanciarProperties] Fin");
				return;
			}
		}

		String puerto = TablaValores.getValor(tablaValores, "correo", "puerto");
		Properties props = new Properties();
		props.setProperty("mail.imap.starttls.enable", "true");
		props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback ", "true");
		props.setProperty("mail.imap.port", puerto);
		props.setProperty("mail.imap.socketFactory.port", puerto);
		mailSession = Session.getInstance(props);

		if (logger.isInfoEnabled()) {
			logger.info("[BandejaCorreoModelMB][instanciarProperties] Properties: " + props.toString());
			logger.info("[BandejaCorreoModelMB][instanciarProperties] Fin");
		}
	}

	public BandejaCorreoModelMB() {
		super();
	}

	public List<CorreoTO> getCorreoRecibidos() {
		if (this.correosRecibidos == null) {
			this.correoRecibidos();
		}
		return this.correosRecibidos;
	}

	public void setCorreoRecibidos(List<CorreoTO> correosRecibidos) {
		this.correosRecibidos = correosRecibidos;
	}

	public List<CorreoTO> getCorreosEnviados() {
		if (this.correosEnviados == null) {
			this.correoEnviados();
		}
		return this.correosEnviados;
	}

	public void setCorreosEnviados(List<CorreoTO> correosEnviados) {
		this.correosEnviados = correosEnviados;
	}

	public List<CorreoTO> getCorreosBorradores() {
		if (this.correosBorradores == null) {
			this.correoBorradores();
		}
		return this.correosBorradores;
	}

	public void setCorreosBorradores(List<CorreoTO> correosBorradores) {
		this.correosBorradores = correosBorradores;
	}

	/**
	 * Return the primary text content of the message.
	 */
	private String getText(Part p) {
		try {
			if (p.isMimeType("text/*")) {
				String s = (String) p.getContent();
				textIsHtml = p.isMimeType("text/html");
				System.out.println(s);
				return s;
			}
		} catch (MessagingException | IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			if (p.isMimeType("multipart/alternative")) {
				// prefer html text over plain text
				Multipart mp = (Multipart) p.getContent();
				String text = null;
				for (int i = 0; i < mp.getCount(); i++) {
					Part bp = mp.getBodyPart(i);
					if (bp.isMimeType("text/plain")) {
						if (text == null)
							text = getText(bp);
						continue;
					} else if (bp.isMimeType("text/html")) {
						String s = getText(bp);
						if (s != null)
							return s;
					} else {
						return getText(bp);
					}
				}
				return text;
			} else if (p.isMimeType("multipart/*")) {
				Multipart mp = (Multipart) p.getContent();
				for (int i = 0; i < mp.getCount(); i++) {
					String s = getText(mp.getBodyPart(i));
					if (s != null)
						return s;
				}
			}
		} catch (MessagingException | IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

}