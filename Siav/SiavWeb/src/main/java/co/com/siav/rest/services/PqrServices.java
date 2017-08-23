package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.PqrBean;
import co.com.siav.request.PqrRequest;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.PqrDetalleResponse;
import co.com.siav.response.PqrResponse;


@RequestScoped
@Path("pqr/")
public class PqrServices {
	
	@Inject
	private PqrBean bean;
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PqrResponse> consultar(){
		return bean.consultarTodo();
	}
	
	@GET
	@Path("consultar/{idPqr}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PqrDetalleResponse> consultarDetalle(@PathParam(value="idPqr") Long idPqr){
		return bean.consultarDetalle(idPqr);
	}
	
	@POST
	@Path("crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse crear(PqrRequest request){
		return bean.crear(request);
	}
	
	@POST
	@Path("actualizar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse actualizar(PqrRequest request){
		return bean.actualizar(request);
	}

}
