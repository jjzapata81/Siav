package co.com.siav.rest.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.ContabilidadBean;
import co.com.siav.entities.Sistema;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("contabilidad/")
public class ContabilidadServices {
	
	@Inject
	private ContabilidadBean bean;
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public Sistema getConfiguracion(){
		return bean.consultar();
	}
	
	@POST
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse saveConfiguracion(Sistema request){
		return bean.guardar(request);
	}

}
