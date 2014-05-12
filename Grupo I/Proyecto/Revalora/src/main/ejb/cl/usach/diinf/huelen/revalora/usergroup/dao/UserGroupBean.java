package cl.usach.diinf.huelen.revalora.usergroup.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.usach.diinf.huelen.revalora.usergroup.dto.UserGroup;

@Stateless public class UserGroupBean {

	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	Logger log = LoggerFactory.getLogger(UserGroupBean.class);

	/**
	 * Objeto de conexion a la clase de acceso de datos para UserGroup
	 * @since 1.0
	 */
	UserGroupIDAO dao;

	/**
	 * Constructor de la clase
	 * @since 1.0
	 */
	public UserGroupBean() {
		log.info("Instancia EJB");
		dao = new UserGroupDAO();
	}

	/**
	 * Método encargado de ingresar a la base de datos una UserGroup.
	 * 
	 * @param p Objeto UserGroup ingresada a la base de datos.
	 * @throws Exception 
	 * @since 1.0
	 */
	public void insertarUserGroup(UserGroup u) throws Exception {
		try {
			log.info("Antes de insertar");
			dao.insertaUserGroup(u);
			log.info("Despues de insertar");
		} catch (Exception e) {
			log.error("Error en insertarPersoa");
			log.error("UserGroup " + u.toString());
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de actualziar a la base de datos una UserGroup.
	 * 
	 * @param p Objeto UserGroup ingresada a la base de datos.
	 * @throws Exception 
	 * @since 1.0
 */
	public void actualizaUserGroup(UserGroup u) throws Exception {
		try { 
			log.info("Antes de actualizaUserGroup");
			dao.actualizaUserGroup(u);;
			log.info("Despues de actualizaUserGroup");
		} catch (Exception e) {
			log.error("Error en actualizaUserGroup");
			log.error("UserGroup " + u.toString());
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de elimina en la base de datos una UserGroup.
	 * 
	 * @param p Objeto UserGroup que se elimina.
	 * @return 
	 * @throws Exception 
	 * @since 1.0
	 */
	public List<UserGroup> obtenerUserGroups() throws Exception {
		try {
			log.info("Antes de obtenerUserGroups");
			return dao.obtenerUserGroups();
		} catch (Exception e) {
			log.error("Error en obtenerUserGroups");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de elimina en la base de datos una UserGroup.
	 * 
	 * @param p Objeto UserGroup que se elimina.
	 * @throws Exception 
	 * @since 1.0
	 */
	public void eliminaUserGroup(UserGroup u) throws Exception {
		try {
			log.info("Antes de eliminaUserGroup");
			dao.eliminaUserGroup(u);
			log.info("Despues de eliminaUserGroup");
		} catch (Exception e) {
			log.error("Error en eliminaUserGroup");
			log.error("UserGroup " + u.toString());
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}


}
