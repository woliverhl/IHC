package cl.usach.diinf.revalora.correo;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.ejb.Stateless;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import cl.usach.infraestructura.util.TablaValores;

/**
* 
* @author	Sergio Reyes
* @updated	Alvaro Maldonado
* @version  1.0 
*/

@Stateless
public class MailServiceBean {
	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	static Logger logger = Logger.getLogger(MailServiceBean.class);

	/**
	 * @param recipient
	 * @param subject
	 * @param message
	 * @param archivo
	 * @param cc
	 * @param bcc
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void enviarDraft(String recipient, String subject,
			String text, String archivo, String cc, String bcc)
			throws MessagingException, IOException {

		draftOrSend = 1;
		sendMessage(recipient, subject, text, archivo, cc, bcc);
	}

	/**
	 * Sends a subject and message to a recipient
	 * 
	 * @param recipient
	 *            Internet address of the recipient
	 * @param subject
	 *            the subject of the message
	 * @param message
	 *            the message
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void sendMessage(String recipient, String subject,
			String text, String archivo, String cc, String bcc)
			throws MessagingException, IOException {

		if (theService == null) {
			theService = new MailServiceBean();
		}
		logger.debug("[MailService] sendMessage destinatario " + recipient);
		logger.debug("[MailService] sendMessage asunto       " + subject);
		logger.debug("[MailService] sendMessage mensaje      " + text);
		logger.debug("[MailService] sendMessage archivo 	 " + archivo);
		logger.debug("[MailService] sendMessage Cc 			 " + cc);
		logger.debug("[MailService] sendMessage Bcc 		 " + bcc);

		MimeBodyPart texto = new MimeBodyPart();
		texto.setContent(text, "text/html; charset=utf-8");

		// seteo de multiples destinatarios
		String[] recipientList = recipient.split(",");
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];

		int counter = 0;
		for (String recipient1 : recipientList) {
			recipientAddress[counter] = new InternetAddress(recipient1.trim());
			counter++;
		}

		// se compone el correo
		MimeMessage mimeMessage = new MimeMessage(mailSession);
		mimeMessage.setFrom(new InternetAddress(FROM));
		mimeMessage.setSubject(subject);
		mimeMessage.setRecipients(Message.RecipientType.TO, recipientAddress);

		if (cc != "") {
			logger.debug("[MailService] setea cc |" + cc + "|");
			mimeMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));

		}

		if (bcc != "") {
			logger.debug("[MailService] setea bcc " + bcc);
			mimeMessage.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));

		}

		mimeMessage.setDataHandler(new DataHandler(new ByteArrayDataSource(text, "text/html")));

		if (draftOrSend == 0) {
			Transport transport = mailSession.getTransport("smtps");
			transport.connect(HOST, PORT, USER, PASSWORD);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());// Recipients(Message.RecipientType.TO));
			transport.close();
		} else {
			saveDraftMessage(mimeMessage);
		}

	}
	
	/**
	 * @param draftMessage
	 * @throws MessagingException
	 */
	public static void saveDraftMessage(MimeMessage draftMessage)
			throws MessagingException {

		String host = "imap.gmail.com";
		String username = USER;
		String password = PASSWORD;
		Properties props = new Properties();
		Session session = Session.getInstance(props);
		Store store = session.getStore("imaps");
		store.connect(host, username, password);

		try {
			session.setDebug(true);
			System.out.println(store);

			Folder draftsMailBoxFolder = store.getFolder("[Gmail]/Drafts");// [Gmail]/Drafts
			draftsMailBoxFolder.open(Folder.READ_WRITE);
			draftMessage.setFlag(Flag.DRAFT, true);
			MimeMessage draftMessages[] = { draftMessage };
			draftsMailBoxFolder.appendMessages(draftMessages);

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}

	}

	/**
	 * 
	 */
	private MailServiceBean() {
		Properties props = new Properties();

		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", HOST);
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtp.from", FROM);
		props.put("mail.smtps.quitwait", "false");

		mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);
	}

	private static MailServiceBean theService = null;
	private static Session mailSession;
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 465;
//	private static String tablaValores = "correo.parametros";
	private static final String USER = "ihcgrupo3"; //
	private static final String PASSWORD = "grupo32014"; //
	private static final String FROM = "Grupo 3 <ihcgrupo3@gmail.com>"; //
//	private static final String USER = TablaValores.getValor(tablaValores, "correo", "user");
//	private static final String PASSWORD = TablaValores.getValor(tablaValores, "correo", "pass");
	private static int draftOrSend = 0;
}