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

import co.com.siav.bean.CuentasBancosBean;
import co.com.siav.dto.CuentaBancoDTO;
import co.com.siav.entities.CuentaBanco;
import co.com.siav.response.MensajeResponse;

@RequestScoped
@Path("bancos/")
public class CuentasBancosServices {
	
	@Inject
	private CuentasBancosBean bean;
	
	@POST
	@Path("crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse createCuenta(CuentaBancoDTO request){
		return bean.crear(request);
	}
	
	@POST
	@Path("editar")
	@Consumes(MediaType.APPLICATION_JSON)
	public MensajeResponse editCuenta(CuentaBancoDTO request){
		return bean.editar(request);
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CuentaBanco> getCuentas(){
		return bean.consultar();
	}
	
}
