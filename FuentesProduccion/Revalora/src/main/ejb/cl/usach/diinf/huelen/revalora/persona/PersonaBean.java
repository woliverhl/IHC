package cl.usach.diinf.huelen.revalora.persona;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.persona.dao.PersonaDAO;
import cl.usach.diinf.huelen.revalora.persona.dao.PersonaDAOImpl;
import cl.usach.diinf.huelen.revalora.persona.dto.Persona;

/**
 * <p>
 * UsuarioEJB
 * </p>
 * 
 * Clase encargada de la logica de negocio correspondiente a los usuarios.
 * 
 * @author Pablo Gavilan
 * @version 1.0
 * 
 */
@Stateless
public class PersonaBean implements PersonaImpl {

	/**
	 * Logger de la clase
	 * 
	 * @since 1.0
	 */
	Logger log = Logger.getLogger(PersonaBean.class);

	/**
	 * Objeto de conexion a la clase de acceso de datos para persona
	 * 
	 * @since 1.0
	 */
	PersonaDAOImpl dao;

	/**
	 * Constructor de la clase
	 * 
	 * @since 1.0
	 */
	public PersonaBean() {
		this.log.info("Instancia EJB");
		this.dao = new PersonaDAO();
	}

	/**
	 * Método encargado de ingresar a la base de datos una persona.
	 * 
	 * @param p
	 *            Objeto persona ingresada a la base de datos.
	 * @throws Exception
	 * @since 1.0
	 */
	public void insertarPersoa(Persona p) throws Exception {
		try {
			this.log.info("Antes de insertar");
			this.dao.insertaPersona(p);
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
	 * @param p
	 *            Objeto persona ingresada a la base de datos.
	 * @throws Exception
	 * @since 1.0
	 */
	public void actualizaPersona(Persona p) throws Exception {
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
	 * @return
	 * @throws Exception
	 * @since 1.0
	 */
	public List<Persona> obtenerPersonas() throws Exception {
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
	 * @param p
	 *            Objeto persona que se elimina.
	 * @throws Exception
	 * @since 1.0
	 */
	public void eliminaPersona(Persona p) throws Exception {
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
	 * Método encargado de obtener una lista de todas las personas.
	 * 
	 * @param rut
	 *            , rut a retornar como objeto persona
	 * @return
	 * @throws Exception
	 * @since 1.0
	 */
	public Persona obtenerPersonas(String rut) throws Exception {
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