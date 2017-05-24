package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Maestros;
import co.com.siav.repositories.IRepositoryMaestros;

@Stateless
public class ListasBean {
	
	@Inject
	private IRepositoryMaestros maestrosRep;

	public List<Maestros> consultarMaestro(String nombreMaestro) {
		return maestrosRep.findByGrupo(nombreMaestro);
	}

}
