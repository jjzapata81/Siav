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

import co.com.siav.bean.RamalBean;
import co.com.siav.entities.Ramal;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.RamalResponse;


@RequestScoped
@Path("/ramal")
public class RamalServices {
	
	@Inject
	private RamalBean bean;
	
	@GET
	@Path("/consultar/todo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ramal> consultar(){
		return bean.consultar();
	}
	
	@GET
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RamalResponse> consultarCompleto(){
		return bean.consultarTodo();
	}
	
	@POST
	@Path("/guardar/nuevo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse crear(Ramal request){
		return bean.crear(request);
	}
	
	@POST
	@Path("/guardar/editar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse actualizar(Ramal request){
		return bean.actualizar(request);
	}

}
