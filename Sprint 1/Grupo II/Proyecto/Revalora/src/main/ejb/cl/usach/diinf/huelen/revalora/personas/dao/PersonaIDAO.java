package cl.usach.diinf.huelen.revalora.personas.dao;

import java.util.List;

import cl.usach.diinf.huelen.revalora.personas.dto.Persona;
import cl.usach.diinf.huelen.revalora.personas.entities.PersonaEntitie;


public interface PersonaIDAO {
	
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
