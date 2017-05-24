package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Ramal;
import co.com.siav.repositories.IRepositoryRamal;

@Stateless
public class RamalBean {
	
	@Inject
	private IRepositoryRamal ramalRep;
	

	public List<Ramal> consultar() {
		return ramalRep.findAll();
	}

	

}
