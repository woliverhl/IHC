package cl.usach.diinf.huelen.revalora.personas.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.usach.diinf.huelen.revalora.personas.dto.Persona;

/**
 * <p>UsuarioEJB</p>
 * 
 * Clase encargada de la logica de negocio correspondiente a los usuarios.
 * 
 * @author Pablo Gavilan
 * @version 1.0
 *
 */
@Stateless public class PersonaBean{

	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	Logger log = LoggerFactory.getLogger(PersonaBean.class);

	/**
	 * Objeto de conexion a la clase de acceso de datos para persona
	 * @since 1.0
	 */
	PersonaIDAO dao;

	/**
	 * Constructor de la clase
	 * @since 1.0
	 */
	public PersonaBean() {
		log.info("Instancia EJB");
		dao = new PersonaDAO();
	}

	/**
	 * Método encargado de ingresar a la base de datos una persona.
	 * 
	 * @param p Objeto persona ingresada a la base de datos.
	 * @throws Exception 
	 * @since 1.0
	 */
	public void insertarPersoa(Persona p) throws Exception {
		try {
			log.info("Antes de insertar");
			dao.insertaPersona(p);
			log.info("Despues de insertar");
		} catch (Exception e) {
			log.error("Error en insertarPersoa");
			log.error("Persona " + p.toString());
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de actualziar a la base de datos una persona.
	 * 
	 * @param p Objeto persona ingresada a la base de datos.
	 * @throws Exception 
	 * @since 1.0
 */
	public void actualizaPersona(Persona p) throws Exception {
		try {
			log.info("Antes de actualizaPersona");
			dao.actualizaPersona(p);
			log.info("Despues de actualizaPersona");
		} catch (Exception e) {
			log.error("Error en actualizaPersona");
			log.error("Persona " + p.toString());
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de obtener una lista de todas las personas.
	 * 
	 * @return 
	 * @throws Exception 
	 * @since 1.0
	 */
	public List<Persona> obtenerPersonas() throws Exception {
		try {
			log.info("Antes de obtenerPersonas");
			return dao.obtenerPersonas();
		} catch (Exception e) {
			log.error("Error en obtenerPersonas");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de elimina en la base de datos una persona.
	 * 
	 * @param p Objeto persona que se elimina.
	 * @throws Exception 
	 * @since 1.0
	 */
	public void eliminaPersona(Persona p) throws Exception {
		try {
			log.info("Antes de eliminaPersona");
			dao.eliminaPersona(p);
			log.info("Despues de eliminaPersona");
		} catch (Exception e) {
			log.error("Error en eliminaPersona");
			log.error("Persona " + p.toString());
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de obtener una lista de todas las personas.
	 * 
	 * @param rut, rut a retornar como objeto persona
	 * @return 
	 * @throws Exception 
	 * @since 1.0
	 */
	public Persona obtenerPersonas(String rut) throws Exception {
		try {
			log.info("Antes de obtenerPersonas");
			return dao.obtenerPersonas(rut);
		} catch (Exception e) {
			log.error("Error en obtenerPersonas");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getCause());
			log.error("Error:" + e.getClass());
			throw e;
		}

	}
}