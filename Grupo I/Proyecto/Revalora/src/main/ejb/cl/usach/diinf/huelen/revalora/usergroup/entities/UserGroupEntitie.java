package cl.usach.diinf.huelen.revalora.usergroup.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the USER_GROUPS database table.
 * 
 */
@Entity
@Table(name="USER_GROUPS")
@NamedQuery(name="UserGroupEntitie.findAll", query="SELECT u FROM UserGroupEntitie u")
public class UserGroupEntitie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String SQL_SELECT_ALL = "UserGroupEntitie.findAll";

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="GROUP_NAME")
	private String groupName;

	public UserGroupEntitie() {
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

}