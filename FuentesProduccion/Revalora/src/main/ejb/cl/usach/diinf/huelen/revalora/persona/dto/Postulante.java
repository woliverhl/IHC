package cl.usach.diinf.huelen.revalora.persona.dto;

import java.io.Serializable;

/**
 * Clase que representa a un objeto persona.
 * 
 * @author FELIPE MORALES
 * @version 1.0
 */
public class Postulante implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rut;
	private String cv;

	public String getCv() {
		return this.cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public Postulante() {
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}


	
}