package cl.usach.infraestructura.util;

public interface FileChangeListener {
    /** Invoked when a file changes.   
     * @param fileName name of changed file.
     */
    public void fileChanged(String fileName);
}
