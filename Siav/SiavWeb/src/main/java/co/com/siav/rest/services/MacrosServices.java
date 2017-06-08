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

import co.com.siav.bean.MacrosBean;
import co.com.siav.entities.Macromedidor;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("macros/")
public class MacrosServices {
	
	@Inject
	private MacrosBean bean;
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Macromedidor> consultar(){
		return bean.consultar();
	}
	
	@POST
	@Path("guardar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse guardar(Macromedidor macro){
		return bean.guardar(macro);
	}
	
	@POST
	@Path("actualizar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse actualizar(Macromedidor macro){
		return bean.actualizar(macro);
	}

}
