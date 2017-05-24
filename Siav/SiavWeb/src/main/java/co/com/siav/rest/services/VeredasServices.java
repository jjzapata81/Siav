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

import co.com.siav.bean.VeredasBean;
import co.com.siav.entities.Vereda;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.VeredaResponse;


@RequestScoped
@Path("veredas/")
public class VeredasServices {
	
	@Inject
	private VeredasBean bean;
	
	@POST
	@Path("crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse createVereda(String request){
		return bean.crear(request);
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vereda> getVeredas(){
		return bean.consultar();
	}
	
	@GET
	@Path("consultar/todo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VeredaResponse> getVeredasConUsuario(){
		return bean.consultarTodo();
	}
	
	@GET
	@Path("consultar/nombres")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getNombres(){
		return bean.consultarNombres();
	}

}
