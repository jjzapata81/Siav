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

import co.com.siav.bean.CreditosBean;
import co.com.siav.request.CreditoRequest;
import co.com.siav.response.CreditoRefinanciar;
import co.com.siav.response.CreditoResponse;


@RequestScoped
@Path("creditos/")
public class CreditosServices {
	
	@Inject
	private CreditosBean bean;
	
	@GET
	@Path("buscar/{instalacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public CreditoResponse buscarCreditos(@PathParam(value="instalacion") Long instalacion){
		return bean.buscar(instalacion);
	}
	
	@PUT
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarCredito(CreditoRequest request){
		bean.guardar(request);
	}
	
	@PUT
	@Path("refinanciar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void refinanciarCredito(CreditoRefinanciar request){
		bean.refinanciar(request);
	}

}
