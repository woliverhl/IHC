package cl.usach.infraestructura.util.web;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


@ManagedBean
@RequestScoped
public class ResoluctorResourceMB implements Serializable {

    /**
     * Variable para serializaci—n.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Variable para asignar log de ejecución.
     */
    private static Logger logger = (Logger)Logger.getLogger(ResoluctorResourceMB.class);

    public String obtieneResourceBundle(String recurso) {

        if (logger.isDebugEnabled()) {
            logger.debug("inicio obtieneResourceBundle");
        }
        try{
           
            ResourceBundle rb =  ResourceBundle.getBundle(recurso);
            logger.debug("obtieneResourceBundle::OK: Se carga el recurso por canal");
            return recurso;
        }
        catch (MissingResourceException me) {            
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn("obtieneResourceBundle::MissingResourceException: " + me.toString());
                logger.warn("obtieneResourceBundle::MissingResourceException:No existe recurso.Carga el default");
            }

            return recurso;
        }
        catch (Exception ex) {           
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("obtieneResourceBundle::Exception: " + ex.toString());
                logger.error("obtieneResourceBundle::Exception: Se carga por defecto");
            }
            return recurso;
        }

    }
}
