package cl.usach.diinf.huelen.revalora.persona;

import java.util.List;

import javax.ejb.Remote;
import cl.usach.diinf.huelen.revalora.persona.dto.Persona;

/**
 * <p>UsuarioEJB</p>
 * 
 * Clase encargada de la logica de negocio correspondiente a los usuarios.
 * 
 * @author Pablo Gavilan
 * @version 1.0
 *
 */
@Remote 
public interface PersonaImpl{

	/**
	 * @see PersonaBean#insertarPersoa(Persona)
	 * @param p
	 * @throws Exception
	 */
	public void insertarPersoa(Persona p) throws Exception;

	/**
	 * @see PersonaBean#actualizaPersona(Persona)
	 * @param p
	 * @throws Exception
	 */
	public void actualizaPersona(Persona p) throws Exception;
	
	/**
	 * @see PersonaBean#obtenerPersonas(Persona)
	 * @return
	 * @throws Exception
	 */
	public List<Persona> obtenerPersonas() throws Exception;

	/**
	 * @see PersonaBean#eliminaPersona(Persona)
	 * @param p
	 * @throws Exception
	 */
	public void eliminaPersona(Persona p) throws Exception;

	/**
	 * @see PersonaBean#obtenerPersonas(Persona)
	 * @param rut
	 * @return
	 * @throws Exception
	 */
	public Persona obtenerPersonas(String rut) throws Exception;
}