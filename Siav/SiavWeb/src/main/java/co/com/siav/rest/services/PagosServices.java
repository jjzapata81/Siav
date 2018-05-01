package co.com.siav.rest.services;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.PagosBean;
import co.com.siav.entities.Pago;
import co.com.siav.request.FiltroRequest;
import co.com.siav.response.FacturaResponse;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.PagoResponse;

@RequestScoped
@Path("pagos")
public class PagosServices {
	
	@Inject
	private PagosBean bean;
	
	@POST
	@Path("/guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeResponse save(@HeaderParam("siav_usuario") String usuario, Pago request){
		return bean.guardar(request, usuario);
	}
	
	@POST
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PagoResponse> consultar(FiltroRequest request){
		return bean.consultar(request);
	}
	
	
	@POST
	@Path("/buscar/{numeroInstalacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public FacturaResponse buscar(@PathParam("numeroInstalacion") Long numeroInstalacion){
		return bean.buscar(numeroInstalacion);
	}
	
	@PUT
	@Path("/cargar")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public void cargarArchivo(@HeaderParam("nombreArchivo") String nombreArchivo, @HeaderParam("codigoBanco") String codigoBanco, @HeaderParam("siav_usuario") String usuario,  File archivoPagos) throws InstantiationException, IllegalAccessException, InvocationTargetException, URISyntaxException {
		bean.guardarArchivo(nombreArchivo, codigoBanco, usuario, archivoPagos);
	}
	
}
