package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.UsuariosInstalaciones;
import co.com.siav.repository.report.AbonoRepository;
import co.com.siav.repository.report.UsuarioRepository;
import co.com.siav.rest.request.AbonoRequest;
import co.com.siav.rest.request.MatriculaRequest;

@RequestScoped
@Path("general/")
public class GeneralServices {
	
	@Inject
	private AbonoRepository repository;
	
	@Inject
	private UsuarioRepository usuarioRep;
	
	@POST
	@Path("abono/pdf")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] pdfAbono(@HeaderParam("siav_usuario") String usuario, AbonoRequest request){
		return repository.getPDF(usuario, request);
	}
	
	@POST
	@Path("abono/matricula/pdf")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] pdfMatricula(@HeaderParam("siav_usuario") String usuario, MatriculaRequest request){
		return repository.getMatriculaPDF(usuario, request);
	}
	
	@POST
	@Path("usuario/instalaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UsuariosInstalaciones> getUsuarios(Filter filtro){
		return usuarioRep.getUsuarios(filtro);
	}
	
	
	
}
