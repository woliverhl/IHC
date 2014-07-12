package cl.usach.diinf.revalora.persona.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import cl.usach.diinf.revalora.persona.dto.PostulanteDTO;
import cl.usach.diinf.revalora.persona.entities.PostulanteEntity;

/**
 * <p>
 * PersonaDAO
 * </p>
 * 
 * Clase encargada del acceso a la capa de datos para las funciones relevantes
 * para una persona.
 * 
 * @author Pablo Gavilan
 * @version 1.0
 * 
 */
public class PostulanteDAO implements PostulanteDAOImpl {

	/**
	 * Logger de la clase
	 * 
	 * @since 1.0
	 */
	Logger log = Logger.getLogger(PersonaDAO.class);

	/**
	 * Objeto encargado de la conexion por jpa a la capa de datos
	 * 
	 * @since 1.0
	 */
	private EntityManager entityManager;

	/**
	 * Constructor de la clase que instancia al #entityManager
	 * 
	 * @since 1.0
	 */
	public PostulanteDAO() {
		this.log.info("Crea constructor");
		try {
			this.entityManager = Persistence.createEntityManagerFactory(
					"revalora-pu").createEntityManager();
		} catch (Exception e) {
			this.log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo encargado de insertar una persona a la base de datos.
	 * 
	 * @author Pablo Gavilan (07/05/2014).
	 * @param p
	 *            Persona a agregar a la base de datos.
	 * @throws Exception
	 *             exepcion lanzada al insertar la persona.
	 * @since 1.0
	 */
	public void insertaPostulante(PostulanteDTO p) throws Exception {

		this.log.info("Ingresa metodo");
		PostulanteEntity pe = this.transforma(p);
		try {
			this.log.info("Inicia transacion");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(pe);
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transacion");
		} catch (Exception e) {
			this.log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo que retorna la lista de personas.
	 * 
	 * @since 1.0
	 * @return Lista de objetos personas.
	 */
	public List<PostulanteDTO> obtenerPostulantes() throws Exception {

		try {
			this.log.info("Antes de llamar createNamedQuery");
			List<PostulanteEntity> lpe = this.entityManager.createNamedQuery(
					PostulanteEntity.SQL_SELECT_ALL, PostulanteEntity.class)
					.getResultList();

			List<PostulanteDTO> lp = new ArrayList<PostulanteDTO>();
			for (PostulanteEntity postulanteEntitie : lpe) {
				lp.add(this.transforma(postulanteEntitie));
			}

			return lp;

		} catch (Exception e) {
			this.log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Clase encargada en eliminar a una persona
	 * 
	 * @since 1.0
	 * @param p
	 *            Objeto persona a eliminar.
	 * @throws Exception
	 * 
	 */
	public void eliminaPostulante(PostulanteDTO p) throws Exception {
		this.log.info("Iniciando eliminaPErsona");
		try {
			this.log.info("Antes de transformar persona a entidad");
			PostulanteEntity pe = this.transforma(p);
			this.log.info("Busca persona para eliminar");
			pe = this.entityManager.find(PostulanteEntity.class, pe.getRut());
			this.log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(pe);
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transaccion");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Clase encargada en actualziar a una persona
	 * 
	 * @since 1.0
	 * @param p
	 *            Datos de objeto persona a actualizar en la base de datos.
	 * @throws Exception
	 * 
	 */
	public void actualizaPostulante(PostulanteDTO p) throws Exception {
		this.log.info("Iniciando actualzaPostulante");
		try {

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Metodo que recive una persona y lo transforma en su identidad
	 * 
	 * @since 1.0
	 * @param p
	 *            Objeto persona a transformar en entidad
	 * @return entidad de tipo persona
	 */
	private PostulanteEntity transforma(PostulanteDTO p) {
		PostulanteEntity pe = new PostulanteEntity();
		pe.setRut(p.getRut());
		pe.setCv(p.getCv());
		return pe;
	}

	/**
	 * Metodo que recive una entidad persona y lo transforma en su objeto
	 * 
	 * @since 1.0
	 * @param p
	 *            Entidad persona a tranasforma.
	 * @return objeto persona.
	 */
	private PostulanteDTO transforma(PostulanteEntity p) {
		PostulanteDTO pos = new PostulanteDTO();
		pos.setRut(p.getRut());
		pos.setCv(p.getCv());
		return pos;
	}
}
