package cl.usach.diinf.huelen.revalora.grupo;

import java.util.List;

import javax.ejb.Remote;

import cl.usach.diinf.huelen.revalora.grupo.dto.Grupo;

@Remote 
public interface GrupoImpl {

	/**
	 * @see GrupoBean#insertarUserGroup(Grupo)
	 * @param u
	 * @throws Exception
	 */
	public void insertarUserGroup(Grupo u) throws Exception;
	
	/**
	 * @see GrupoBean#actualizaUserGroup(Grupo)
	 * @param u
	 * @throws Exception
	 */
	public void actualizaUserGroup(Grupo u) throws Exception;

	/**
	 * @see GrupoBean#obtenerUserGroups(Grupo)
	 * @return
	 * @throws Exception
	 */
	public List<Grupo> obtenerUserGroups() throws Exception;

	/**
	 * @see GrupoBean#eliminaUserGroup(Grupo)
	 * @param u
	 * @throws Exception
	 */
	public void eliminaUserGroup(Grupo u) throws Exception;
	
}
