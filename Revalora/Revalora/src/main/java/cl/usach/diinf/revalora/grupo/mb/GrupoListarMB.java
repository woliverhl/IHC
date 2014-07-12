package cl.usach.diinf.revalora.grupo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.grupo.GrupoImpl;
import cl.usach.diinf.huelen.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.revalora.persona.PersonaImpl;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.infraestructura.util.TablaValores;

@Named
@SessionScoped
public class GrupoListarMB implements Serializable{

	@EJB
	GrupoImpl bean;

	@EJB
	PersonaImpl beanPersona;

	private GrupoDTO grupoSeleccionado;

	/**
	 * Logger de la clase
	 */
	Logger log = Logger.getLogger(GrupoListarMB.class);

	/**
	 * Tabla de parametros para persona
	 */
	private static final String TABLA_PARAMETROS = "grupo.parametros";

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<GrupoDTO> grupos = new ArrayList<GrupoDTO>();
	private GrupoDTO grupo;

	/**
	 * Listado de grupos a eliminar.
	 */
	private	List<GrupoDTO> gruposEliminar;

	/**
	 * Listado de personas seleccionadas.
	 */
	private List<PersonaDTO> personasSelecccionadas;

	/**
	 * Listado de personas a eliminar.
	 */
	private List<PersonaDTO> personasEliminar;

	public void buttonAction(ActionEvent actionEvent) {
		this.personasSelecccionadas.removeAll(this.personasEliminar);
	}

	public GrupoListarMB(){}

	@PostConstruct
	public void init() {
		try {
			    this.grupos = this.bean.obtenerUserGroups();
			} catch (Exception e) {
			    e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de eliminar un grupo.
	 * @param actionEvent
	 *
	 * @author Pablo Gavilan Jun 10, 2014
	 * @version 1.0
	 */
	public void EliminarGrupoAction(ActionEvent actionEvent) {
		try {
			
			List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
			List<PersonaDTO> todaPersona = null;
			todaPersona = this.beanPersona.obtenerPersonas();
			
			if(todaPersona!=null) {

				
				for (PersonaDTO dto : todaPersona) {
					for (GrupoDTO dtog : this.gruposEliminar) {
						
					
					if(dto.getId()==dtog.getId()) {
						personas.add(dto);
					}
					
					}
				}
			}
			
			if (personas.size()==0)
			{				
			this.log.info("EliminarGrupoAction:Antes de bean.eliminaUserGroup");
			for (GrupoDTO dto : this.gruposEliminar) {
				this.bean.eliminaUserGroup(dto);
			}
			this.log.info("EliminarGrupoAction:Antes de grupos.removeAll");
			this.grupos.removeAll(this.gruposEliminar);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgElimina", "OK");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			this.log.info("EliminarGrupoAction:OK");
			}else
			{
				String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgEG", "NOK");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,""));
				
			}
		} catch (Exception e) {
			this.log.error("EliminarGrupoAction:NOK");
			this.log.error("EliminarGrupoAction:" + e);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgElimina", "NOK");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
		}
	}

	public void CrearGrupoAction(ActionEvent actionEvent) {

		try {
			this.log.info("CrearGrupoAction:Antes de bean.insertarUserGroup");

			if(this.grupo.getId()<=0) {
				this.grupo.setId(this.bean.obtenerProximoIdGrupo());
			}

			this.bean.insertarUserGroup(this.grupo);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrea", "OK");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			this.log.info("CrearGrupoAction:OK");
			this.grupos.add(this.grupo);
			this.grupo=new GrupoDTO();
		} catch (Exception e) {
			this.log.error("CrearGrupoAction:NOK");
			this.log.error("CrearGrupoAction:" + e);
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgCrea", "NOK");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
		}
	}
	public void EditarGrupoAction(ActionEvent actionEvent) {

		int index = -1;
		try {
			index = this.grupos.indexOf(this.grupoSeleccionado);
			this.log.info("EditarGrupoAction:Antes de bean.agregarPersonasAlGrupo");

			if(this.personasEliminar!=null&&this.personasEliminar.size()>0) {
				int indexdto = 0;
				for (PersonaDTO el : this.personasEliminar) {
					indexdto = this.personasSelecccionadas.indexOf(el);
					if(indexdto>=0) {
						this.personasSelecccionadas.remove(indexdto);
					}
				};
			}

			if(this.personasSelecccionadas!=null&&this.personasSelecccionadas.size()>0) {
				this.log.info("EditarGrupoAction:Antes agregarPersonasAlGrupo");
				this.bean.agregarPersonasAlGrupo(
						this.grupoSeleccionado.getId(),
						this.personasSelecccionadas);
				this.log.info("EditarGrupoAction:Antes agregarPersonasAlGrupo OK");
			}

			if(this.personasEliminar!=null&&this.personasEliminar.size()>0) {
				this.log.info("EditarGrupoAction:Antes eliminarPersonasDeGrupo");
				this.bean.agregarPersonasAlGrupo(
						0,
						this.personasEliminar);
				this.log.info("EditarGrupoAction:Antes eliminarPersonasDeGrupo OK");
			}

			this.log.info("EditarGrupoAction:Antes de bean.actualizaUserGroup");
			this.bean.actualizaUserGroup(this.grupoSeleccionado);

			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgEdit", "OK");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			this.personasSelecccionadas = new ArrayList<PersonaDTO>();
			this.log.info("EditarGrupoAction:OK");
		} catch (Exception e) {
			this.log.error("EditarGrupoAction:NOK");
			this.log.error("EditarGrupoAction:" + e);
			//String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgEdit", "NOK");
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, e.getMessage()));
			String msg = TablaValores.getValor(TABLA_PARAMETROS, "msgEdit", "OK");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg + "!!!"));
		}
		this.grupos.set(index, this.grupoSeleccionado);
	}

	public List<PersonaDTO> getPersonasEliminar() {
		return this.personasEliminar;
	}

	public void setPersonasEliminar(List<PersonaDTO> personasEliminar) {
		this.personasEliminar = personasEliminar;
	}

	public List<PersonaDTO> getPersonasSelecccionadas() {
		return this.personasSelecccionadas;
	}

	public void setPersonasSelecccionadas(List<PersonaDTO> personasSelecccionadas) {
		this.personasSelecccionadas = personasSelecccionadas;
	}

	public List<GrupoDTO> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<GrupoDTO> grupos) {
		this.grupos = grupos;
	}

	public GrupoDTO getGrupoSeleccionado() {
		return this.grupoSeleccionado;
	}

	public void setGrupoSeleccionado(GrupoDTO grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;

		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		List<PersonaDTO> todaPersona = null;
		try {
			todaPersona = this.beanPersona.obtenerPersonas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(todaPersona!=null) {
			for (PersonaDTO dto : todaPersona) {
				if(dto.getId()==this.grupoSeleccionado.getId()) {
					personas.add(dto);
				}
			}
		}
		this.personasSelecccionadas = personas;
	}

	public GrupoDTO getGrupo() {
		if(this.grupo==null) {
			this.grupo = new GrupoDTO();
		}
		return this.grupo;
	}
	public void setGrupo(GrupoDTO grupo) {
		this.grupo = grupo;
	}
	public List<GrupoDTO> getGruposEliminar() {
		return this.gruposEliminar;
	}
	public void setGruposEliminar(List<GrupoDTO> gruposEliminar) {
		this.gruposEliminar = gruposEliminar;
	}

}
