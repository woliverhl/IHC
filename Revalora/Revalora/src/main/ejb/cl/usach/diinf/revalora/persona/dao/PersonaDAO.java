package cl.usach.diinf.revalora.persona.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.diinf.revalora.persona.entities.PersonaEntity;
import cl.usach.diinf.revalora.personas.util.PersonaUtil;

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
		this.log.info("Crea constructor");
		try{
			this.entityManager = Persistence.createEntityManagerFactory("revalora-pu").createEntityManager();
		} catch (Exception e) {
			this.log.error("Error " + e);
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

		this.log.info("Ingresa metodo");
		PersonaEntity pe = PersonaUtil.transforma(p);
		try {
			this.log.info("Inicia transacion");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(pe);
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transacion");
		}catch(Exception e) {
			this.log.error("Error " + e);
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
			this.log.info("Antes de llamar createNamedQuery");
			List<PersonaEntity> lpe = this.entityManager.createNamedQuery(PersonaEntity.SQL_SELECT_ALL, PersonaEntity.class).getResultList();

			return PersonaUtil.transforma(lpe);

		} catch(Exception e) {
			this.log.error("Error " + e);
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
		this.log.info("Iniciando eliminaPErsona");
		try {
			this.log.info("Antes de transformar persona a entidad");
			PersonaEntity pe = PersonaUtil.transforma(p);
			this.log.info("Busca persona para eliminar");
			pe = this.entityManager.find(PersonaEntity.class, pe.getRut());
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
	 * Metodo encargado de retornar un objeto persona buscado por su llave o rut
	 *
	 * @since 1.0
	 * @param rut rut a buscar.
	 * @throws Exception
	 *
	 */
	public PersonaDTO obtenerPersonas(String rut) throws Exception {
		this.log.info("Iniciando obtenerPersonas(String rut)");
		try {
			this.log.info("Busca persona para eliminar");
			PersonaEntity pe = this.entityManager.find(PersonaEntity.class, rut);
			this.log.info("Termina transaccion y retorna");
			return PersonaUtil.transforma(pe);
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
		this.log.info("Iniciando actualziaPersona");
		try {
			this.log.info("Busca persona para eliminar");
			PersonaEntity pe = this.entityManager.find(PersonaEntity.class, p.getRut());
			this.log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.log.info("Seteamos datos transaccion");
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
			this.log.info("Termino datos transaccion");
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transaccion");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}
	
	/**
	 * Metodo que retorna la lista de personas por id de grupo.
	 * @since 1.0
	 * @return Lista de objetos personas. 
	 */
	public List<PersonaDTO> obtenerPersonasPorIdGrupo(int idgrupo) throws Exception {

		try {
			log.info("Antes de llamar createNamedQuery");
			List<PersonaEntity> lpe = entityManager.createNamedQuery(PersonaEntity.SQL_SELECT_CORREO , PersonaEntity.class).setParameter("idgrupo", idgrupo) .getResultList();

			List<PersonaDTO> lp = new ArrayList<PersonaDTO>();

			for (PersonaEntity personaEntitie : lpe) {
				lp.add(PersonaUtil.transforma(personaEntitie));
			}

			return lp;
			
		} catch(Exception e) {
			log.error("Error " + e);
			throw e;
		}
	}
}
