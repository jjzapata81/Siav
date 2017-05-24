package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.ListasBean;
import co.com.siav.entities.Maestros;


@RequestScoped
@Path("listas/")
public class ListasServices {
	
	@Inject
	private ListasBean bean;
	
	@GET
	@Path("maestros/{nombreMaestro}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Maestros> getMaestro(@PathParam("nombreMaestro") String nombreMaestro){
		return bean.consultarMaestro(nombreMaestro);
	}

}
