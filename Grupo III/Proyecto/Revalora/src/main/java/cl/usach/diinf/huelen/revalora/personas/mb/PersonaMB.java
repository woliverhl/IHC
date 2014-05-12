package cl.usach.diinf.huelen.revalora.personas.mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.personas.dao.PersonaBean;
import cl.usach.diinf.huelen.revalora.personas.dao.PersonaDAO;
import cl.usach.diinf.huelen.revalora.personas.dto.Persona;

/**
 * Clase manageBean encargada de las acciones relacionadas con una persona
 * 
 * @author Pablo Gavilan
 * @version 1.0
 *
 */
@Named @RequestScoped public class PersonaMB {

    @EJB PersonaBean bean;
	/**
	 * Logger de la clase
	 */
	Logger log = Logger.getLogger(PersonaDAO.class);

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
	
	private List<Persona> personas;
	private List<Persona> personasGrupo; 
	int idgrupo;

	public PersonaMB() {   
		super();
	}

	@PostConstruct
	public void init(){
		try {
			log.info("antes de llamar bean.obtenerPersonas()");
			personas = bean.obtenerPersonas();
		} catch (Exception e) {
			log.error("Error al PersonaIDAO.obtenerPersonas():" + e);
			e.printStackTrace();
		}
	}
	
	
	
	public void setIdgrupo(int idgrupo) {
		this.idgrupo = idgrupo;
	}

	
	public List<Persona> obtenerPersonasPorIdGrupo(){
		try {
			log.info("antes de llamar bean.obtenerPersonas()");
			personasGrupo = bean.obtenerPersonasPorIdGrupo(idgrupo);
			return personasGrupo;
		} catch (Exception e) {
			log.error("Error al PersonaIDAO.obtenerPersonas():" + e);
			e.printStackTrace();
			return personasGrupo;
		}
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 * 
	 * @since 1.0
	 * @return 
	 */
    public String insertarPersona() {

    	log.info("inicio insertarPersoa");
    	try{
    		Persona p = this.getPersona();
    		bean.insertarPersoa(p);
    		log.info("termino insertarPersoa");
    	} catch(Exception e) {
    		log.error("Error " + e.getMessage());
    		log.error("Error " + e.getClass());
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

    	log.info("inicio actualizaPersona");
    	try{
    		Persona p = this.getPersona();    		
    		bean.actualizaPersona(p);
    		log.info("termino actualizaPersona");
    	} catch(Exception e) {
    		log.error("Error actualizaPersona");
    		log.error("Error " + e.getMessage());
    		log.error("Error " + e.getClass());
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
    public String eliminaPersona() {

    	log.info("inicio eliminaPersona");
    	try{
    		Persona p = this.getPersona();    		
    		bean.eliminaPersona(p);
    		log.info("termino eliminaPersona");
    	} catch(Exception e) {
    		log.error("Error eliminaPersona");
    		log.error("Error " + e.getMessage());
    		log.error("Error " + e.getClass());
    		return "error";
    	}
        return "success";
    }

    /**
     * Metodo encargado de obtener un objeto persona de los atributos de MB
     * 
     * @since 1.0 
     * @param p Objeto persona
     * @return
     */
	private Persona getPersona() {
		Persona pe = new Persona();
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

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
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

	public List<Persona> getPersonasGrupo() { 
		return this.personasGrupo;
	}

	public void setPersonasGrupo(List<Persona> personasGrupo) {
		this.personasGrupo = personasGrupo;
	}
	
	

}
