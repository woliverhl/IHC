package cl.usach.diinf.huelen.revalora.persona.dao;

import java.util.List;

import cl.usach.diinf.huelen.revalora.persona.dto.Postulante;


public interface PostulanteDAOImpl {
	
	/**
	 * {@link PostulanteDAO#insertaPersona(PostulanteEntitie)}
	 * @param p
	 * @throws Exception 
	 */
	public void insertaPostulante(Postulante p) throws Exception;

	/**
	 * {@link PostulanteDAO#obtenerPersonas()}
	 * @throws Exception 
	 */
	public List<Postulante> obtenerPostulantes() throws Exception;
	
	
	
	/**
	 * {@link PostulanteDAO#actualizaPostulante(PostulanteEntitie)}
	 * @param p
	 * @throws Exception
	 */
	public void actualizaPostulante(Postulante p) throws Exception;
}
