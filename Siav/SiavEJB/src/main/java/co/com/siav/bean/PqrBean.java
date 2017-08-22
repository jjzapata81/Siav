package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Instalacion;
import co.com.siav.entities.Pqr;
import co.com.siav.entities.UsuarioSistema;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryMaestros;
import co.com.siav.repositories.IRepositoryPqr;
import co.com.siav.repositories.IRepositoryUsuarioSistema;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.PqrResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class PqrBean {
	
	@Inject
	private IRepositoryPqr pqrRep;
	

	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryUsuarioSistema usuarioSistemaRep;
	
	@Inject
	private IRepositoryMaestros mestrosRep;

	public List<PqrResponse> consultarTodo(){
		return pqrRep.findAll().stream().map(this::transform).collect(Collectors.toList());
	}

	public MensajeResponse crear(Pqr request) {
		try{
			UsuarioSistema usuario = usuarioSistemaRep.findByNombreUsuario(request.getNombreUsuario());
			Instalacion instalacion = instalacionesRep.findOne(request.getNumeroInstalacion());
			if(instalacion == null){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, request.getInstalacion().getNumeroInstalacion()));
			}
			request.setInstalacion(instalacion);
			request.setUsuario(usuario);
			pqrRep.save(request);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_CREAR_PQR + " " + e.getMessage());
		}
		
	}
	
	private PqrResponse transform(Pqr pqr){
		PqrResponse response = new PqrResponse();
		response.setId(pqr.getId());
		response.setNumeroInstalacion(pqr.getNumeroInstalacion());
		response.setDescripcion(pqr.getDescripcion());
		response.setEstado(getEstado(pqr.getEstado()));
		response.setFechaFin(pqr.getFechaFin());
		response.setFechaInicio(pqr.getFechaInicio());
		response.setNombreCompleto(pqr.getUsuario().getNombreCompleto());
		response.setNombreUsuario(pqr.getNombreUsuario());
		return response;
	}

	private String getEstado(Long estado) {
		return mestrosRep.findByCodigoAndGrupo(String.valueOf(estado), Constantes.PQR_ESTADO).getValor();
	}

}
