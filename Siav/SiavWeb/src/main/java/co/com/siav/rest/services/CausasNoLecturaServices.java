package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.CausasNoLecturaBean;
import co.com.siav.dto.CausasNoLecturaDTO;
import co.com.siav.entities.CausaNoLectura;
import co.com.siav.response.CausasResponse;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("nolectura/")
public class CausasNoLecturaServices {
	
	@Inject
	private CausasNoLecturaBean bean;
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CausaNoLectura> getCausas(){
		return bean.consultar();
	}
	
	@GET
	@Path("consultar/activo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CausaNoLectura> getCausasActivas(){
		return bean.consultarActivas();
	}
	
	@GET
	@Path("consultar/descripciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getDescripciones(){
		return bean.consultarDescripciones();
	}
	
	@PUT
	@Path("activar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void activar(CausaNoLectura causa){
		bean.activar(causa.getCodigo());
	}
	
	@GET
	@Path("movil/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public CausasResponse getCausasMovil(){
		CausasResponse causas = new CausasResponse();
		causas.setCausas(bean.consultarActivas());
		return causas;
	}
	
	@POST
	@Path("crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse createCausa(CausasNoLecturaDTO request){
		return bean.crear(request);
	}

}
