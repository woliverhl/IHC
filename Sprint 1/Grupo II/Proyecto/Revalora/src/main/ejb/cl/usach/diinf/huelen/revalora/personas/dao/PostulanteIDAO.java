package cl.usach.diinf.huelen.revalora.personas.dao;

import java.util.List;
import cl.usach.diinf.huelen.revalora.personas.dto.Postulante;


public interface PostulanteIDAO {
	
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
