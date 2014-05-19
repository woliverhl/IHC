package cl.usach.diinf.huelen.revalora.personas.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa a un objeto persona.
 * 
 * @author Pablo Gavilan
 * @version 1.0
 */
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

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

	public Persona() {
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

	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getCumpleano() {
		return this.cumpleano;
	}

	public void setCumpleano(Date cumpleano) {
		this.cumpleano = cumpleano;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}