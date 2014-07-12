package cl.usach.diinf.revalora.correo.mb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Named
@ManagedBean
public class ModeloMensajesBean {

	private String modelo;
	private Map<String, String> modelos = new HashMap<String, String>();
	private String statusMessage = "";
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



	public String getModelo() {
		return modelo;
	}


		public void setModelo(String modelo) {
			this.modelo = modelo;
		}


	public Map<String, String> getModelos() {
		return modelos;
	}


	public void setModelos(Map<String, String> modelos) {
		this.modelos = modelos;
	}

	public String getStatusMessage() {
		return statusMessage;
	}


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	
	public void onHandleKeyEvent() {
		if(modelo !=null && !modelo.equals(""))
            text = modelo;
        else
            text = "";
		
      System.out.println((modelo));
	}
	

	@PostConstruct
	public void init(){
		modelos = new HashMap<String, String>();

		Properties prop = new Properties();
		InputStream input = null;
		try{
			String filename = "mensajes.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("No se puede encontrar el archivo " + filename);
				return;
			}
			
			prop.load(input);

			Enumeration<?> e = prop.propertyNames();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				System.out.println("Key : " + key + ", Value : " + value);
				modelos.put(key, value);
			}


		}catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}


}
