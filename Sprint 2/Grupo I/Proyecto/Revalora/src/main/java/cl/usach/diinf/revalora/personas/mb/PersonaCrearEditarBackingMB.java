package cl.usach.diinf.revalora.personas.mb;

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
public class PersonaCrearEditarBackingMB {

	/**
	 * EJB que llama a todos los métodos encargados de la clase persona
	 */
	@EJB
	PersonaImpl bean;

	/**
	 * Logger de la clase
	 */
	Logger log = Logger.getLogger(PersonaCrearEditarBackingMB.class);

	/**
	 * Objeto persona utilizado por el managebean
	 */
	private PersonaDTO dto;

	/**
	 * Tabla de parametros para persona
	 */
	private static final String TABLA_PARAMETROS = "persona.parametros";

	/**
	 * Constructor de la clase
	 */
	public PersonaCrearEditarBackingMB() {
		super();
		this.dto = new PersonaDTO();
	}

	/**
	 * Pos constructor de la clase
	 *
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	@PostConstruct
	public void init() {
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 *
	 * @since 1.0
	 * @return
	 */
	public String insertarPersona() {

		this.log.info("inicio insertarPersoa");
		try {
			this.bean.insertarPersoa(this.dto);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "okInserta");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			this.log.info("termino insertarPersoa");
		} catch (Exception e) {
			this.log.error("Error " + e.getMessage());
			this.log.error("Error " + e.getClass());
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "nokInserta");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
			return "error";
		}
		return "success";
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 *
	 * @since 1.0
	 * @return
	 */
	public String actualizaPersona() {

		this.log.info("inicio actualizaPersona");
		try {
			this.bean.actualizaPersona(this.dto);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "okActualiza");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			this.log.info("termino actualizaPersona");
		} catch (Exception e) {
			this.log.error("Error actualizaPersona");
			this.log.error("Error " + e.getMessage());
			this.log.error("Error " + e.getClass());
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "nokActualiza");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
			return "error";
		}
		return "success";
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 *
	 * @since 1.0
	 * @return
	 */
	public String obtenerPersona(String rut) {

		if (rut != null && "".compareTo(rut) != 0) {
			try {
				this.dto = this.bean.obtenerPersonas(rut);
			} catch (Exception e) {
				this.log.error("Error al bean.obtenerPersonas(" + this.dto.getRut() + "):" + e);
				e.printStackTrace();
				String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "nokObtener");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
			}
		}
		return "actualiza";
	}

	/**
	 * Metodo que retorna el campo dto
	 * @return the dto
	 */
	public PersonaDTO getDto() {
		return this.dto;
	}

	/**
	 * Metodo que setea el campo dto
	 * @param dto the dto to set
	 */
	public void setDto(PersonaDTO dto) {
		this.dto = dto;
	}
}
