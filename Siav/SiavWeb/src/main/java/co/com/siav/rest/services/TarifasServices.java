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

import co.com.siav.bean.TarifasBean;
import co.com.siav.entities.Tarifa;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("tarifas/")
public class TarifasServices {
	
	@Inject
	private TarifasBean bean;
	
	@POST
	@Path("crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse createTarifa(Tarifa request){
		return bean.crear(request);
	}
	
	@POST
	@Path("editar")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse editTarifa(Tarifa request){
		return bean.editar(request);
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tarifa> getTarifas(){
		return bean.consultar();
	}
	
	@GET
	@Path("consultar/activas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tarifa> getTarifasActivas(){
		return bean.consultarActivas();
	}
	
	@GET
	@Path("consultar/credito")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tarifa> getTarifaCreidot(){
		return bean.consultarTarifaCredito();
	}
	
	@GET
	@Path("consultar/descripciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getDescripciones(){
		return bean.consultarDescripciones();
	}
	
	@GET
	@Path("buscar/{codigoTarifa}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tarifa getTarifa(@PathParam("codigoTarifa") String codigoTarifa){
		return bean.consultarPorCodigo(codigoTarifa);
	}

}
