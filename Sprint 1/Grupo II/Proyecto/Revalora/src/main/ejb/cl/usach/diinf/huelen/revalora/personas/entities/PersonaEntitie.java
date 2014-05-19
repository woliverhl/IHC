package cl.usach.diinf.huelen.revalora.personas.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Clase entidad que representa los registros de la tabla PERSONS.
 * 
 * @author Pablo Gavilan
 * @version 1.0
 */
@Entity @Table(name="PERSONS")
@NamedQuery(name="Persona.findAll",  query="SELECT c FROM PersonaEntitie c")
public class PersonaEntitie implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String SQL_SELECT_ALL = "Persona.findAll";
	public static final String SQL_ELIMINA = "Persona.eliminar";

	@Id @Column(nullable=false, length=10, name="RUT") private String rut;
	@Column(unique=true, nullable=false, name="ID") private int id;
	@Column(unique=true, length=20, nullable=false, name="PASSWORD") private String clave;
	@Column(unique=true, length=30, nullable=false, name="FIRST_NAME") private String nombre;
	@Column(unique=true, length=30, nullable=false, name="LAST_NAME") private String apellido;
	@Column(unique=true, length=1, nullable=false, name="GENDER") private String genero;
	@Column(unique=true, nullable=false, name="BIRTH_DATE") private Date cumpleano;
	@Column(unique=true, nullable=false, name="ADDRESS") private String direccion;
	@Column(unique=true, nullable=false, name="PHONE") private int telefono;
	@Column(nullable=false, length=256, name="E_MAIL") private String correo;
	@Column(nullable=false, length=100, name="POSITION") private String posicion;

	public PersonaEntitie() {
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