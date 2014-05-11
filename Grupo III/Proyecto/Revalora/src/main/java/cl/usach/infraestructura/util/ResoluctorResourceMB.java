package cl.usach.infraestructura.util;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Request ManagedBean
 * 
 * <pre><b>ResoluctorResourceMB</b>
 *
 * Componente encargado de obtener el resource bundle. 
 *
 * </pre>
 *
 * Registro de versiones:<ul>
 *
 * <LI>1.0 13/04/2014, Oliver Hidalgo L. - Versi??n inicial
 * 
 * </ul>
 * <b>Todos los derechos reservados por USACH.</b>
 */

@ManagedBean
@RequestScoped
public class ResoluctorResourceMB implements Serializable {

	/**
	 * Variable para serializaci??n
	 */
	private static final long serialVersionUID = 1L;

	public String obtieneResourceBundle(String recurso) {

		try {
			String recursoWeb = recurso;

			ResourceBundle rb = ResourceBundle.getBundle(recursoWeb);
			return recursoWeb;
		} catch (MissingResourceException me) {
			return recurso;
		} catch (Exception ex) {
			return recurso;
		}
	}
}
