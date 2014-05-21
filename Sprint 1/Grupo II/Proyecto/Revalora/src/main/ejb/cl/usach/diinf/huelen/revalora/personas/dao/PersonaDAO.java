package cl.usach.diinf.huelen.revalora.personas.dao;

import java.util.List;

import cl.usach.diinf.huelen.revalora.personas.dto.PersonaDTO;

/**
 * Interface de la clase PersonaDAO
 * 
 * @author Pablo Gavilan May 14, 2014
 * @version 1.0
 * @updated Pablo Gavilan
 */
public interface PersonaDAO {

	/**
	 * {@link PersonaDAOImpl#obtenerPersonas()}
	 * 
	 * @throws Exception
	 */
	public List<PersonaDTO> obtenerPersonas() throws Exception;

	/**
	 * {@link PersonaDAOImpl#eliminaPersona(PersonaDTO)}
	 * 
	 * @param p
	 * @throws Exception
	 */
	public void eliminaPersona(PersonaDTO p) throws Exception;

	/**
	 * {@link PersonaDAOImpl#actualizaPersona(PersonaDTO)}
	 * 
	 * @param p
	 * @throws Exception
	 */
	public void actualizaPersona(PersonaDTO p) throws Exception;

	/**
	 * {@link PersonaDAOImpl#obtenerPersonas(String)}
	 * 
	 * @param p
	 * @throws Exception
	 */
	public PersonaDTO obtenerPersonas(String rut) throws Exception;
}
