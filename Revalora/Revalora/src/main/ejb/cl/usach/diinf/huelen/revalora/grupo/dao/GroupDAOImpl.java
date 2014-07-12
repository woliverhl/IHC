package cl.usach.diinf.huelen.revalora.grupo.dao;

import java.util.List;

import cl.usach.diinf.huelen.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.huelen.revalora.grupo.entities.GrupoEntity;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;


public interface GroupDAOImpl {

	/**
	 * {@link GrupoDAO#insertaUserGroup(GrupoEntity)}
	 * @param u
	 * @throws Exception
	 */
	public void insertaUserGroup (GrupoDTO u) throws Exception;

	/**
	 * {@link GrupoDAO#obtenerUserGroups()}
	 * @throws Exception
	 */
	public List<GrupoDTO> obtenerUserGroups() throws Exception;

	/**
	 * {@link GrupoDAO#eliminaUserGroup(GrupoDTO)}
	 * @param u
	 * @throws Exception
	 */
	public void eliminaUserGroup(GrupoDTO u) throws Exception;

	/**
	 * {@link GrupoDAO#actualizaUserGroup(GrupoDTO)}
	 * @param u
	 * @throws Exception
	 */
	public void actualizaUserGroup(GrupoDTO u) throws Exception;

	/**
	 * {@link GrupoDAO#obtenerUserGroups(int)}
	 * @param u
	 * @throws Exception
	 */
	public GrupoDTO obtenerUserGroups(int id) throws Exception;

	/**
	 * {@link GrupoDAO#obtenerPersonasPorGrupo(int)}
	 * @param u
	 * @throws Exception
	 */
	public List<PersonaDTO> obtenerPersonasPorGrupo(int id) throws Exception;

	public void agregarPersonasAlGrupo(int id, PersonaDTO dto) throws Exception;

	public int obtenerProximoIdGrupo();
}
