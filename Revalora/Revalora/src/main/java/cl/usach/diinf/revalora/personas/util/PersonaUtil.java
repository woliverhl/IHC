package cl.usach.diinf.revalora.personas.util;

import java.util.ArrayList;
import java.util.List;

import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.diinf.revalora.persona.entities.PersonaEntity;

public class PersonaUtil {
	/**
	 * Metodo que recive una persona y lo transforma en su identidad
	 *
	 * @since 1.0
	 * @param p Objeto persona a transformar en entidad
	 * @return entidad de tipo persona
	 */
	public static PersonaEntity transforma(PersonaDTO p) {
		PersonaEntity pe = new PersonaEntity();
		pe.setApellido(p.getApellido());
		pe.setClave(p.getClave());
		pe.setCorreo(p.getCorreo());
		pe.setCumpleano(p.getCumpleano());
		pe.setDireccion(p.getDireccion());
		pe.setGenero(p.getGenero());
		pe.setId(p.getId());
		pe.setNombre(p.getNombre());
		pe.setPosicion(p.getPosicion());
		pe.setRut(p.getRut());
		pe.setTelefono(p.getTelefono());
		return pe;
	}

	/**
	 * Metodo que recive una entidad persona y lo transforma en su objeto
	 *
	 * @since 1.0
	 * @param p Entidad persona a tranasforma.
	 * @return objeto persona.
	 */
	public static PersonaDTO transforma(PersonaEntity p) {
		PersonaDTO pe = new PersonaDTO();
		pe.setApellido(p.getApellido());
		pe.setClave(p.getClave());
		pe.setCorreo(p.getCorreo());
		pe.setCumpleano(p.getCumpleano());
		pe.setDireccion(p.getDireccion());
		pe.setGenero(p.getGenero());
		pe.setId(p.getId());
		pe.setNombre(p.getNombre());
		pe.setPosicion(p.getPosicion());
		pe.setRut(p.getRut());
		pe.setTelefono(p.getTelefono());
		return pe;
	}

	public static List<PersonaDTO> transforma(List<PersonaEntity> lpe) {
		List<PersonaDTO> lp = new ArrayList<PersonaDTO>();

		for (PersonaEntity personaEntitie : lpe) {
			lp.add(PersonaUtil.transforma(personaEntitie));
		}

		return lp;
	}
}
