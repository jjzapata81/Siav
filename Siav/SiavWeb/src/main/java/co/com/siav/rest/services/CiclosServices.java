package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.CiclosBean;
import co.com.siav.entities.Ciclo;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("ciclos/")
public class CiclosServices {
	
	@Inject
	private CiclosBean bean;
	
	@POST
	@Path("editar")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse editarCiclo(Ciclo request){
		return bean.editar(request);
	}
	
	@POST
	@Path("cerrar/{numeroCiclo}")
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse cerrarCiclo(@PathParam(value = "numeroCiclo") Long numeroCiclo){
		return bean.cerrar();
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public Ciclo getCiclo(){
		return bean.consultar();
	}
	
	@GET
	@Path("consultar/todo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ciclo> getTodosCiclos(){
		return bean.consultarTodos();
	}

}
