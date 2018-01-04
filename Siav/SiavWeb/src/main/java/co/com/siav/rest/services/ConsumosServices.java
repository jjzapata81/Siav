package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.ConsumosBean;
import co.com.siav.entities.Consumo;
import co.com.siav.request.CorreccionConsumoRequest;
import co.com.siav.request.FiltroRequest;
import co.com.siav.response.ConsumoAuditoriaResponse;
import co.com.siav.response.ConsumoRiesgo;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("consumos/")
public class ConsumosServices {
	
	@Inject
	private ConsumosBean bean;
	
	@GET
	@Path("consultar/incompletos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consumo> getConsumosIncompletos(){
		return bean.consultarIncompletos();
	}
	
	@POST
	@Path("consultar/rango")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConsumoRiesgo> getConsumosRango(CorreccionConsumoRequest request){
		return bean.consultarRango(request);
	}
	
	@GET
	@Path("consultar/{instalacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConsumoRiesgo> getConsumoInstalacion(@PathParam(value="instalacion")Long instalacion){
		return bean.consultarInstalacion(instalacion);
	}
	
	@POST
	@Path("consultar/log")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConsumoAuditoriaResponse> getAuditoriaIncompletos(FiltroRequest request){
		return bean.consultarAuditoria(request);
	}
	
	@POST
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse guardar(@HeaderParam("siav_usuario") String usuario, CorreccionConsumoRequest request){
		return bean.guardar(request, usuario);
	}
	
	@POST
	@Path("guardar/correccion/lectura")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse guardarCorreccionLectura(@HeaderParam("siav_usuario") String usuario, CorreccionConsumoRequest request){
		return bean.guardarCorreccionLectura(request, usuario);
	}
	
	@POST
	@Path("guardar/correccion/consumo")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse guardarCorreccionConsumo(@HeaderParam("siav_usuario") String usuario, CorreccionConsumoRequest request){
		return bean.guardarCorreccionConsumo(request, usuario);
	}

}
