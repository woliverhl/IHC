package cl.usach.diinf.huelen.revalora.personas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.usach.diinf.huelen.revalora.personas.dto.Postulante;
import cl.usach.diinf.huelen.revalora.personas.entities.PostulanteEntitie;

/**
 * <p>PersonaDAO</p>
 * 
 * Clase encargada del acceso a la capa de datos para las funciones
 * relevantes para una persona.
 * 
 * @author Pablo Gavilan
 * @version 1.0
 *
 */
public class PostulanteDAO implements PostulanteIDAO {

	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	Logger log = LoggerFactory.getLogger(PersonaDAO.class);

	/**
	 * Objeto encargado de la conexion por jpa a la capa de datos
	 * @since 1.0
	 */
	private EntityManager entityManager;

	/**
	 * Constructor de la clase que instancia al #entityManager
	 * @since 1.0
	 */
	public PostulanteDAO() {
		log.info("Crea constructor");
		try{
			this.entityManager = Persistence.createEntityManagerFactory("revalora-pu").createEntityManager();			
		} catch (Exception e) {
			log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo encargado de insertar una persona a la base de datos.
	 * 
	 * @author Pablo Gavilan (07/05/2014).
	 * @param p Persona a agregar a la base de datos.
	 * @throws Exception exepcion lanzada al insertar la persona.
	 * @since 1.0
	 */
	public void insertaPostulante (Postulante p) throws Exception {

		log.info("Ingresa metodo");
		PostulanteEntitie pe = transforma(p);
		try {
			log.info("Inicia transacion");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(pe);
			this.entityManager.getTransaction().commit();
			log.info("Termina transacion");
		}catch(Exception e) {
			log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo que retorna la lista de personas.
	 * @since 1.0
	 * @return Lista de objetos personas. 
	 */
	public List<Postulante> obtenerPostulantes() throws Exception {

		try {
			log.info("Antes de llamar createNamedQuery");
			List<PostulanteEntitie> lpe = entityManager.createNamedQuery(PostulanteEntitie.SQL_SELECT_ALL, PostulanteEntitie.class).getResultList();

			List<Postulante> lp = new ArrayList<Postulante>();
			for (PostulanteEntitie postulanteEntitie : lpe) {
				lp.add(transforma(postulanteEntitie));
			}

			return lp;
			
		} catch(Exception e) {
			log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Clase encargada en eliminar a una persona
	 * 
	 * @since 1.0
	 * @param p Objeto persona a eliminar.
	 * @throws Exception
	 * 
	 */
	public void eliminaPostulante(Postulante p) throws Exception {
		log.info("Iniciando eliminaPErsona");
		try {
			log.info("Antes de transformar persona a entidad");
			PostulanteEntitie pe = this.transforma(p);
			log.info("Busca persona para eliminar");
			pe = entityManager.find(PostulanteEntitie.class, pe.getRut());
			log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(pe);
			this.entityManager.getTransaction().commit();
			log.info("Termina transaccion");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Clase encargada en actualziar a una persona
	 * 
	 * @since 1.0
	 * @param p Datos de objeto persona a actualizar en la base de datos.
	 * @throws Exception
	 * 
	 */
	public void actualizaPostulante(Postulante p) throws Exception {
		log.info("Iniciando actualzaPostulante");
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
	 * @param p Objeto persona a transformar en entidad
	 * @return entidad de tipo persona
	 */
	private PostulanteEntitie transforma(Postulante p) {
		PostulanteEntitie pe = new PostulanteEntitie();
		pe.setRut(p.getRut());
		pe.setCv(p.getCv());
		return pe;
	}

	/**
	 * Metodo que recive una entidad persona y lo transforma en su objeto
	 * 
	 * @since 1.0
	 * @param p Entidad persona a tranasforma.
	 * @return objeto persona.
	 */
	private Postulante transforma(PostulanteEntitie p) {
		Postulante pos = new Postulante();
		pos.setRut(p.getRut());
		pos.setCv(p.getCv());
		return pos;
	}
}
