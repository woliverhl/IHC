package cl.usach.diinf.revalora.persona.dao;

import java.util.List;

import cl.usach.diinf.revalora.persona.dto.PostulanteDTO;


public interface PostulanteDAOImpl {
	
	/**
	 * {@link PostulanteDAO#insertaPersona(PostulanteEntitie)}
	 * @param p
	 * @throws Exception 
	 */
	public void insertaPostulante(PostulanteDTO p) throws Exception;

	/**
	 * {@link PostulanteDAO#obtenerPersonas()}
	 * @throws Exception 
	 */
	public List<PostulanteDTO> obtenerPostulantes() throws Exception;
	
	
	
	/**
	 * {@link PostulanteDAO#actualizaPostulante(PostulanteEntitie)}
	 * @param p
	 * @throws Exception
	 */
	public void actualizaPostulante(PostulanteDTO p) throws Exception;
}
