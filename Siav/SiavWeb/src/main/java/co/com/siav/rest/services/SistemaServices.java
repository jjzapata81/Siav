package co.com.siav.rest.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.SistemaBean;
import co.com.siav.response.IpResponse;


@RequestScoped
@Path("general/")
public class SistemaServices {
	
	@Inject
	private SistemaBean bean;
	
	@GET
	@Path("consultar/ip")
	@Produces(MediaType.APPLICATION_JSON)
	public IpResponse getIp(){
		return bean.consultarIp();
	}

}
