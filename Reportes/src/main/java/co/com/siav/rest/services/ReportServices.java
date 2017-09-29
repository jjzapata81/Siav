package co.com.siav.rest.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.siav.reports.factory.ReportFactory;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.report.EnvioFacturaRepository;

@RequestScoped
@Path("reports/")
public class ReportServices {
	
	@Inject
	private ReportFactory factory;
	
	@Inject
	private EnvioFacturaRepository envioRep;
	
	@POST
	@Path("{reporte}/pdf")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] pdfCuentasVencidas(@PathParam(value = "reporte") String reporte, Filter filter){
		return factory.getInstance(reporte).getPDF(filter);
	}
	
	@POST
	@Path("{reporte}/download")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] descargarCuentasVencidas(@PathParam(value = "reporte") String reporte, Filter filter){
		return factory.getInstance(reporte).download(filter);
	}
	
	@PUT
	@Path("{reporte}/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendCuentasVencidas(@PathParam(value = "reporte") String reporte, Filter filter){
		factory.getInstance(reporte).send(filter);
	}
	
	@GET
	@Path("mail")
	@Produces(MediaType.APPLICATION_JSON)
	public void sendMail(){
		envioRep.send();
	}
	
}
