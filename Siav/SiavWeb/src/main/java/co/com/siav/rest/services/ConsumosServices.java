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

import co.com.siav.bean.ConsumosBean;
import co.com.siav.entities.Consumo;
import co.com.siav.request.CorreccionConsumoRequest;
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
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse guardar(CorreccionConsumoRequest request){
		return bean.guardar(request);
	}

}