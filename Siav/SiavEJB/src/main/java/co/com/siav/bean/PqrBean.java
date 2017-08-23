package co.com.siav.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Instalacion;
import co.com.siav.entities.Pqr;
import co.com.siav.entities.PqrDetalle;
import co.com.siav.entities.UsuarioSistema;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryMaestros;
import co.com.siav.repositories.IRepositoryPqr;
import co.com.siav.repositories.IRepositoryUsuarioSistema;
import co.com.siav.request.PqrRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.PqrDetalleResponse;
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

	public MensajeResponse crear(PqrRequest request) {
		try{
			UsuarioSistema usuario = usuarioSistemaRep.findByNombreUsuario(request.getNombreUsuario());
			Instalacion instalacion = instalacionesRep.findOne(request.getNumeroInstalacion());
			if(instalacion == null){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, request.getNumeroInstalacion()));
			}
			Long id = pqrRep.findMaxPqr() + 1L;
			Pqr pqr = new Pqr();
			pqr.setId(id);
			List<PqrDetalle> detalles = Arrays.asList(crearDetalle(request, id));
			pqr.setInstalacion(instalacion);
			pqr.setNumeroInstalacion(request.getNumeroInstalacion());
			pqr.setNombreUsuario(request.getNombreUsuario());
			pqr.setUsuario(usuario);
			pqr.setDetalles(detalles);
			pqr.setDescripcion(request.getDescripcion());
			pqrRep.save(pqr);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_CREAR_PQR + " " + e.getMessage());
		}
		
	}

	public List<PqrDetalleResponse> consultarDetalle(Long idPqr) {
		return pqrRep.findOne(idPqr).getDetalles().stream().map(this::toDetalleResponse).collect(Collectors.toList());
	}
	
	public MensajeResponse actualizar(PqrRequest request) {
		try{
			Pqr pqrBD = pqrRep.findOne(request.getId());
			pqrBD.setEstado(request.getEstado());
			if(getEstado(request.getEstado()).equals(Constantes.CERRADO)){
				pqrBD.setFechaFin(new Date());
			}
			pqrBD.getDetalles().add(crearDetalle(request, request.getId()));
			pqrRep.save(pqrBD);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_CREAR_PQR + " " + e.getMessage());
		}
	}
	
	private PqrDetalle crearDetalle(PqrRequest request, Long id) {
		PqrDetalle detalle = new PqrDetalle();
		detalle.setIdPqr(id);
		detalle.setAccion(request.getAccion());
		detalle.setUsuario(request.getUsuarioAsignar());
		detalle.setUsuarioAsignar(request.getUsuarioAsignar().getNombreUsuario());
		detalle.setFechaAccion(new Date());
		return detalle;
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
	
	private PqrDetalleResponse toDetalleResponse(PqrDetalle detalleBD){
		PqrDetalleResponse response = new PqrDetalleResponse();
		response.setId(detalleBD.getId());
		response.setIdPqr(detalleBD.getIdPqr());
		response.setFechaAccion(detalleBD.getFechaAccion());
		response.setAccion(detalleBD.getAccion());
		response.setUsuario(detalleBD.getUsuario());
		return response;
	}

}
