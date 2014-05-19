package cl.usach.diinf.huelen.revalora.correo.mb;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
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


public class MailService {
	static Logger logger = Logger.getLogger(MailService.class);

	public static void enviarDraft(String recipient, String subject,
			String message, String archivo, String cc, String bcc)
			throws MessagingException, IOException {

		draftOrSend = 1;
		sendMessage(recipient, subject, message, archivo, cc, bcc);
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
			String message, String archivo, String cc, String bcc)
			throws MessagingException, IOException {

		if (theService == null) {
			theService = new MailService();
		}
		logger.debug("[MailService] sendMessage destinatario " + recipient);
		logger.debug("[MailService] sendMessage asunto       " + subject);
		logger.debug("[MailService] sendMessage mensaje      " + message);
		logger.debug("[MailService] sendMessage archivo 	 " + archivo);
		logger.debug("[MailService] sendMessage Cc 			 " + cc);
		logger.debug("[MailService] sendMessage Bcc 		 " + bcc);

		MimeBodyPart texto = new MimeBodyPart();
		texto.setContent(message, "text/html; charset=utf-8");

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

		mimeMessage.setDataHandler(new DataHandler(new ByteArrayDataSource(message, "text/html")));

		if (draftOrSend == 0) {
			Transport transport = mailSession.getTransport("smtps");
			transport.connect(HOST, PORT, USER, PASSWORD);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());// Recipients(Message.RecipientType.TO));
			transport.close();
		} else {
			saveDraftMessage(mimeMessage);
		}

	}

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


	private MailService() {
		Properties props = new Properties();

		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", HOST);
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtp.from", FROM);
		props.put("mail.smtps.quitwait", "false");

		mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);
	}

	private static MailService theService = null;
	private static Session mailSession;
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 465;
	private static final String USER = "ihcgrupo3"; //
	private static final String PASSWORD = "grupo32014"; //
	private static final String FROM = "Grupo 3 <ihcgrupo3@gmail.com>"; //
	private static int draftOrSend = 0;
}