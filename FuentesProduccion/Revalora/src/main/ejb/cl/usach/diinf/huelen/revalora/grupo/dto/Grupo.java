package cl.usach.diinf.huelen.revalora.grupo.dto;

import java.io.Serializable;

public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String groupName;
	
	public Grupo() {
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
