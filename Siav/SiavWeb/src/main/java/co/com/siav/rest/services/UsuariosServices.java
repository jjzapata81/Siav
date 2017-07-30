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

import co.com.siav.bean.UsuariosBean;
import co.com.siav.entities.Usuario;
import co.com.siav.request.UsuarioRequest;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.UsuarioInfo;


@RequestScoped
@Path("usuario/")
public class UsuariosServices {
	
	@Inject
	private UsuariosBean bean;
	
	@GET
	@Path("buscar/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("cedula") String cedula){
		return bean.consultarPorCedula(cedula);
	}
	
	@GET
	@Path("buscar/info/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioInfo getInfo(@PathParam("cedula") String cedula){
		return bean.consultarInfo(cedula);
	}
	
	@POST
	@Path("buscar/nombre")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarioPorNombre(UsuarioRequest request){
		return bean.consultarPorNombre(request);
	}
	
	@POST
	@Path("guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse saveUsuario(Usuario request){
		return bean.guardar(request);
	}
	
	@POST
	@Path("actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse updateUsuario(Usuario request){
		return bean.actualizar(request);
	}

}
