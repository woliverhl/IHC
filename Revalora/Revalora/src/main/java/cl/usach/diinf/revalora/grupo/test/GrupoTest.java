package cl.usach.diinf.revalora.grupo.test;

import junit.framework.TestCase;
import cl.usach.diinf.huelen.revalora.grupo.dao.GroupDAOImpl;
import cl.usach.diinf.huelen.revalora.grupo.dao.GrupoDAO;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;

public class GrupoTest extends TestCase {

	public void test(){
		GroupDAOImpl dao = new GrupoDAO();
		int id = 0;

		PersonaDTO dto = new PersonaDTO();
		dto.setRut("8781238-3");
		try {
			dao.agregarPersonasAlGrupo(id , dto );
			assertEquals(true, true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
