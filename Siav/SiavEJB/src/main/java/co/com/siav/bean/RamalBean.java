package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Ramal;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryRamal;
import co.com.siav.repositories.IRepositoryUsuarios;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.RamalResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class RamalBean {
	
	@Inject
	private IRepositoryRamal ramalRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryUsuarios usuariosRep;

	public List<Ramal> consultar() {
		return ramalRep.findAll();
	}


	public List<RamalResponse> consultarTodo() {
		return ramalRep.findAll().stream().map(this::transform).collect(Collectors.toList());
	}
	
	private RamalResponse transform(Ramal ramal){
		RamalResponse response = new RamalResponse();
		response.setCodigoRamal(ramal.getCodigoRamal());
		response.setNombre(ramal.getNombre());
		response.setUsuarios(usuariosRep.countByRamalAndUsuario(ramal.getCodigoRamal()));
		response.setInstalaciones(instalacionesRep.countByRamal(ramal.getCodigoRamal()));
		return response;
	}


	public MensajeResponse crear(Ramal request) {
		if(ramalRep.exists(request.getCodigoRamal())){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_COD_RAMAL_EXISTE);
		}
		if(!ramalRep.findByNombre(request.getNombre()).isEmpty()){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_NOMBRE_RAMAL_EXISTE);
		}
		try{
			ramalRep.save(request);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}


	public MensajeResponse actualizar(Ramal request) {
		try{
			ramalRep.save(request);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	

}
