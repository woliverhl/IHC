package cl.usach.diinf.huelen.revalora.personas.test;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.TestCase;
import cl.usach.diinf.huelen.revalora.personas.dao.PersonaBean;
import cl.usach.diinf.huelen.revalora.personas.dto.PersonaDTO;


public class PersonaTest extends TestCase {

	@EJB PersonaBean bean;

/**
 * Lista las personas
 */
	public void obtenerPersonas() {
		try{
			List<PersonaDTO> personas = bean.obtenerPersonas();
			assertTrue(personas!=null&&personas.size()>0);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail();
		}
	}

	public void insertarPersoa() {
		PersonaDTO p = this.getPersona();

		try {
			bean.insertarPersoa(p);
			assertTrue(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail();
		}
	}

	private PersonaDTO getPersona() {
		PersonaDTO p = new PersonaDTO();
		p.setApellido("apellido");
		p.setClave("clave");
		p.setCorreo("correo");
		p.setCumpleano(new Date());
		p.setDireccion("direccion");
		p.setGenero("M");
		p.setNombre("nombre");
		p.setPosicion("posicion");
		p.setRut("18278091k");
		p.setTelefono(666666);
		return p;
	}
}
