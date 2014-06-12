package cl.usach.diinf.revalora.correo.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
* 
* @author	Sergio Reyes
* @updated	Alvaro Maldonado
* @version  1.0 
*/
@ManagedBean
@RequestScoped
public class BandejaCorreoBackingMB implements Serializable {

	/**
	 * Nœmero versi—n
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Logger de la clase
	 * @since 1.0
	 */	
	public static Logger logger = Logger.getLogger(BandejaCorreoBackingMB.class);

	private TreeNode mailboxes;
	private TreeNode mailbox;
	private String pagina = "recibidos";
	/**
	 * Metodo que define la estructura de carpeta de correos
	 * @since 1.0
	 */
	@PostConstruct
	public void init() {
		
		if (logger.isInfoEnabled()){
			logger.info("[BandejaCorreoBackingMB][init] Inicio Metodo");
		}
		
		mailboxes = new DefaultTreeNode("root", null);

		TreeNode inbox = new DefaultTreeNode("i", "Inbox", mailboxes);
		TreeNode sent = new DefaultTreeNode("e", "Enviados", mailboxes);
		TreeNode trash = new DefaultTreeNode("b", "Borradores", mailboxes);
		TreeNode junk = new DefaultTreeNode("j", "Eliminados", mailboxes);
		
		if (logger.isInfoEnabled()){
			logger.info("[BandejaCorreoBackingMB][init] Fin");
		}

	}
	/**
	 * Metodo que indica la actualizacion de un correo
	 * @return
	 * @since 1.0
	 */
	public String actualizacorreo() {
		System.out.println("actualiza correo");
		return "success";
	}
	/**
	 * Metodo que permite la visualizacion de una carpeta de correo seleccionada
	 * @param event carpeta de correo seleccionada
	 * @since 1.0
	 */
	public void procesaSeleccion(NodeSelectEvent event) {
		
		if (logger.isInfoEnabled()){
			logger.info("[BandejaCorreoBackingMB][procesaSeleccion] Inicio Metodo");
			logger.info("[BandejaCorreoBackingMB][procesaSeleccion] Evento: " + event.getTreeNode().toString());
		}
		if (event.getTreeNode().toString() == "Inbox") {
			verRecibidos();
		} else if (event.getTreeNode().toString() == "Enviados") {
			verEnviados();
		} else if (event.getTreeNode().toString() == "Borradores") {
			verBorradores();
		} else if (event.getTreeNode().toString() == "Eliminados") {
			verRecibidos();
		}
		if (logger.isInfoEnabled()){
			logger.info("[BandejaCorreoBackingMB][procesaSeleccion] Pagina selecionada: " + this.pagina + ".jsf");
			logger.info("[BandejaCorreoBackingMB][procesaSeleccion] Fin Metodo");
		}
	}

	public void verRecibidos() {
		this.pagina = "recibidos";
	}

	public void verBorradores() {
		this.pagina = "borradores";
	}

	public void verEnviados() {
		this.pagina = "enviados";
	}

	public TreeNode getMailboxes() {
		return mailboxes;
	}

	public TreeNode getMailbox() {
		return mailbox;
	}

	public void setMailbox(TreeNode mailbox) {
		this.mailbox = mailbox;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
