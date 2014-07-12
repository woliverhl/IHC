package cl.usach.diinf.revalora.persona.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase entidad que representa los registros de la tabla PERSONS.
 *
 * @author Pablo Gavilan
 * @version 1.0
 */
@Entity @Table(name="PERSONS")
@NamedQueries({
	@NamedQuery(name="Persona.findAll",  query="SELECT c FROM PersonaEntity c"),
	@NamedQuery(name="Persona.findCorreo", query = "SELECT c FROM PersonaEntity c WHERE c.id = :idgrupo")
})
public class PersonaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Nombre de la consulta para seleccionar todo
	 */
	public static final String SQL_SELECT_ALL = "Persona.findAll";
	public static final String SQL_SELECT_CORREO = "Persona.findCorreo";

	/**
	 * Rut del usuario corresponde al campo RUT de la tabla PERSONS
	 */
	@Id @Column(nullable=false, length=10, name="RUT") private String rut;

	/**
	 * id del usuario corresponde al campo ID de la tabla PERSONS
	 */

	@Column(unique=true, nullable=false, name="ID") private int id;

	/**
	 * Clave del usuario corresponde al campo PASSWORD de la tabla PERSONS
	 */
	@Column(unique=true, length=20, nullable=false, name="PASSWORD") private String clave;

	/**
	 * Nombres del usuario corresponde al campo FIRST_NAME de la tabla PERSONS
	 */
	@Column(unique=true, length=30, nullable=false, name="FIRST_NAME") private String nombre;

	/**
	 * Apellidos del usuario corresponde al campo LAST_NAME de la tabla PERSONS
	 */
	@Column(unique=true, length=30, nullable=false, name="LAST_NAME") private String apellido;

	/**
	 * Genero del usuario corresponde al campo GENDER de la tabla PERSONS
	 */
	@Column(unique=true, length=1, nullable=false, name="GENDER") private String genero;

	/**
	 * Fecha de nacimiento del usuario corresponde al campo BIRTH_DATE de la tabla PERSONS
	 */
	@Column(unique=true, nullable=false, name="BIRTH_DATE") private Date cumpleano;

	/**
	 * Direccion del usuario corresponde al campo ADDRESS de la tabla PERSONS
	 */
	@Column(unique=true, nullable=false, name="ADDRESS") private String direccion;

	/**
	 * Telefono del usuario corresponde al campo PHONE de la tabla PERSONS
	 */
	@Column(unique=true, nullable=false, name="PHONE") private int telefono;

	/**
	 * Correo electronico del usuario corresponde al campo E_MAIL de la tabla PERSONS
	 */
	@Column(nullable=false, length=256, name="E_MAIL") private String correo;

	/**
	 * Posicion del usuario corresponde al campo POSITION de la tabla PERSONS
	 */
	@Column(nullable=false, length=100, name="POSITION") private String posicion;

	/**
	 * Constructor de la clase
	 */
	public PersonaEntity() {
	}

	/**
	 * Metodo que retorna el rut del usuario
	 * @return rut del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getRut() {
		return this.rut;
	}

	/**
	 * Metodo que setea el rut del usuario
	 * @param rut a setear
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * Metodo que retorna el id del usuario
	 * @return campo id del objeto usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Metodo que setea el id del usuario
	 * @param id campo id del objeto usuario a setear
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metodo que recupera la posicion del usuario
	 * @return posision del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getPosicion() {
		return this.posicion;
	}

	/**
	 * Metodo que setea la posicion del usuario
	 * @param posicion a setear en el usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	/**
	 * Metodo que retorna el correo electronico del usuario
	 * @return correo del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getCorreo() {
		return this.correo;
	}

	/**
	 * Metodo que setea el correo electronico del usuario
	 * @param correo a setear en el objeto usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Metodo que obtiene el telefono del usuario
	 * @return telefono del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public int getTelefono() {
		return this.telefono;
	}

	/**
	 * Metodo que setea el telefono del usuario
	 * @param telefono a ingresar al objeto usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo encargado de retornar la direccion del usuario
	 * @return direccion del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getDireccion() {
		return this.direccion;
	}

	/**
	 * Metodo que setea la direccion del usuario
	 * @param direccion a setear en el objeto usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo encargado de retornar la fecha de nacimiento del usuario
	 * @return fecha de nacimiento del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public Date getCumpleano() {
		return this.cumpleano;
	}

	/**
	 * Metodo que setea la fecha de cumpleanos del usuario
	 * @param fecha de nacimiento del usuario a setear
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setCumpleano(Date cumpleano) {
		this.cumpleano = cumpleano;
	}

	/**
	 * Metodo que retorna el genero del usuario
	 * @return genero del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getGenero() {
		return this.genero;
	}

	/**
	 * Metodo que setea el genero del usuario
	 * @param genero a setear en el usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Metodo encargado de obtener los apellidos del usuario
	 * @return apellidos del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getApellido() {
		return this.apellido;
	}

	/**
	 * Metodo encargado de setear los apellidos del usuario
	 * @param apellido del usuario a setear
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Metodo encargado de obtener los nombres del usuario
	 * @return nombres del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Metodo encargado de setear los nombres del usuario
	 * @param nombre a setear en el usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo encargado de obtener la clave del usuario
	 * @return clave del usuario
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public String getClave() {
		return this.clave;
	}

	/**
	 * Metodo encargado de setear la clave del usuario
	 * @param clave del usuario a setear
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
}