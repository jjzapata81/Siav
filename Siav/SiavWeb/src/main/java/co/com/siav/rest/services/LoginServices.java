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

import co.com.siav.bean.LoginBean;
import co.com.siav.dto.LoginDTO;
import co.com.siav.entities.Perfil;
import co.com.siav.response.LoginResponse;
import co.com.siav.response.UsuarioMovilResponse;

@RequestScoped
@Path("login/")
public class LoginServices {
	
	@Inject
	private LoginBean bean;
	
	@PUT
	@Path("ingresar")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse ingresar(LoginDTO request){
		return bean.ingresar(request);
	}
	
	@POST
	@Path("movil/autenticar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioMovilResponse validarUsuarioMovil(LoginDTO request){
		return bean.validarUsuarioMovil(request);
	}
	
	@GET
	@Path("perfiles/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Perfil> getPerfiles(){
		return bean.getRoles();
	}

	
}
