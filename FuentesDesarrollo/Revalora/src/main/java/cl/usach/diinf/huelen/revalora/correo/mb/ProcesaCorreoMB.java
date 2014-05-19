package cl.usach.diinf.huelen.revalora.correo.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
 

@Named 
@ManagedBean  
public class ProcesaCorreoMB {
	
	private TreeNode mailboxes;   
	private TreeNode mailbox;
	private String pagina="recibidos";
	
	@PostConstruct
    public void init() {  
        mailboxes = new DefaultTreeNode("root", null);

        TreeNode inbox = new DefaultTreeNode("i","Inbox", mailboxes);
        TreeNode sent = new DefaultTreeNode("e", "Enviados", mailboxes);
        TreeNode trash = new DefaultTreeNode("b", "Borradores", mailboxes);
        TreeNode junk = new DefaultTreeNode("j", "Eliminados", mailboxes);

	}  
	
	
	public String actualizacorreo(){
		System.out.println("actualiza correo");
		return "success";    
	}
	
	
	public void procesaSeleccion(NodeSelectEvent event){
		
		System.out.println("CORREO " + event.getTreeNode().toString());
		if(event.getTreeNode().toString() == "Inbox"){
			verRecibidos();
		}else if(event.getTreeNode().toString() == "Enviados"){
			System.out.println("CORREO enviados");
			verEnviados();
		}else if(event.getTreeNode().toString() == "Borradores"){
			System.out.println("CORREO borradores");
			verBorradores();
		}else if(event.getTreeNode().toString() == "Eliminados"){
			System.out.println("CORREO eliminados");
			verRecibidos();
		}
		
	}
	
	public void verRecibidos(){
		this.pagina = "recibidos";
	}
	
	public void verBorradores(){
		this.pagina = "borradores"; 
	} 
	
	public void verEnviados(){
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



