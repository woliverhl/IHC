package cl.usach.diinf.revalora.persona.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.diinf.revalora.persona.entities.PersonaEntity;

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
public class PersonaDAO implements PersonaDAOImpl {

	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	Logger log = Logger.getLogger(PersonaDAO.class);

	/**
	 * Objeto encargado de la conexion por jpa a la capa de datos
	 * @since 1.0
	 */
	private EntityManager entityManager;

	/**
	 * Constructor de la clase que instancia al #entityManager
	 * @since 1.0
	 */
	public PersonaDAO() {
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
	public void insertaPersona (PersonaDTO p) throws Exception {

		log.info("Ingresa metodo");
		PersonaEntity pe = this.transforma(p);
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
	public List<PersonaDTO> obtenerPersonas() throws Exception {

		try {
			log.info("Antes de llamar createNamedQuery");
			List<PersonaEntity> lpe = entityManager.createNamedQuery(PersonaEntity.SQL_SELECT_ALL, PersonaEntity.class).getResultList();

			List<PersonaDTO> lp = new ArrayList<PersonaDTO>();

			for (PersonaEntity personaEntitie : lpe) {
				lp.add(transforma(personaEntitie));
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
	public void eliminaPersona(PersonaDTO p) throws Exception {
		log.info("Iniciando eliminaPErsona");
		try {
			log.info("Antes de transformar persona a entidad");
			PersonaEntity pe = this.transforma(p);
			log.info("Busca persona para eliminar");
			pe = entityManager.find(PersonaEntity.class, pe.getRut());
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
	 * Metodo encargado de retornar un objeto persona buscado por su llave o rut
	 * 
	 * @since 1.0
	 * @param rut rut a buscar.
	 * @throws Exception
	 * 
	 */
	public PersonaDTO obtenerPersonas(String rut) throws Exception {
		log.info("Iniciando obtenerPersonas(String rut)");
		try {
			log.info("Busca persona para eliminar");
			PersonaEntity pe = entityManager.find(PersonaEntity.class, rut);
			log.info("Termina transaccion y retorna");
			return this.transforma(pe);
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
	public void actualizaPersona(PersonaDTO p) throws Exception {
		log.info("Iniciando actualziaPersona");
		try {
			log.info("Busca persona para eliminar");
			PersonaEntity pe = entityManager.find(PersonaEntity.class, p.getRut());
			log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			log.info("Seteamos datos transaccion");
			pe.setApellido(p.getApellido());
			pe.setApellido(p.getApellido());
			pe.setClave(p.getClave());
			pe.setCorreo(p.getCorreo());
			pe.setCumpleano(p.getCumpleano());
			pe.setDireccion(p.getDireccion());
			pe.setGenero(p.getGenero());
			pe.setNombre(p.getNombre());
			pe.setPosicion(p.getPosicion());
			pe.setRut(p.getRut());
			pe.setTelefono(p.getTelefono());
			log.info("Termino datos transaccion");
			this.entityManager.getTransaction().commit();
			log.info("Termina transaccion");
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
	private PersonaEntity transforma(PersonaDTO p) {
		PersonaEntity pe = new PersonaEntity();
		pe.setApellido(p.getApellido());
		pe.setClave(p.getClave());
		pe.setCorreo(p.getCorreo());
		pe.setCumpleano(p.getCumpleano());
		pe.setDireccion(p.getDireccion());
		pe.setGenero(p.getGenero());
		pe.setId(p.getId());
		pe.setNombre(p.getNombre());
		pe.setPosicion(p.getPosicion());
		pe.setRut(p.getRut());
		pe.setTelefono(p.getTelefono());
		return pe;
	}

	/**
	 * Metodo que recive una entidad persona y lo transforma en su objeto
	 * 
	 * @since 1.0
	 * @param p Entidad persona a tranasforma.
	 * @return objeto persona.
	 */
	private PersonaDTO transforma(PersonaEntity p) {
		PersonaDTO pe = new PersonaDTO();
		pe.setApellido(p.getApellido());
		pe.setClave(p.getClave());
		pe.setCorreo(p.getCorreo());
		pe.setCumpleano(p.getCumpleano());
		pe.setDireccion(p.getDireccion());
		pe.setGenero(p.getGenero());
		pe.setId(p.getId());
		pe.setNombre(p.getNombre());
		pe.setPosicion(p.getPosicion());
		pe.setRut(p.getRut());
		pe.setTelefono(p.getTelefono());
		return pe;
	}
}
