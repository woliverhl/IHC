package cl.usach.diinf.huelen.revalora.personas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Clase entidad que representa los registros de la tabla APPLICANTS.
 * 
 * @author Felipe Morales
 * @version 1.0
 */
@Entity @Table(name="APPLICANTS")
@NamedQuery(name="Postulante.findAll",  query="SELECT c FROM PostulanteEntitie c")
public class PostulanteEntitie implements Serializable {
	

	private static final long serialVersionUID = 1L;

	public static final String SQL_SELECT_ALL = "Postulante.findAll";

	@Id @Column(nullable=false, length=10, name="RUT") private String rut;
	@Column(nullable=false, length=100, name="CURRICULUM") private String cv;
	
	
	
	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getCv() {
		return this.cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}
	

	public PostulanteEntitie() {
	}

}