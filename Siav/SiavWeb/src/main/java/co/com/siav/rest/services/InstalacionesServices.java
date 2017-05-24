package co.com.siav.rest.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.InstalacionBean;
import co.com.siav.entities.Instalacion;
import co.com.siav.response.InstalacionResponse;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("instalacion/")
public class InstalacionesServices {
	
	
	@Inject
	private InstalacionBean bean;
	
	
	@GET
	@Path("buscar/{numeroInstalacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public InstalacionResponse getInstalacion(@PathParam("numeroInstalacion") String numeroInstalacion){
		return bean.consultarInstalacionPorNumero(numeroInstalacion);
	}
	
	@POST
	@Path("crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse createInstalacion(Instalacion instalacion){
		return bean.crear(instalacion);
	}
	
	@POST
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse saveInstalacion(Instalacion instalacion){
		return bean.guardar(instalacion);
	}

}
