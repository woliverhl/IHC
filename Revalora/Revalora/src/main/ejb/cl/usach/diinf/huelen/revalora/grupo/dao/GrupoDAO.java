package cl.usach.diinf.huelen.revalora.grupo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.huelen.revalora.grupo.entities.GrupoEntity;
import cl.usach.diinf.revalora.grupo.util.GrupoUtil;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.diinf.revalora.persona.entities.PersonaEntity;
import cl.usach.diinf.revalora.personas.util.PersonaUtil;

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
		this.log.info("Crea constructor");
		try{
			this.entityManager = Persistence.createEntityManagerFactory("revalora-pu").createEntityManager();
		} catch (Exception e) {
			this.log.error("Error " + e);
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
	public void insertaUserGroup (GrupoDTO u) throws Exception {

		this.log.info("Ingresa metodo");
		if(u.getId()<=0) {
			u.setId(this.obtenerProximoIdGrupo());
		}
		GrupoEntity ue = GrupoUtil.transforma(u);
		try {
			this.log.info("Inicia transacion");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(ue);
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transacion");
		}catch(Exception e) {
			this.entityManager.getTransaction().rollback();
			this.log.error("Error " + e);
			throw e;
		}
	}

	public int obtenerProximoIdGrupo() {

		return
			Integer.parseInt(
				this.entityManager.createQuery("SELECT MAX(E.id) FROM GrupoEntity E")
					.getSingleResult()
						.toString()
				) + 1;
	}

	/**
	 * Metodo que retorna la lista de usergroups.
	 * @since 1.0
	 * @return Lista de objetos usergroups.
	 */
    public List<GrupoDTO> obtenerUserGroups() throws Exception{

		try {
			this.log.info("Antes de llamar createNamedQuery");
			TypedQuery<GrupoEntity> salida = this.entityManager.createNamedQuery(GrupoEntity.SQL_SELECT_ALL, GrupoEntity.class);
			this.log.info("Despues de llamar TypedQuery<GrupoEntity> salida");
			List<GrupoEntity> luge = salida.getResultList();
			this.log.info("Despues de llamar salida.getResultList()");

			return GrupoUtil.transforma(luge);

		} catch(Exception e) {
			this.log.error("Error " + e);
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
	public void eliminaUserGroup(GrupoDTO u) throws Exception {
		this.log.info("Iniciando eliminausergroup");
		try {
			this.log.info("Antes de transformar UserGroup a entidad");
			GrupoEntity uge = GrupoUtil.transforma(u);
			this.log.info("Busca UserGroup para eliminar");
			uge = this.entityManager.find(GrupoEntity.class, uge.getId());
			this.log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(uge);
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transaccion");
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
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
	public void actualizaUserGroup(GrupoDTO u) throws Exception {
		this.log.info("Iniciando actualziaUserGroup");
		try {
			this.log.info("Busca UserGroup para eliminar");
			GrupoEntity ue = this.entityManager.find(GrupoEntity.class, u.getId() );
			this.log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.log.info("Seteamos datos transaccion");
		//	ue.setId(u.getId());
			ue.setGroupName(u.getGroupName());
			ue.setDescripcion(u.getDescripcion());
		//	List<PersonaEntity> persons = new ArrayList<>();
//			for (PersonaDTO p : u.getPersonas()){
//				PersonaEntity pEntity = new PersonaEntity();
//				pEntity.setApellido(p.getApellido());
//				pEntity.setClave(p.getClave());
//				pEntity.setCorreo(p.getCorreo());
//				pEntity.setCumpleano(p.getCumpleano());
//				pEntity.setDireccion(p.getDireccion());
//				pEntity.setGenero(p.getGenero());
//				pEntity.setId(p.getId());
//				pEntity.setNombre(p.getNombre());
//				pEntity.setPosicion(p.getPosicion());
//				pEntity.setRut(p.getRut());
//				pEntity.setTelefono(p.getTelefono());
//				persons.add(pEntity);
//			}
		//	ue.setPersons(persons);
			this.log.info("Termino datos transaccion");
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transaccion");
		} catch (Exception e) {
			//this.entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Método encargado de obtener en la base de datos un UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup que se elimina.
	 * @return GrupoDTO segun su llave
	 * @throws Exception
	 * @author Pablo Gavilan
	 * @since 1.0
	 */
	public GrupoDTO obtenerUserGroups(int id) throws Exception {
		this.log.info("Iniciando obtenerGrupo(String rut)");
		try {
			this.log.info("Busca grupo");
			GrupoEntity pe = this.entityManager.find(GrupoEntity.class, id);
			this.log.info("Termina transaccion y retorna");
			return GrupoUtil.transforma(pe);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
    }

	/**
	 * Método encargado de obtener en la base de datos un UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup que se elimina.
	 * @return GrupoDTO segun su llave
	 * @throws Exception
	 * @author Pablo Gavilan
	 * @since 1.0
	 */
	public List<PersonaDTO> obtenerPersonasPorGrupo(int id) throws Exception {
		this.log.info("Iniciando obtenerGrupo(String rut)");
		try {
			this.log.info("Busca grupo");

			TypedQuery<PersonaEntity> query = this.entityManager.createQuery(
			        "SELECT c FROM PersonaEntity c WHERE c.id = :id", PersonaEntity.class);
			query.setParameter("id", id);

			List<PersonaEntity> salida = query.getResultList();

			this.log.info("Termina transaccion y retorna");
			return PersonaUtil.transforma(salida);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	public void agregarPersonasAlGrupo(int id, PersonaDTO dto) throws Exception{

		this.log.info("Comienza transaccion");
		Query q = this.entityManager.createQuery(
				"UPDATE PersonaEntity p SET p.id=:id WHERE p.rut = :rut"
				);
		this.log.info("Seteamos datos transaccion");
		q.setParameter("id", id);
		q.setParameter("rut", dto.getRut());
		this.log.info("Termino datos transaccion");

		try {
			this.entityManager.getTransaction().begin();
			this.log.info("Termina transaccion con " + q.executeUpdate() + " objetos actualizados");
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	};
}
