package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Vereda;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryUsuarios;
import co.com.siav.repositories.IRepositoryVeredas;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.VeredaResponse;

@Stateless
public class VeredasBean {
	
	@Inject
	private IRepositoryVeredas veredasRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryUsuarios usuariosRep;
	
	public List<String> consultarNombres() {
		List<Vereda> veredasBD = veredasRep.findAll();
		return veredasBD.stream().map(Vereda::getNombre).collect(Collectors.toList());
	}
	
	public List<Vereda> consultar() {
		return veredasRep.findAll();
	}
	public List<VeredaResponse> consultarTodo() {
		return veredasRep.findAll().stream().map(this::transform).collect(Collectors.toList());
	}
	
	public MensajeResponse crear(String request) {
		if(null == veredasRep.findByNombre(request.trim())){
			Vereda vereda = new Vereda();
			vereda.setNombre(request);
			veredasRep.save(vereda);
			return new MensajeResponse("Se creó la vereda " + request);
		}
		return new MensajeResponse("Ya existe una vereda con ese nombre.");
	}
	
	private VeredaResponse transform(Vereda vereda){
		VeredaResponse response = new VeredaResponse();
		response.setCodigo(vereda.getCodigo());
		response.setNombre(vereda.getNombre());
		response.setCantidadUsuarios(usuariosRep.countByVeredaCodigoAndUsuario(vereda.getCodigo()));
		response.setCantidadInstalaciones(instalacionesRep.countByVeredaCodigo(vereda.getCodigo()));
		return response;
	}

}
