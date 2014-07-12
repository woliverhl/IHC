package cl.usach.diinf.revalora.grupo.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "mapSiteView")
@ViewScoped
public class MapSiteUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeNode root;

	@SuppressWarnings("unused")
	@PostConstruct
	public void init() {
		root = new DefaultTreeNode("Revalora", null);
		TreeNode node0 = new DefaultTreeNode("Gestión de personas", root);
		TreeNode node1 = new DefaultTreeNode("Gestión de grupos", root);
		TreeNode node2 = new DefaultTreeNode("Selección de personal", root);
		TreeNode node3 = new DefaultTreeNode("Estrategia organizacional", root);
		TreeNode node4 = new DefaultTreeNode("clima organizacional", root);
		TreeNode node5 = new DefaultTreeNode("Correo", root);

		TreeNode node00 = new DefaultTreeNode("Crear", node0);
		TreeNode node01 = new DefaultTreeNode("Editar", node0);
		TreeNode node02 = new DefaultTreeNode("Listar", node0);
		
		TreeNode node10 = new DefaultTreeNode("Crear", node1);
		TreeNode node11 = new DefaultTreeNode("Listar", node1);
		
		TreeNode node20 = new DefaultTreeNode("Crear proceso", node2);
		TreeNode node21 = new DefaultTreeNode("Editar proceso", node2);
		TreeNode node22 = new DefaultTreeNode("Listar proceso", node2);
		
		TreeNode node30 = new DefaultTreeNode("Crear estudio", node3);
		TreeNode node31 = new DefaultTreeNode("Listar estudios", node3);
		
		TreeNode node40 = new DefaultTreeNode("No disponible", node4);
		
		TreeNode node50 = new DefaultTreeNode("Inbox", node5);
		TreeNode node51 = new DefaultTreeNode("Enviados", node5);
		TreeNode node52 = new DefaultTreeNode("Borradores", node5);
		TreeNode node53 = new DefaultTreeNode("Eliminados", node5);
	}

	public TreeNode getRoot() {
		return root;
	}

}
