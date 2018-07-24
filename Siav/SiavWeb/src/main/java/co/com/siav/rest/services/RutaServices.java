package co.com.siav.rest.services;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.com.siav.bean.RutaBean;
import co.com.siav.dto.ConfiguracionRuta;
import co.com.siav.entities.UsuarioRamal;
import co.com.siav.request.UsuarioRamalRequest;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("ruta/")
public class RutaServices {
	
	@Inject
	private RutaBean bean;
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfiguracionRuta> consultar(){
		return bean.consultar();
	}
	
	@GET
	@Path("consultar/usuario/sistema")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioRamal> consultarUsuarioSistema(){
		return bean.consultarUsuarioSistema();
	}
	
	@GET
	@Path("consultar/{numeroInstalacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConfiguracionRuta consultarPorNumero(@PathParam(value="numeroInstalacion") Long numeroInstalacion){
		return bean.consultarPorNumero(numeroInstalacion);
	}
	
	@POST
	@Path("guardar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse guardar(ConfiguracionRuta request){
		return bean.guardar(request);
	}
	
	@POST
	@Path("actualizar/usuario/sistema")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse actualizarUsuarioSistema(UsuarioRamalRequest request){
		return bean.actualizarUsuarioSistema(request);
	}
	

}
