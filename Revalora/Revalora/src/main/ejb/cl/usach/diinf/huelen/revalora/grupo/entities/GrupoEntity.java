package cl.usach.diinf.huelen.revalora.grupo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cl.usach.diinf.revalora.persona.entities.PersonaEntity;


/**
 * The persistent class for the USER_GROUPS database table.
 *
 */
@Entity
@Table(name="USER_GROUPS")
@NamedQuery(name="UserGroupEntitie.findAll", query="SELECT u FROM GrupoEntity u")
public class GrupoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String SQL_SELECT_ALL = "UserGroupEntitie.findAll";

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="GROUP_NAME")
	private String groupName;

	@Column(name="DESCRIPTION")
	private String descripcion;
	private List<PersonaEntity> personas;

	public GrupoEntity() {
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

	/**
	 * Metodo que retorna el campo GrupoEntity.java
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Metodo que setea el campo GrupoEntity.java
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<PersonaEntity> getPersons() {
		return this.personas;
	}

	public void setPersons(List<PersonaEntity> persons) {
		this.personas = persons;
	}



}