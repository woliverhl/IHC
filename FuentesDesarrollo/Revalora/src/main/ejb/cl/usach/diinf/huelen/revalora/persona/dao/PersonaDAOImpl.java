package cl.usach.diinf.huelen.revalora.persona.dao;

import java.util.List;

import cl.usach.diinf.huelen.revalora.persona.dto.Persona;
import cl.usach.diinf.huelen.revalora.persona.entities.PersonaEntitie;


public interface PersonaDAOImpl {
	
	/**
	 * {@link PersonaDAO#insertaPersona(PersonaEntitie)}
	 * @param p
	 * @throws Exception 
	 */
	public void insertaPersona (Persona p) throws Exception;

	/**
	 * {@link PersonaDAO#obtenerPersonas()}
	 * @throws Exception 
	 */
	public List<Persona> obtenerPersonas() throws Exception;
	
	/**
	 * {@link PersonaDAO#eliminaPersona(Persona)}
	 * @param p
	 * @throws Exception
	 */
	public void eliminaPersona(Persona p) throws Exception;
	
	/**
	 * {@link PersonaDAO#actualizaPersona(Persona)}
	 * @param p
	 * @throws Exception
	 */
	public void actualizaPersona(Persona p) throws Exception;

	/**
	 * {@link PersonaDAO#obtenerPersonas(String)}
	 * @param p
	 * @throws Exception
	 */
	public Persona obtenerPersonas(String rut) throws Exception;
}
