package cl.usach.diinf.huelen.revalora.grupo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.grupo.dto.Grupo;
import cl.usach.diinf.huelen.revalora.grupo.entities.GrupoEntitie;

/**
 * <p>UserGroupDAO</p>
 * 
 * Clase encargada del acceso a la capa de datos para las funciones
 * relevantes para una persona.
 * 
 * @author Pablo Gavilan
 * @version 1.0
 *
 */
public class GrupoDAO implements GroupDAOImpl {

	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	Logger log = Logger.getLogger(GrupoDAO.class);

	/**
	 * Objeto encargado de la conexion por jpa a la capa de datos
	 * @since 1.0
	 */
	private EntityManager entityManager;

	/**
	 * Constructor de la clase que instancia al #entityManager
	 * @since 1.0
	 */
	public GrupoDAO() {
		log.info("Crea constructor");
		try{
			this.entityManager = Persistence.createEntityManagerFactory("revalora-pu").createEntityManager();
		} catch (Exception e) {
			log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo encargado de insertar una usergroup a la base de datos.
	 * 
	 * @author Pablo Gavilan (07/05/2014).
	 * @param p UserGroup a agregar a la base de datos.
	 * @throws Exception exepcion lanzada al insertar la UserGroup.
	 * @since 1.0
	 */
	public void insertaUserGroup (Grupo u) throws Exception {

		log.info("Ingresa metodo");
		GrupoEntitie ue = this.transforma(u);
		try {
			log.info("Inicia transacion");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(ue);
			this.entityManager.getTransaction().commit();
			log.info("Termina transacion");
		}catch(Exception e) {
			log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo que retorna la lista de usergroups.
	 * @since 1.0
	 * @return Lista de objetos usergroups. 
	 */
    public List<Grupo> obtenerUserGroups() throws Exception{

		try {
			log.info("Antes de llamar createNamedQuery");
			List<GrupoEntitie> luge = entityManager.createNamedQuery(GrupoEntitie.SQL_SELECT_ALL, GrupoEntitie.class).getResultList();
			
			List<Grupo> lug = new ArrayList<Grupo>();

			for (GrupoEntitie userGroupEntitie : luge){
				lug.add(transforma(userGroupEntitie));
			}
			

			return lug;
			
		} catch(Exception e) {
			log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Clase encargada en eliminar a una usergroup
	 * 
	 * @since 1.0
	 * @param p Objeto usergroup a eliminar.
	 * @throws Exception
	 * 
	 */
	public void eliminaUserGroup(Grupo u) throws Exception {
		log.info("Iniciando eliminausergroup");
		try {
			log.info("Antes de transformar UserGroup a entidad");
			GrupoEntitie uge = this.transforma(u);
			log.info("Busca UserGroup para eliminar");
			uge = entityManager.find(GrupoEntitie.class, uge.getId());
			log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(uge);
			this.entityManager.getTransaction().commit();
			log.info("Termina transaccion");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Clase encargada en actualziar a una UserGroup
	 * 
	 * @since 1.0
	 * @param p Datos de objeto UserGroup a actualizar en la base de datos.
	 * @throws Exception
	 * 
	 */
	public void actualizaUserGroup(Grupo u) throws Exception {
		log.info("Iniciando actualziaUserGroup");
		try {
			log.info("Busca UserGroup para eliminar");
			GrupoEntitie ue = entityManager.find(GrupoEntitie.class, u.getId() );
			log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			log.info("Seteamos datos transaccion");
			ue.setId(u.getId());
			ue.setGroupName(u.getGroupName());
			
			log.info("Termino datos transaccion");
			this.entityManager.getTransaction().commit();
			log.info("Termina transaccion");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Metodo que recive una UserGroup y lo transforma en su identidad
	 * 
	 * @since 1.0
	 * @param p Objeto UserGroup a transformar en entidad
	 * @return entidad de tipo UserGroup
	 */
	private GrupoEntitie transforma(Grupo ug) {
		GrupoEntitie uge = new GrupoEntitie();
		uge.setId(ug.getId());
		uge.setGroupName(ug.getGroupName());
		return uge;
	}

	/**
	 * Metodo que recive una entidad UserGroup y lo transforma en su objeto
	 * 
	 * @since 1.0
	 * @param p Entidad UserGroup a tranasforma.
	 * @return objeto UserGroup.
	 * UserGroup transforma(UserGroupEntitie p)
	 */
	private Grupo transforma (GrupoEntitie u)    {
		Grupo ug = new Grupo();
		ug.setId(u.getId());
		ug.setGroupName(u.getGroupName());
		return ug;
	}
}
