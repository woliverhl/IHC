package cl.usach.diinf.huelen.revalora.usergroup.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.usergroup.dao.UserGroupBean;
import cl.usach.diinf.huelen.revalora.usergroup.dao.UserGroupDAO;
import cl.usach.diinf.huelen.revalora.usergroup.dto.UserGroup;

@Named @RequestScoped public class UserGroupMB {  

    @EJB UserGroupBean bean;
	/**
	 * Logger de la clase
	 */
	Logger log = Logger.getLogger(UserGroupDAO.class);
 
	private String groupName;
	private int id;
	private UserGroup userSelected;
	
	private List<UserGroup> userGroups;

	public UserGroupMB() {
		super();
	}

	@PostConstruct
	public void init(){
		try {
			log.info("antes de llamar bean.obtenerUserGroups()");
			userGroups = bean.obtenerUserGroups();
		} catch (Exception e) {
			log.error("Error al UserGroupIDAO.obtenerUserGroups():" + e);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una UserGroup.
	 * 
	 * @since 1.0
	 * @return 
	 */
    public String insertarUserGroup() {

    	log.info("inicio insertarPersoa");
    	try{
    		UserGroup u = this.getUserGroup();
    		bean.insertarUserGroup(u);
    		log.info("termino insertarPersoa");
    	} catch(Exception e) {
    		log.error("Error " + e.getMessage());
    		log.error("Error " + e.getClass());
    		return "error";
    	}
        return "success";
    }

	/**
	 * Metodo encargado de llamar al EJB para insertar una UserGroup.
	 * 
	 * @since 1.0
	 * @return 
	 */
    public String actualizaUserGroup() {

    	log.info("inicio actualizaUserGroup");
    	try{
    		UserGroup u = this.getUserGroup();
    		bean.actualizaUserGroup(u);
    		log.info("termino actualizaUserGroup");
    	} catch(Exception e) {
    		log.error("Error actualizaUserGroup");
    		log.error("Error " + e.getMessage());
    		log.error("Error " + e.getClass());
    		return "error";
    	}
        return "success";
    }

	/**
	 * Metodo encargado de llamar al EJB para insertar una UserGroup.
	 * 
	 * @since 1.0
	 * @return 
	 */
    public String eliminaUserGroup() {

    	log.info("inicio eliminaUserGroup");
    	try{
    		UserGroup u = this.getUserGroup();
    		bean.eliminaUserGroup(u);
    		log.info("termino eliminaUserGroup");
    	} catch(Exception e) {
    		log.error("Error eliminaUserGroup");
    		log.error("Error " + e.getMessage());
    		log.error("Error " + e.getClass());
    		return "error";
    	}
        return "success";
    }
    
    
    
	public List<UserGroup> obtenerUserGroups() throws Exception {
		try {
			log.info("Antes de obtenerUserGroups");
			
			return bean.obtenerUserGroups();
		} catch (Exception e) {
			log.error("Error en obtenerUserGroups");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}
    

    /**
     * Metodo encargado de obtener un objeto UserGroup de los atributos de MB
     * 
     * @since 1.0 
     * @param p Objeto UserGroup
     * @return
     */
	private UserGroup getUserGroup() {
		
		UserGroup ug = new UserGroup();

		ug.setId(this.id);
		ug.setGroupName(this.groupName);
		
		return ug;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<UserGroup> getUserGroups() {
		return this.userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
   
	public UserGroup getUserSelected() {
		return this.userSelected;
	}

	public void setUserSelected(UserGroup userSelected) {
		this.userSelected = userSelected;
	}

}
