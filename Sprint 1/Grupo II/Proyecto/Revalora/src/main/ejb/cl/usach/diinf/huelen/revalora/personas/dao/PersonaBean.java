package cl.usach.diinf.huelen.revalora.personas.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.usach.diinf.huelen.revalora.personas.dto.PersonaDTO;

/**
 * <p>
 * PersonaBean
 * </p>
 * 
 * Clase encargada de la logica de negocio correspondiente a las personas.
 * 
 * @author Pablo Gavilan
 * @updated Pablo Gavilan
 * @version 1.0
 * 
 */
@Stateless
public class PersonaBean {

	/**
	 * Logger de la clase
	 * 
	 * @author Pablo Gavilan
	 * @since 1.0
	 */
	Logger log = LoggerFactory.getLogger(PersonaBean.class);

	/**
	 * Objeto de conexion a la clase de acceso de datos para persona
	 * 
	 * @author Pablo Gavilan
	 * @since 1.0
	 */
	PersonaDAO dao;

	/**
	 * Constructor de la clase
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @since 1.0
	 */
	public PersonaBean() {
		this.log.info("Instancia EJB");
		this.dao = new PersonaDAOImpl();
	}

	/**
	 * Método encargado de ingresar a la base de datos una persona.
	 * 
	 * @author Pablo Gavilan
	 * @param p
	 *            Objeto persona ingresada a la base de datos.
	 * @throws Exception
	 *             generada al insertar una persona
	 * @version 1.0
	 * @since 1.0
	 */
	public void insertarPersoa(PersonaDTO p) throws Exception {
		try {
			this.log.info("Antes de insertar");
			this.dao.actualizaPersona(p);
			this.log.info("Despues de insertar");
		} catch (Exception e) {
			this.log.error("Error en insertarPersoa");
			this.log.error("Persona " + p.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de actualziar a la base de datos una persona.
	 * 
	 * @author Pablo Gavilan
	 * @param p
	 *            Objeto persona ingresada a la base de datos.
	 * @throws Exception
	 *             generada al actualizar una persona
	 * @version 1.0
	 * @since 1.0
	 */
	public void actualizaPersona(PersonaDTO p) throws Exception {
		try {
			this.log.info("Antes de actualizaPersona");
			this.dao.actualizaPersona(p);
			this.log.info("Despues de actualizaPersona");
		} catch (Exception e) {
			this.log.error("Error en actualizaPersona");
			this.log.error("Persona " + p.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de obtener una lista de todas las personas.
	 * 
	 * @author Pablo Gavilan
	 * @return Lista de personas
	 * @throws Exception
	 *             generada al listar personas
	 * @version 1.0
	 * @since 1.0
	 */
	public List<PersonaDTO> obtenerPersonas() throws Exception {
		try {
			this.log.info("Antes de obtenerPersonas");
			return this.dao.obtenerPersonas();
		} catch (Exception e) {
			this.log.error("Error en obtenerPersonas");
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de elimina en la base de datos una persona.
	 * 
	 * @author Pablo Gavilan
	 * @param p
	 *            Objeto persona que se elimina.
	 * @throws Exception
	 *             generada al eliminar una persona
	 * @version 1.0
	 * @since 1.0
	 */
	public void eliminaPersona(PersonaDTO p) throws Exception {
		try {
			this.log.info("Antes de eliminaPersona");
			this.dao.eliminaPersona(p);
			this.log.info("Despues de eliminaPersona");
		} catch (Exception e) {
			this.log.error("Error en eliminaPersona");
			this.log.error("Persona " + p.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de obtener una persona segun su rut.
	 * 
	 * @author Pablo Gavilan
	 * @param rut
	 *            , rut a retornar como objeto persona
	 * @return un objeto persona obtenido por su rut
	 * @throws Exception
	 *             generada al obtener una persona
	 * @version 1.0
	 * @since 1.0
	 */
	public PersonaDTO obtenerPersonas(String rut) throws Exception {
		try {
			this.log.info("Antes de obtenerPersonas");
			return this.dao.obtenerPersonas(rut);
		} catch (Exception e) {
			this.log.error("Error en obtenerPersonas");
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}

	}
}