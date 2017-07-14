package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.NovedadesBean;
import co.com.siav.entities.Novedad;
import co.com.siav.request.NotaCreditoRequest;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("novedades/")
public class NovedadesServices {
	
	@Inject
	private NovedadesBean bean;
	
	@PUT
	@Path("eliminar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse eliminarNovedad(Novedad request){
		return bean.eliminar(request);
	}
	
	@PUT
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse guardarNovedad(Novedad request){
		return bean.guardar(request);
	}
	
	@GET
	@Path("consultar/{numeroInstalacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Novedad> consultar(@PathParam("numeroInstalacion") Long numeroInstalacion){
		return bean.consultarPorCedula(numeroInstalacion);
	}
	
	@PUT
	@Path("guardar/nota-credito")
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse guardarNotaCredito(@HeaderParam("user") String usuraio, NotaCreditoRequest request){
		return bean.guardarNotaCredito(request);
	}
	

}
