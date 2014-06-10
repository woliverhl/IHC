package cl.usach.diinf.revalora.persona.dao;

import java.util.List;

import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.diinf.revalora.persona.entities.PersonaEntity;


public interface PersonaDAOImpl {
	
	/**
	 * {@link PersonaDAO#insertaPersona(PersonaEntity)}
	 * @param p
	 * @throws Exception 
	 */
	public void insertaPersona (PersonaDTO p) throws Exception;

	/**
	 * {@link PersonaDAO#obtenerPersonas()}
	 * @throws Exception 
	 */
	public List<PersonaDTO> obtenerPersonas() throws Exception;
	
	/**
	 * {@link PersonaDAO#eliminaPersona(PersonaDTO)}
	 * @param p
	 * @throws Exception
	 */
	public void eliminaPersona(PersonaDTO p) throws Exception;
	
	/**
	 * {@link PersonaDAO#actualizaPersona(PersonaDTO)}
	 * @param p
	 * @throws Exception
	 */
	public void actualizaPersona(PersonaDTO p) throws Exception;

	/**
	 * {@link PersonaDAO#obtenerPersonas(String)}
	 * @param p
	 * @throws Exception
	 */
	public PersonaDTO obtenerPersonas(String rut) throws Exception;
}
