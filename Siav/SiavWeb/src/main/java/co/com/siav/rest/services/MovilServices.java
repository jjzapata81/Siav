package co.com.siav.rest.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.MovilBean;
import co.com.siav.request.ConsumosRequest;
import co.com.siav.response.ConsumosResponse;
import co.com.siav.response.MacrosResponse;


@RequestScoped
@Path("movil/")
public class MovilServices {
	
	@Inject
	private MovilBean bean;
	
		
	@GET
	@Path("consumos/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsumosResponse getConsumos(){
		return new ConsumosResponse(bean.consultarConsumos());
	}
	
	@GET
	@Path("macros/consultar/{usuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public MacrosResponse getMacros(@PathParam(value="usuario") String usuario){
		return bean.consultarMacrosPorUsuario(usuario);
	}
	
	
	@PUT
	@Path("consumos/guardar/{usuario}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveConsumos(@PathParam(value="usuario") String usuario, ConsumosRequest request){
		bean.guardarConsumos(usuario, request);
	}
	

}
