package co.com.siav.rest.services;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.rest.config.error.AdministradorExcepciones;

@ApplicationScoped
@Path("log/")
public class ServicioLog {
	
	@GET
	@Path("consultar")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPaquetes(){
		return AdministradorExcepciones.getErroLogs();
	}
	
}
