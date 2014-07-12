package cl.usach.diinf.revalora.persona;

import java.util.List;

import javax.ejb.Remote;

import cl.usach.diinf.revalora.persona.dto.PersonaDTO;

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
	 * @see PersonaBean#insertarPersoa(PersonaDTO)
	 * @param p
	 * @throws Exception
	 */
	public void insertarPersoa(PersonaDTO p) throws Exception;

	/**
	 * @see PersonaBean#actualizaPersona(PersonaDTO)
	 * @param p
	 * @throws Exception
	 */
	public void actualizaPersona(PersonaDTO p) throws Exception;
	
	/**
	 * @see PersonaBean#obtenerPersonas(PersonaDTO)
	 * @return
	 * @throws Exception
	 */
	public List<PersonaDTO> obtenerPersonas() throws Exception;

	/**
	 * @see PersonaBean#eliminaPersona(PersonaDTO)
	 * @param p
	 * @throws Exception
	 */
	public void eliminaPersona(PersonaDTO p) throws Exception;

	/**
	 * @see PersonaBean#obtenerPersonas(PersonaDTO)
	 * @param rut
	 * @return
	 * @throws Exception
	 */
	public PersonaDTO obtenerPersonas(String rut) throws Exception;
}