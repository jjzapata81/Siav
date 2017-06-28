package co.com.techandsolve.lazy.services.rest;

import java.io.File;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.techandsolve.lazy.bean.CalculatorBean;

@RequestScoped
@Path("lazy/")
public class LazyServices {
	
	@Inject
	private CalculatorBean bean;
	
	@POST
	@Path("file/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] getFile(File inputFile, @HeaderParam(value="cedula") String cedula){
		return bean.getFile(inputFile, cedula);
	}

}
