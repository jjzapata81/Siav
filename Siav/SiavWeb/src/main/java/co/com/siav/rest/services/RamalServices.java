package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.RamalBean;
import co.com.siav.entities.Ramal;


@RequestScoped
@Path("ramal/")
public class RamalServices {
	
	@Inject
	private RamalBean bean;
	
	@GET
	@Path("consultar/todo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ramal> guardarCredito(){
		return bean.consultar();
	}

}
