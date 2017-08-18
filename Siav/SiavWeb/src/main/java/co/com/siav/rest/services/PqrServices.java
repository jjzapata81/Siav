package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.PqrBean;
import co.com.siav.entities.Pqr;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("pqr/")
public class PqrServices {
	
	@Inject
	private PqrBean bean;
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pqr> consultar(){
		return bean.consultarTodo();
	}
	
	@POST
	@Path("crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse crear(Pqr request){
		return bean.crear(request);
	}

}
