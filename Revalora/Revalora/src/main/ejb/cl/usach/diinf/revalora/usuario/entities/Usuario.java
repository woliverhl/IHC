package cl.usach.diinf.revalora.usuario.entities;

import java.io.Serializable;

import javax.persistence.Id;

public class Usuario implements Serializable  {

	private static final long serialVersionUID = 1L;
    @Id
    private String username;
    private String password;
    private String firstname;
    private String lastname;
  
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return firstname;
    }

    public void setNombre(String firstname) {
        this.firstname = firstname;
    }

    public String getApellido() {
        return lastname;
    }

    public void setApellido(String lastname) {
        this.lastname = lastname;
    }
    
    public static Usuario getUser(String username, String password){
    	Usuario usuario = new Usuario();
    	usuario.setUsername(username);
    	usuario.setNombre("Login");
    	usuario.setApellido("Revalora Demo");
    	return usuario;
    }
    
    @Override
    public String toString() {
        return "USER {" + "username=" + username + "; }";
    }
}
