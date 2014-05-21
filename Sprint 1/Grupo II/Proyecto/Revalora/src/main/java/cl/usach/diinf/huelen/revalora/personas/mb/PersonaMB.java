package cl.usach.diinf.huelen.revalora.personas.mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.personas.dao.PersonaBean;
import cl.usach.diinf.huelen.revalora.personas.dto.PersonaDTO;

/**
 * Clase manageBean encargada de las acciones relacionadas con una persona
 * 
 * @author Pablo Gavilan
 * @updated Pablo Gavilan
 * @version 1.0
 * 
 */
@Named
@RequestScoped
public class PersonaMB {

	@EJB
	PersonaBean bean;
	/**
	 * Logger de la clase
	 */
	Logger log = Logger.getLogger(PersonaMB.class);

	private String rut;
	private int id;
	String clave;
	String nombre;
	String apellido;
	String genero;
	Date cumpleano;
	String direccion;
	private int telefono;
	private String correo;
	private String posicion;
	private String tipoPersona;
	private boolean experto;

	private List<PersonaDTO> personas;

	private PersonaDTO dto;

	public PersonaMB() {
		super();
	}

	/**
	 * @author Pablo Gavilan
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
	 * Metodo encargado de recargar la pagina.
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @return Palabra clave que retorna a la pagina segun faces-config
	 * @since 1.0
	 */
	public String recarga() {
		this.init();
		return "recarga";
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @return Palabra clave que retorna a la pagina segun faces-config
	 * @since 1.0
	 */
	public String insertarPersona() {

		this.log.info("inicio insertarPersoa");
		try {
			PersonaDTO p = this.getPersona();
			this.bean.insertarPersoa(p);
			this.log.info("termino insertarPersoa");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Usuario " + this.rut + " " + this.nombre
							+ " " + this.apellido + " creado!"));
		} catch (Exception e) {
			this.log.error("Error " + e.getMessage());
			this.log.error("Error " + e.getClass());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario "
							+ this.rut + " " + this.nombre + " "
							+ this.apellido + " no creado :(", e.getMessage()));

			return "error";
		}
		return "success";
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @return Palabra clave que retorna a la pagina segun faces-config
	 * @since 1.0
	 */
	public String actualizaPersona() {

		this.log.info("inicio actualizaPersona");
		try {
			PersonaDTO p = this.getPersona();
			this.bean.actualizaPersona(p);
			this.log.info("termino actualizaPersona");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("usuario " + this.rut + " " + this.nombre
							+ " " + this.apellido + " actualizado!"));
		} catch (Exception e) {
			this.log.error("Error actualizaPersona");
			this.log.error("Error " + e.getMessage());
			this.log.error("Error " + e.getClass());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario "
							+ this.rut + " " + this.nombre + " "
							+ this.apellido + " no actualizado :(", e
							.getMessage()));
			return "error";
		}
		this.init();
		return "success";
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @return Palabra clave que retorna a la pagina segun faces-config
	 * @since 1.0
	 */
	public String obtenerPersona(String rut) {

		if (rut != null && "".compareTo(rut) != 0) {
			PersonaDTO p;
			try {
				p = this.bean.obtenerPersonas(rut);
				this.rut = p.getRut();
				this.id = p.getId();
				this.clave = p.getClave();
				this.nombre = p.getNombre();
				this.apellido = p.getApellido();
				this.genero = p.getGenero();
				this.cumpleano = p.getCumpleano();
				this.direccion = p.getDireccion();
				this.telefono = p.getTelefono();
				this.correo = p.getCorreo();
				this.posicion = p.getPosicion();
			} catch (Exception e) {
				this.log.error("Error al bean.obtenerPersonas(" + this.rut
						+ "):" + e);
				e.printStackTrace();
			}
		}
		return "actualiza";
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @return Palabra clave que retorna a la pagina segun faces-config
	 * @since 1.0
	 */
	public void eliminaPersona(ActionEvent event) {

		this.log.info("inicio eliminaPersona " + this.rut);

		if (this.dto != null) {
			this.rut = this.dto.getRut();
		}

		if (this.rut != null && "".compareTo(this.rut) != 0) {
			try {
				PersonaDTO p = this.bean.obtenerPersonas(this.rut);
				this.bean.eliminaPersona(p);
				this.log.info("termino eliminaPersona");
			} catch (Exception e) {
				this.log.error("Error eliminaPersona");
				this.log.error("Error " + e.getMessage());
				this.log.error("Error " + e.getClass());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Usuario " + this.rut + " " + this.nombre + " "
										+ this.apellido + " no eliminado :(", e
										.getMessage()));
			}
		}
	}

	/**
	 * Metodo encargado de obtener un objeto persona de los atributos de MB
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @return objeto persona segun los atributos del formulario
	 * @since 1.0
	 */
	private PersonaDTO getPersona() {
		PersonaDTO pe = new PersonaDTO();
		pe.setApellido(this.apellido);
		pe.setClave(this.clave);
		pe.setCorreo(this.correo);
		pe.setCumpleano(this.cumpleano);
		pe.setDireccion(this.direccion);
		pe.setGenero(this.genero);
		pe.setId(this.id);
		pe.setNombre(this.nombre);
		pe.setPosicion(this.posicion);
		pe.setRut(this.rut);
		pe.setTelefono(this.telefono);
		return pe;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClave() {
		return this.clave;
	}

	public List<PersonaDTO> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<PersonaDTO> personas) {
		this.personas = personas;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getCumpleano() {
		return this.cumpleano;
	}

	public void setCumpleano(Date cumpleano) {
		this.cumpleano = cumpleano;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getTipoPersona() {
		return this.tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public boolean isExperto() {
		return this.experto;
	}

	public void setExperto(boolean experto) {
		this.experto = experto;
	}

	public PersonaDTO getDto() {
		return this.dto;
	}

	public void setDto(PersonaDTO dto) {
		this.dto = dto;
	}

}
