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

import co.com.siav.bean.ArticuloBean;
import co.com.siav.entities.Articulo;
import co.com.siav.request.ArticuloRequest;
import co.com.siav.response.ArticuloResponse;
import co.com.siav.response.MensajeResponse;

@RequestScoped
@Path("articulo/")
public class ArticuloServices {
	
	@Inject
	private ArticuloBean bean;
	
	@POST
	@Path("crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse crear(ArticuloRequest request){
		return bean.crear(request);
	}
	
	@POST
	@Path("actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse actualizar(ArticuloRequest request){
		return bean.actualizar(request);
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArticuloResponse> consultar(){
		return bean.consultar();
	}
	
	@GET
	@Path("consultar/entradas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArticuloResponse> consultarEntradas(){
		return bean.consultarEntradas();
	}
	
	@GET
	@Path("consultar/salidas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArticuloResponse> consultarSalidas(){
		return bean.consultarSalidas();
	}
	
	@GET
	@Path("consultar/nombres")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getNombres(){
		return bean.consultarNombres();
	}
	
}
