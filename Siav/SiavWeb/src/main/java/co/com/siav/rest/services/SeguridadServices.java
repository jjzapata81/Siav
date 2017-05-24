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

import co.com.siav.bean.UsuarioSistemaBean;
import co.com.siav.entities.UsuarioSistema;
import co.com.siav.request.CambioClaveRequest;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.PerfilResponse;
import co.com.siav.response.UsuarioSistemaResponse;


@RequestScoped
@Path("seguridad/")
public class SeguridadServices {
	
	@Inject
	private UsuarioSistemaBean bean;
	
	@POST
	@Path("usuario/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse crearUsuario(UsuarioSistema request){
		return bean.crear(request);
	}
	
	@POST
	@Path("usuario/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse actualizarUsuario(UsuarioSistema request){
		return bean.actualizar(request);
	}
	
	@GET
	@Path("usuario/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioSistemaResponse> getUsuarios(){
		return bean.consultarUsuarios();
	}
	
	@GET
	@Path("perfiles/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PerfilResponse> getPerfiles(){
		return bean.consultarPerfiles();
	}
	
	@PUT
	@Path("estado")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cambioEstado(UsuarioSistema usuario){
		bean.cambioEstado(usuario);
	}
	
	@POST
	@Path("clave/cambiar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse cambiarClave(CambioClaveRequest request){
		return bean.cambiarClave(request);
	}
	
}
