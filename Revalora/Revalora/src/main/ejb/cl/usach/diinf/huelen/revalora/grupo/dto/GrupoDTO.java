package cl.usach.diinf.huelen.revalora.grupo.dto;

import java.io.Serializable;
import java.util.List;

import cl.usach.diinf.revalora.persona.dto.PersonaDTO;

public class GrupoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String groupName;
	private String descripcion;
	private List<PersonaDTO> personas;

	public GrupoDTO() {
		this.id = 0;
		this.personas = null;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<PersonaDTO> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<PersonaDTO> personas) {
		this.personas = personas;
	}

	/**
	 * Metodo que retorna el campo GrupoDTO.java
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Metodo que setea el campo GrupoDTO.java
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



}
