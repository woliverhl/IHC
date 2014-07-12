package cl.usach.diinf.huelen.revalora.grupo;

import java.util.List;

import javax.ejb.Remote;

import cl.usach.diinf.huelen.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;

@Remote
public interface GrupoImpl {

	/**
	 * @see GrupoBean#insertarUserGroup(GrupoDTO)
	 * @param u
	 * @throws Exception
	 */
	public void insertarUserGroup(GrupoDTO u) throws Exception;

	/**
	 * @see GrupoBean#actualizaUserGroup(GrupoDTO)
	 * @param u
	 * @throws Exception
	 */
	public void actualizaUserGroup(GrupoDTO u) throws Exception;

	/**
	 * @see GrupoBean#obtenerUserGroups()
	 * @return
	 * @throws Exception
	 */
	public List<GrupoDTO> obtenerUserGroups() throws Exception;

	/**
	 * @see GrupoBean#obtenerUserGroups(int)
	 * @return
	 * @throws Exception
	 */
	public GrupoDTO obtenerUserGroups(int id) throws Exception;

	/**
	 * @see GrupoBean#eliminaUserGroup(GrupoDTO)
	 * @param u
	 * @throws Exception
	 */
	public void eliminaUserGroup(GrupoDTO u) throws Exception;

	public void agregarPersonasAlGrupo(int id,
			List<PersonaDTO> personasSelecccionadas) throws Exception;

	public void agregarPersonasAlGrupo(int id, PersonaDTO dto) throws Exception;

	public int obtenerProximoIdGrupo() ;
}


