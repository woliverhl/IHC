package cl.usach.diinf.revalora.persona.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa a un objeto persona.
 *
 * @author Pablo Gavilan
 *
 * @version 1.0
 */
public class PersonaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Rut de una persona
	 */
	private String rut;

	/**
	 * ID de una persona
	 */
	private int id;

	/**
	 * Clave de una persona
	 */
	String clave;

	/**
	 * Nombres de una persona
	 */
	String nombre;

	/**
	 * Apellidos de una persona
	 */
	String apellido;

	/**
	 * Sexo de una persona
	 */
	String genero;

	/**
	 * Fecha de nacimiento de una persona
	 */
	Date cumpleano;

	/**
	 * Direccion de una persona
	 */
	String direccion;

	/**
	 * Telefono de una persona
	 */
	private int telefono;

	/**
	 * Correo electronico de una persona
	 */
	private String correo;

	/**
	 * Posicion de una persona
	 */
	private String posicion;

	/**
	 * Tipo de persona
	 */
	private String tipoPersona;

	/**
	 * Indica si es experto
	 */
	private boolean experto;
	/**
	 * Constructor
	 */
	public PersonaDTO() {
	}

	/**
	 *
	 * @return Retorna el rut de la persona
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getRut() {
		return (this.rut!=null?this.rut:"");
	}

	/**
	 * Setea el rut de una persona
	 *
	 * @param rut
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * Retorna el id de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setea el ID de una persona
	 *
	 * @param id
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna la posicion de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getPosicion() {
		return this.posicion;
	}

	/**
	 * Setea la posicion de una persona
	 *
	 * @param posicion
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	/**
	 * Retorna el correo electronico de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getCorreo() {
		return this.correo;
	}

	/**
	 * Setea el correo electronico de una persona
	 *
	 * @param correo
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Retorna el telefono de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public int getTelefono() {
		return this.telefono;
	}

	/**
	 * Setea el telefono de una persona
	 *
	 * @param telefono
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Retorna la direccion de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getDireccion() {
		return this.direccion;
	}

	/**
	 * Setea la direccion de una persona
	 *
	 * @param direccion
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Retorna la fecha de nacimiento de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public Date getCumpleano() {
		return this.cumpleano;
	}

	/**
	 * Setea la fecha de nacimiento de una persona
	 *
	 * @param cumpleano
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setCumpleano(Date cumpleano) {
		this.cumpleano = cumpleano;
	}

	/**
	 * Retorna el genero de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getGenero() {
		return this.genero;
	}

	/**
	 * Setea el genero de una persona
	 *
	 * @param genero
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Retorna los apellidos de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getApellido() {
		return this.apellido;
	}

	/**
	 * Setea los apellidos de una persona
	 *
	 * @param apellido
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Obtiene los nombres de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Setea los nombres de una persona
	 *
	 * @param nombre
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la clave de una persona
	 *
	 * @return
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getClave() {
		return this.clave;
	}

	/**
	 * Setea la clave de una persona
	 *
	 * @param clave
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Metodo sobreescrito que transforma el objeto persona en texto
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Persona [rut=");
		builder.append(this.rut);
		builder.append(", id=");
		builder.append(this.id);
		builder.append(", clave=");
		builder.append(this.clave);
		builder.append(", nombre=");
		builder.append(this.nombre);
		builder.append(", apellido=");
		builder.append(this.apellido);
		builder.append(", genero=");
		builder.append(this.genero);
		builder.append(", cumpleano=");
		builder.append(this.cumpleano);
		builder.append(", direccion=");
		builder.append(this.direccion);
		builder.append(", telefono=");
		builder.append(this.telefono);
		builder.append(", correo=");
		builder.append(this.correo);
		builder.append(", posicion=");
		builder.append(this.posicion);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Metodo que retorna el campo tipo persona
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return this.tipoPersona;
	}

	/**
	 * Metodo que setea el campo tipo persona
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Metodo que retorna el campo experto
	 * @return the experto
	 */
	public boolean isExperto() {
		return this.experto;
	}

	/**
	 * Metodo que setea el campo experto
	 * @param experto the experto to set
	 */
	public void setExperto(boolean experto) {
		this.experto = experto;
	}
}