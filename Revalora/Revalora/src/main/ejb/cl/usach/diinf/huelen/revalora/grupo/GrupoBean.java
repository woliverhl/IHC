package cl.usach.diinf.huelen.revalora.grupo;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.grupo.dao.GroupDAOImpl;
import cl.usach.diinf.huelen.revalora.grupo.dao.GrupoDAO;
import cl.usach.diinf.huelen.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;

@Stateless
public class GrupoBean implements GrupoImpl {

	/**
	 * Logger de la clase
	 *
	 * @since 1.0
	 */
	Logger log = Logger.getLogger(GrupoBean.class);

	/**
	 * Objeto de conexion a la clase de acceso de datos para UserGroup
	 *
	 * @since 1.0
	 */
	GroupDAOImpl dao;

	/**
	 * Constructor de la clase
	 *
	 * @since 1.0
	 */
	public GrupoBean() {
		this.log.info("Instancia EJB");
		this.dao = new GrupoDAO();
	}

	/**
	 * Método encargado de ingresar a la base de datos una UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup ingresada a la base de datos.
	 * @throws Exception
	 * @since 1.0
	 */
	public void insertarUserGroup(GrupoDTO u) throws Exception {
		try {
			this.log.info("Antes de insertar");
			this.dao.insertaUserGroup(u);
			this.log.info("Despues de insertar");
		} catch (Exception e) {
			this.log.error("Error en insertarPersoa");
			this.log.error("UserGroup " + u.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de actualziar a la base de datos una UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup ingresada a la base de datos.
	 * @throws Exception
	 * @since 1.0
	 */
	public void actualizaUserGroup(GrupoDTO u) throws Exception {
		try {
			this.log.info("Antes de actualizaUserGroup");
			this.dao.actualizaUserGroup(u);
			;
			this.log.info("Despues de actualizaUserGroup");
		} catch (Exception e) {
			this.log.error("Error en actualizaUserGroup");
			this.log.error("UserGroup " + u.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de elimina en la base de datos una UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup que se elimina.
	 * @return
	 * @throws Exception
	 * @since 1.0
	 */
	public List<GrupoDTO> obtenerUserGroups() throws Exception {
		try {
			this.log.info("Antes de obtenerUserGroups");
			return this.dao.obtenerUserGroups();
		} catch (Exception e) {
			this.log.error("Error en obtenerUserGroups");
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de obtener en la base de datos un UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup que se elimina.
	 * @return GrupoDTO segun su llave
	 * @throws Exception
	 * @author Pablo Gavilan
	 * @since 1.0
	 */
	public GrupoDTO obtenerUserGroups(int id) throws Exception {
		try {
			this.log.info("Antes de obtenerUserGroups");
			GrupoDTO salida = this.dao.obtenerUserGroups(id);
			this.log.info("Antes de obtenerPersonasPorGrupo");
			List<PersonaDTO> personas = this.dao.obtenerPersonasPorGrupo(id);
			salida.setPersonas(personas);
			return salida;
		} catch (Exception e) {
			this.log.error("Error en obtenerGrupo");
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}
	/**
	 * Método encargado de elimina en la base de datos una UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup que se elimina.
	 * @throws Exception
	 * @since 1.0
	 */
	public void eliminaUserGroup(GrupoDTO u) throws Exception {
		try {
			this.log.info("Antes de eliminaUserGroup");
			this.dao.eliminaUserGroup(u);
			this.log.info("Despues de eliminaUserGroup");
		} catch (Exception e) {
			this.log.error("Error en eliminaUserGroup");
			this.log.error("UserGroup " + u.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	public void eliminaUserGroup(List<GrupoDTO> gruposEliminar) throws Exception {
		if(gruposEliminar!=null&&gruposEliminar.size()>0) {
			for (GrupoDTO grupoDTO : gruposEliminar) {
				try {
					this.eliminaUserGroup(grupoDTO);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
	}

	public void agregarPersonasAlGrupo(int id,
			List<PersonaDTO> personasSelecccionadas) throws Exception {
		if(personasSelecccionadas!=null&&personasSelecccionadas.size()>0) {
			for (PersonaDTO dto : personasSelecccionadas) {
				try {
					this.agregarPersonasAlGrupo(id, dto);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
	}

	public void agregarPersonasAlGrupo(int id, PersonaDTO dto) throws Exception{
		try {
			this.log.info("Antes de agregarPersonasAlGrupo");
			this.dao.agregarPersonasAlGrupo(id, dto);
			this.log.info("Despues de agregarPersonasAlGrupo");
		} catch (Exception e) {
			this.log.error("Error en agregarPersonasAlGrupo");
			this.log.error("UserGroup " + id);
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	public int obtenerProximoIdGrupo() {
		return this.dao.obtenerProximoIdGrupo();
	}
}
