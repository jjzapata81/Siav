package co.com.siav.rest.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.MaterialesBean;
import co.com.siav.request.MaterialRequest;
import co.com.siav.response.MensajeResponse;


@RequestScoped
@Path("material/")
public class MaterialesServices {
	
	@Inject
	private MaterialesBean bean;
	
	@POST
	@Path("entrada/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse crearEntrada(MaterialRequest request){
		return bean.crearEntrada(request);
	}
	
	@POST
	@Path("salida/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse crear(MaterialRequest request){
		return bean.crearSalida(request);
	}

}
