package cl.usach.diinf.revalora.grupo.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.grupo.GrupoImpl;
import cl.usach.diinf.huelen.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.infraestructura.util.TablaValores;

/**
 * Clase manageBean encargada de las acciones relacionadas con un grupo
 *
 * @author Pablo Gavilan
 * @version 1.0
 *
 */
@Named
@RequestScoped
public class GrupoCrearEditarBackingMB {

	/**
	 * EJB que llama a todos los métodos encargados de la clase persona
	 */
	@EJB
	GrupoImpl bean;

	/**
	 * Logger de la clase
	 */
	Logger log = Logger.getLogger(GrupoCrearEditarBackingMB.class);

	/**
	 * Objeto persona utilizado por el managebean
	 */
	private GrupoDTO dto;

	private List<PersonaDTO> personasEliminar;
	/**
	 * Tabla de parametros para persona
	 */
	private static final String TABLA_PARAMETROS = "grupo.parametros";

	/**
	 * Constructor de la clase
	 */
	public GrupoCrearEditarBackingMB() {
		super();
		this.dto = new GrupoDTO();
	}

	/**
	 * Pos constructor de la clase
	 *
	 *
	 * @author Pablo Gavilan May 23, 2014
	 * @version 1.0
	 */
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();

		String busca = req.getParameter("BUSCA");
		if(busca!=null&&"SI".compareTo(busca)==0) {
			String id = req.getParameter("ID");
			if(id!=null&&"".compareTo(id)!=0) {
				try {
					this.dto = this.bean.obtenerUserGroups(Integer.parseInt(id));
				} catch (Exception e) {
					String msg = TablaValores.getValor(TABLA_PARAMETROS,
							"msgCrearEditar",
							"nokInserta");
					FacesContext.getCurrentInstance().addMessage(
							null, new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									msg, e.getMessage()));
				}
			}
		}
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 *
	 * @since 1.0
	 * @return
	 */
	public String insertarGrupo() {

		this.log.info("inicio insertarPersoa");
		try {
			this.bean.insertarUserGroup(this.dto);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "okInserta");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			this.log.info("termino insertarGrupo");
		} catch (Exception e) {
			this.log.error("Error " + e.getMessage());
			this.log.error("Error " + e.getClass());
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "nokInserta");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
			return "error";
		}
		return "success";
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 *
	 * @since 1.0
	 * @return
	 */
	public String actualizaGrupo() {

		this.log.info("inicio actualizaPersona");
		try {
			this.bean.actualizaUserGroup(this.dto);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "okActualiza");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			this.log.info("termino actualizaGrupo");
		} catch (Exception e) {
			this.log.error("Error actualizaGrupo");
			this.log.error("Error " + e.getMessage());
			this.log.error("Error " + e.getClass());
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "nokActualiza");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
			return "error";
		}
		return "success";
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 *
	 * @since 1.0
	 * @return
	 */
	public String obtenerGrupo(int id) {

		if (id > 0) {
			try {
				this.dto = this.bean.obtenerUserGroups(id);
			} catch (Exception e) {
				this.log.error("Error al bean.obtenerPersonas(" + this.dto.getId() + "):" + e);
				e.printStackTrace();
				String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "nokObtener");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
			}
		}
		return "actualiza";
	}

	public void doDeleteUsers(ActionEvent ae) {
		try {
			this.bean.agregarPersonasAlGrupo(0, this.personasEliminar);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "okEliPer");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		} catch (Exception e) {
			this.log.error("Error al eliminarPersonasDeGrupo:" + e);
			e.printStackTrace();
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrearEditar", "nokEliPer");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
		}
	}

	/**
	 * Metodo que retorna el campo dto
	 * @return the dto
	 */
	public GrupoDTO getDto() {
		return this.dto;
	}

	/**
	 * Metodo que setea el campo dto
	 * @param dto the dto to set
	 */
	public void setDto(GrupoDTO dto) {
		this.dto = dto;
	}

	public List<PersonaDTO> getPersonasEliminar() {
		return this.personasEliminar;
	}

	public void setPersonasEliminar(List<PersonaDTO> personasEliminar) {
		this.personasEliminar = personasEliminar;
	}
}
