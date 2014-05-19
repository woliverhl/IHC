package cl.usach.infraestructura.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;



/**
* <b>TablaValores</b>
*
 * Componente encargado de obtener tabla de parametros. 
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
public class TablaValores implements Serializable, FileChangeListener{

	/**
	 * Variable para serializaci??n
	 */
   private static final long serialVersionUID = 1L;


    private static Hashtable tbl   = new Hashtable();
    private static String p = "/tablas/";
    private final static TablaValores tv = new TablaValores();
    private final static String NOMLOG = tv.getClass().getName();

    public synchronized static Hashtable reLoad(String file){
        Logger logger = Logger.getLogger(NOMLOG);
        if (logger.isDebugEnabled()) {
            logger.debug("reLoad: [" + file + "]");
        }
        if (file.startsWith(p)){
            file = file.substring(p.length());            
        }
        Hashtable tmp = loadTable(file);
        tbl.put(file, tmp);
        return (Hashtable)tbl.get(file);
    }

    public void fileChanged(String fileName) {        
        reLoad(fileName);
    }

    /**
     * Carga informaci??n de una tabla de par??metros para dejarla en memoria. 
     * @param archivo nombre de la tabla de parametros a cargar.
     * @return
     */
    private synchronized static Hashtable loadTable(String archivo) {        
        Logger logger = Logger.getLogger(NOMLOG);
        if (logger.isDebugEnabled()) {
            logger.debug("loadTable: [" + archivo + "]");
        }
        String path = archivo.startsWith(System.getProperty("file.separator")) ? "" : 
                        archivo.startsWith(p) ? "" : p; // Completa Path de Archivo
        String arch = TablaValores.class.getClassLoader().getResource(path + archivo).getPath(); 
        String record = null;
        String tmp = null;
        String mainkey = null;
        String seckey = null;
        String secValue = null;
        Hashtable tabla = new Hashtable();
        Hashtable tblreg = null;
        int pos = 0;
        
        if (logger.isDebugEnabled()) {
            logger.debug("Carga/recarga Archivo con nueva version: [" + arch + "]");
        }
        
        try {
        	FileReader fr = new FileReader(arch);
            BufferedReader br = new BufferedReader(fr);
            record = new String();

            while ((record = br.readLine()) != null) {
                if (!record.trim().equals("") & !record.trim().startsWith("#")){
                    tblreg = new Hashtable();
                    StringTokenizer stTok = new StringTokenizer(record);
                    tmp = stTok.nextToken(";");
                    mainkey = tmp == null ? "" : tmp.trim().toUpperCase();
                    while (stTok.hasMoreTokens()) {
                        try{
                            tmp = stTok.nextToken("=;");
                            seckey = tmp == null ? "" : tmp.trim().toUpperCase();
                            tmp = stTok.nextToken(";");
                            secValue = tmp == null ? "" : normaliza(tmp.substring(1).trim());
                            tblreg.put(seckey, secValue);
                         }catch(NoSuchElementException e){
                            if (logger.isEnabledFor(Level.WARN)) {
                                logger.warn("Error Archivo: " + arch + ", llave: " +  mainkey + ", Excepcion: " + e);
                            }
                            secValue = "";
                         }catch(StringIndexOutOfBoundsException e){
                             if (logger.isEnabledFor(Level.WARN)) {
                                 logger.warn("Error Archivo: " + arch + ", llave: " +  mainkey + ", Excepcion: " + e);
                             }
                            secValue = "";
                         }
                    }
                    tblreg.put("_Posicion", String.valueOf(pos));
                    pos++;
                    tabla.put(mainkey, tblreg);
                }
            }            
            try {
	            FileMonitor.getInstance().addFileChangeListener(tv, arch, 10000l);// M???x. 10 Segundos;
	        }catch (FileNotFoundException e) {
	            if (logger.isEnabledFor(Level.ERROR)) {
	                logger.error("FileNotFoundException : " + e);
	            }
	        }
            br.close();
        } catch (IOException ioe) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error(ioe);
            }
            try {
                FileMonitor.getInstance().addFileChangeListener(tv, arch, 10000l);// M???x. 10 Segundos;
            } catch (FileNotFoundException e) {
                if (logger.isEnabledFor(Level.ERROR)) {
                    logger.error(e);
                }
            }
        }
        return tabla;
    }
        
        
    /**
     * <p>
     * Metodo utilitario que transforma los caracteres \\n, \\t, \\r
     * en car??cteres especiales de salto de l??nea, tabulaci??n y retorno de carro
     * respectivamente. Ej:
     * si linea="Hola\\nMundo" entonces, retorno="Hola\nMundo".
     * 
     * </p>
     *
     * 
     * @param linea a transformar 
     * @return linea con los caracteres reemplazados
     * @since 1.0
     *
     */
    public static String normaliza(String linea){
        final int LENGTH = linea.length();
        if (LENGTH == 0) {
		return linea;
    }

        StringBuffer sb = null;

        int beg = 0; int a = 0;
        while (a < LENGTH) {
            //
            // Invariante: sb contiene a[0:beg[ con caracteres \n, \t, \r
            //
        
            // buscar '\'
            a = linea.indexOf('\\', a);
            if (a == -1 || a == LENGTH-1) {
                // 
                // el caracter '\' no se encuentra en linea[beg, LENGTH[ 
                // ...o es el ultimo caracter
                // 
            
                if (beg == 0) {
                    //evitamos copiar la linea al string buffer
                    return linea;
                }
                sb.append(linea.substring(beg, LENGTH));
                break;
            }

            if (sb == null) {
                sb = new StringBuffer(LENGTH);
            }
            //
            // linea[a] == '\' y linea[a+1] es eventualmente 'n', 'r', 't'
            //
            char c = linea.charAt(a+1);
            switch (c) {
                case 'n': 
                    sb.append(linea.substring(beg, a)).append('\n');
                    beg = a+2;
                    a = a+2;
                    break;
                case 't': 
                    sb.append(linea.substring(beg, a)).append('\t');
                    beg = a+2;
                    a = a+2;
                    break;
                case 'r': 
                    sb.append(linea.substring(beg, a)).append('\r');
                    beg = a+2;
                    a = a+2;
                    break;
                default:
                    a++;
            }
        }
        return sb.toString();
    }

    public static String getValor(String file, String clave, String cod) {

        if (tbl.get(file) == null){
            Hashtable ht = loadTable(file);
            if (tbl.get(file) == null)
                tbl.put(file, ht);
        }
        Hashtable a = (Hashtable)tbl.get(file);
        Hashtable b = (Hashtable)a.get(clave.toUpperCase());
        String ret = null;
        if (b != null) {
            ret = (String)b.get(cod.toUpperCase());
        }
        return ret;
    }
    
    public static Hashtable getTabla(String file){
        if (tbl.get(file) == null){
            Hashtable ht = loadTable(file);
            if (tbl.get(file) == null)
                tbl.put(file, ht);
        }
        return (Hashtable)tbl.get(file);
    }

    public static int getCantTablas(){
        return tbl == null ? 0 : tbl.size();
    }
    
    public static Hashtable test(){
        return tbl;
    }
}
