package cl.usach.diinf.huelen.revalora.grupo.dao;

import java.util.List;

import cl.usach.diinf.huelen.revalora.grupo.dto.Grupo;
import cl.usach.diinf.huelen.revalora.grupo.entities.GrupoEntitie;


public interface GroupDAOImpl {
	
	/**
	 * {@link GrupoDAO#insertaUserGroup(GrupoEntitie)}
	 * @param u
	 * @throws Exception 
	 */
	public void insertaUserGroup (Grupo u) throws Exception;

	/**
	 * {@link GrupoDAO#obtenerUserGroups()}
	 * @throws Exception 
	 */
	public List<Grupo> obtenerUserGroups() throws Exception;
	
	/**
	 * {@link GrupoDAO#eliminaUserGroup(Grupo)}
	 * @param u
	 * @throws Exception
	 */
	public void eliminaUserGroup(Grupo u) throws Exception;
	
	/**
	 * {@link GrupoDAO#actualizaUserGroup(Grupo)}
	 * @param u
	 * @throws Exception
	 */
	public void actualizaUserGroup(Grupo u) throws Exception;
}
