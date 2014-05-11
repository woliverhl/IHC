package cl.usach.diinf.huelen.revalora.usergroup.dto;

import java.io.Serializable;

public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String groupName;
	
	public UserGroup() {
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
