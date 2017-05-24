package co.com.siav.rest.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.FacturacionBean;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("facturacion/")
public class FacturacionServices {
	
	@Inject
	private FacturacionBean bean;
	
	@GET
	@Path("iniciar")
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse facturar(){
		return bean.facturar();
	}

}
