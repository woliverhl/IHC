package cl.usach.diinf.revalora.persona.dto;

import java.io.Serializable;

/**
 * Clase que representa a un objeto persona.
 * 
 * @author FELIPE MORALES
 * @version 1.0
 */
public class PostulanteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rut;
	private String cv;

	public String getCv() {
		return this.cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public PostulanteDTO() {
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}


	
}