package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.bean.ReportesBean;
import co.com.siav.dto.ReporteLecturaConsumo;


@RequestScoped
@Path("reportes/")
public class ReportesServices {
	
	@Inject
	private ReportesBean bean;
	
	@GET
	@Path("buscar/{ciclo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReporteLecturaConsumo> getUsuario(@PathParam("ciclo") Long ciclo){
		return bean.consultar(ciclo);
	}

}
