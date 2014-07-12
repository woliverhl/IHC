package cl.usach.diinf.revalora.grupo.util;

import java.util.ArrayList;
import java.util.List;

import cl.usach.diinf.huelen.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.huelen.revalora.grupo.entities.GrupoEntity;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.diinf.revalora.persona.entities.PersonaEntity;

public class GrupoUtil {
	/**
	 * Metodo que recive una UserGroup y lo transforma en su identidad
	 *
	 * @since 1.0
	 * @param p Objeto UserGroup a transformar en entidad
	 * @return entidad de tipo UserGroup
	 */
	public static GrupoEntity transforma(GrupoDTO ug) {
		GrupoEntity uge = new GrupoEntity();
		uge.setId(ug.getId());
		uge.setGroupName(ug.getGroupName());
		uge.setDescripcion(ug.getDescripcion());
		if(ug.getPersonas()!=null&&ug.getPersonas().size()>0) {
			List<PersonaEntity> persons = new ArrayList<>();
			for (PersonaDTO p : ug.getPersonas()){
				PersonaEntity pEntity = new PersonaEntity();
				pEntity.setApellido(p.getApellido());
				pEntity.setClave(p.getClave());
				pEntity.setCorreo(p.getCorreo());
				pEntity.setCumpleano(p.getCumpleano());
				pEntity.setDireccion(p.getDireccion());
				pEntity.setGenero(p.getGenero());
				pEntity.setId(p.getId());
				pEntity.setNombre(p.getNombre());
				pEntity.setPosicion(p.getPosicion());
				pEntity.setRut(p.getRut());
				pEntity.setTelefono(p.getTelefono());
				persons.add(pEntity);
			}
			uge.setPersons(persons);
		}
		return uge;
	}

	/**
	 * Metodo que recive una entidad UserGroup y lo transforma en su objeto
	 *
	 * @since 1.0
	 * @param p Entidad UserGroup a tranasforma.
	 * @return objeto UserGroup.
	 * UserGroup transforma(GrupoEntity p)
	 */
	public static GrupoDTO transforma (GrupoEntity u)    {
		GrupoDTO ug = new GrupoDTO();
		ug.setId(u.getId());
		ug.setGroupName(u.getGroupName());
		ug.setDescripcion(u.getDescripcion());

		if(u.getPersons()!=null&&u.getPersons().size()>0) {
			List<PersonaDTO> persons = new ArrayList<>();
			for (PersonaEntity p : u.getPersons()){
				PersonaDTO personDTO = new PersonaDTO();
				personDTO.setApellido(p.getApellido());
				personDTO.setApellido(p.getClave());
				personDTO.setApellido(p.getCorreo());
				personDTO.setApellido(p.getDireccion());
				personDTO.setApellido(p.getGenero());
				personDTO.setApellido(p.getNombre());
				personDTO.setApellido(p.getPosicion());
				personDTO.setApellido(p.getRut());
				persons.add(personDTO);
			}
			ug.setPersonas(persons);
		}
		return ug;
	}

	public static List<GrupoDTO> transforma(List<GrupoEntity> luge) {
		List<GrupoDTO> lug = new ArrayList<GrupoDTO>();

		for (GrupoEntity userGroupEntitie : luge){
			lug.add(transforma(userGroupEntitie));
		}

		return lug;
	}
}
