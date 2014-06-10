package cl.usach.diinf.revalora.correo.to;

/**
* Objeto para la obtencion de datos asociados a la estructura del correo
* @author	Sergio Reyes
* @updated	Alvaro Maldonado
* @version  1.0 
*/
public class CorreoTO { 
	  
	private String from; 
	private String[] to;  
	private String[] cc;       
	private String[] bcc;      
	private String subject;  
	private String text;  
	private String fecha;   
	private String descripcion; 
	private String mimeType;
	private int numero;
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Correo[from=");
		builder.append(this.from);
		builder.append(", to=");
		builder.append(this.to);
		builder.append(", cc=");
		builder.append(this.cc);
		builder.append(", bcc=");
		builder.append(this.bcc);
		builder.append(", subject=");
		builder.append(this.subject);
		builder.append(", text=");
		builder.append(this.text);
		builder.append(", fecha=");
		builder.append(this.fecha);
		builder.append(", descripcion=");
		builder.append(this.descripcion);
		builder.append(", mimeType=");
		builder.append(this.mimeType);
		builder.append(", numero=");
		builder.append(this.numero);
		builder.append("]");
		return builder.toString();
	}
	   
	public int getNumero() {    
		return numero; 
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public CorreoTO() {
	}


	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;  
	} 
	public String[] getCc() { 
		return cc; 
	} 
	public void setCc(String[] cc) {
		this.cc = cc;
	} 
	public String[] getBcc() {
		return bcc;
	}
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject; 
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
