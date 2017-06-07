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

import co.com.siav.bean.SistemaBean;
import co.com.siav.entities.Empresa;
import co.com.siav.entities.Estructura;
import co.com.siav.request.EstructuraRequest;
import co.com.siav.response.EstructuraResponse;
import co.com.siav.response.IpResponse;
import co.com.siav.response.MensajeResponse;


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
	
	@GET
	@Path("consultar/empresa")
	@Produces(MediaType.APPLICATION_JSON)
	public Empresa getEmpresa(){
		return bean.consultarEmpresa();
	}
	
	@GET
	@Path("consultar/estructura")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EstructuraResponse> getEstructura(){
		return bean.consultarEstructura();
	}
	
	@POST
	@Path("actualizar/empresa")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse actualizarEmpresa(Empresa request){
		return bean.actualizarEmpresa(request);
	}
	
	@POST
	@Path("agregar/junta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse agregarJunta(Estructura request){
		return bean.agregarJunta(request);
	}
	
	@POST
	@Path("actualizar/junta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse actualizarJunta(EstructuraRequest request){
		return bean.actualizarJunta(request);
	}

}
