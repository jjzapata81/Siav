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

import co.com.siav.bean.RangosBean;
import co.com.siav.entities.Rango;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("rangos/")
public class RangosServices {
	
	@Inject
	private RangosBean bean;
	
	@POST
	@Path("crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse createRango(Rango request){
		return bean.crear(request);
	}
	
	@POST
	@Path("eliminar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse deleteRango(Rango request){
		return bean.eliminar(request);
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rango> getRangos(){
		return bean.consultar();
	}

}
