package cl.usach.diinf.revalora.personas.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.usach.diinf.revalora.persona.PersonaImpl;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.infraestructura.util.TablaValores;

/**
 * Clase manageBean encargada de las acciones relacionadas con una persona
 *
 * @author Pablo Gavilan
 * @version 1.0
 *
 */
@Named
@RequestScoped
public class PersonaListarBackingMB {

	/**
	 * EJB que llama a todos los métodos encargados de la clase persona
	 */
	@EJB
	PersonaImpl bean;

	/**
	 * Logger de la clase
	 */
	Logger log = Logger.getLogger(PersonaListarBackingMB.class);

	/**
	 * Objeto lista de personas
	 */
	private List<PersonaDTO> personas;

	/**
	 * Objeto persona
	 */
	private PersonaDTO persona;

	/**
	 * Tabla de parametros para persona
	 */
	private static final String TABLA_PARAMETROS = "persona.parametros";

	/**
	 * Constructor de la clase
	 */
	public PersonaListarBackingMB() {
		super();
	}

	/**
	 * Metodo que inicialista la carga de la lista de personas
	 *
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	@PostConstruct
	public void init() {
		try {
			this.log.info("antes de llamar bean.obtenerPersonas()");
			this.personas = this.bean.obtenerPersonas();
		} catch (Exception e) {
			this.log.error("Error al PersonaIDAO.obtenerPersonas():" + e);
			e.printStackTrace();
		}
	}


	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 *
	 * @since 1.0
	 * @return
	 */
	public String eliminaPersona() {

		this.log.info("inicio eliminaPersona");
		if (this.persona != null && "".compareTo(this.persona.getRut()) != 0) {
			try {
				PersonaDTO p = this.bean.obtenerPersonas(this.persona.getRut());
				this.bean.eliminaPersona(p);
				this.log.info("termino eliminaPersona");
				String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "okElimina");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			} catch (Exception e) {
				this.log.error("Error eliminaPersona");
				this.log.error("Error " + e.getMessage());
				this.log.error("Error " + e.getClass());
				String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "nokElimina");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
				return "error";
			}
		}
		this.init();
		return "success";
	}

	/**
	 *
	 * @return Lista de personas
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public List<PersonaDTO> getPersonas() {
		return this.personas;
	}

	/**
	 *
	 * @param Una lista de personas
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setPersonas(List<PersonaDTO> personas) {
		this.personas = personas;
	}

	/**
	 *
	 * @return objeto persona a eliminar
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public PersonaDTO getPersona() {
		return this.persona;
	}

	/**
	 * Setea persona a eliminar
	 *
	 * @param persona
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}

}
