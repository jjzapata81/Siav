package co.com.siav.rest.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.NovedadesBean;
import co.com.siav.entities.Novedad;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("novedades/")
public class NovedadesServices {
	
	@Inject
	private NovedadesBean bean;
	
	@PUT
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse guardarNovedad(Novedad request){
		return bean.guardar(request);
	}

}
