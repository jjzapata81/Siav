package co.com.siav.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.file.excel.dto.SalidaArticuloExcel;
import co.com.siav.repository.report.SalidaMaterialesRepository;

@RequestScoped
@Path("material/")
public class EntradaMateriales {
	
	@Inject
	private SalidaMaterialesRepository repository;
	
	@POST
	@Path("excel")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] descargarCuentasVencidas(List<SalidaArticuloExcel> excel){
		return repository.download(excel);
	}

}
