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

import co.com.siav.bean.ExcesosBean;
import co.com.siav.dto.ExcesoDTO;
import co.com.siav.dto.RangoConsumosDTO;
import co.com.siav.entities.Exceso;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("excesos/")
public class ExcesosServices {
	
	@Inject
	private ExcesosBean bean;
	
	@POST
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse createExceso(ExcesoDTO request){
		return bean.guardar(request);
	}
	
	@POST
	@Path("editar/rango")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse editRange(RangoConsumosDTO request){
		return bean.editarRango(request);
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Exceso> getExcesos(){
		return bean.consultar();
	}
	
}
