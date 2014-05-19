package cl.usach.diinf.huelen.revalora.usergroup.dao;

import java.util.List;

import cl.usach.diinf.huelen.revalora.usergroup.dto.UserGroup;
import cl.usach.diinf.huelen.revalora.usergroup.entities.UserGroupEntitie;


public interface UserGroupIDAO {
	
	/**
	 * {@link UserGroupDAO#insertaUserGroup(UserGroupEntitie)}
	 * @param u
	 * @throws Exception 
	 */
	public void insertaUserGroup (UserGroup u) throws Exception;

	/**
	 * {@link UserGroupDAO#obtenerUserGroups()}
	 * @throws Exception 
	 */
	public List<UserGroup> obtenerUserGroups() throws Exception;
	
	/**
	 * {@link UserGroupDAO#eliminaUserGroup(UserGroup)}
	 * @param u
	 * @throws Exception
	 */
	public void eliminaUserGroup(UserGroup u) throws Exception;
	
	/**
	 * {@link UserGroupDAO#actualizaUserGroup(UserGroup)}
	 * @param u
	 * @throws Exception
	 */
	public void actualizaUserGroup(UserGroup u) throws Exception;
}
