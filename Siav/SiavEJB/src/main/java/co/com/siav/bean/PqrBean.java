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
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.notificador.GeneradorHTML;
import co.com.siav.notificador.NotificationBean;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryMaestros;
import co.com.siav.repositories.IRepositoryParametros;
import co.com.siav.repositories.IRepositoryPqr;
import co.com.siav.repositories.IRepositoryPqrDetalle;
import co.com.siav.repositories.IRepositoryUsuarioSistema;
import co.com.siav.request.PqrRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.PqrConsultaResponse;
import co.com.siav.response.PqrDetalleResponse;
import co.com.siav.response.PqrResponse;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.Utilidades;

@Stateless
public class PqrBean {
	
	@Inject
	private IRepositoryPqr pqrRep;
	
	@Inject
	private IRepositoryPqrDetalle pqrDetalleRep;

	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryUsuarioSistema usuarioSistemaRep;
	
	@Inject
	private IRepositoryParametros parametrosRep;
	
	@Inject
	private IRepositoryMaestros mestrosRep;

	public List<PqrResponse> consultarTodo(){
		return pqrRep.findAll().stream().map(this::transform).collect(Collectors.toList());
	}

	public MensajeResponse crear(PqrRequest request) {
		validar(request, false);
		try{
			UsuarioSistema usuario = usuarioSistemaRep.findByNombreUsuario(request.getNombreUsuario());
			Instalacion instalacion = instalacionesRep.findOne(request.getNumeroInstalacion());
			if(instalacion == null){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, request.getNumeroInstalacion()));
			}
			Long id = pqrRep.findMaxPqr() + 1L;
			Pqr pqr = new Pqr();
			pqr.setId(id);
			PqrDetalle detalle = crearDetalle(request, id);
			List<PqrDetalle> detalles = Arrays.asList(detalle);
			pqr.setInstalacion(instalacion);
			pqr.setNumeroInstalacion(request.getNumeroInstalacion());
			pqr.setNombreUsuario(request.getNombreUsuario());
			pqr.setUsuario(usuario);
			pqr.setDetalles(detalles);
			pqr.setDescripcion(request.getDescripcion());
			pqrRep.save(pqr);
			notificar(pqr, detalle);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_CREAR_PQR + " " + e.getMessage());
		}
		
	}

	public PqrConsultaResponse consultarDetalle(String user, Long idPqr) {
		PqrConsultaResponse response = new PqrConsultaResponse();
		response.setDetalles(pqrRep.findOne(idPqr).getDetalles().stream().map(this::toDetalleResponse).sorted((a, b)-> Long.compare(b.getId(), a.getId())).collect(Collectors.toList()));
		PqrDetalleResponse primerRegistro = response.getDetalles().stream().findFirst().orElse(null);
		response.setPuedeEditar(null == primerRegistro || user.equals(primerRegistro.getUsuario().getNombreUsuario()));
		return response;
	}
	
	public MensajeResponse actualizar(PqrRequest request) {
		validar(request, true);
		try{
			Pqr pqrBD = pqrRep.findOne(request.getId());
			pqrBD.setEstado(request.getEstado());
			if(getEstado(request.getEstado()).equals(Constantes.CERRADO)){
				pqrBD.setFechaFin(new Date());
			}
			PqrDetalle detalle = crearDetalle(request, request.getId());
			pqrDetalleRep.save(detalle);
			pqrRep.save(pqrBD);
			notificar(pqrBD, detalle);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_CREAR_PQR + " " + e.getMessage());
		}
	}
	
	private void notificar(Pqr pqr, PqrDetalle detalle) {
		if(detalle.getUsuario().getEmail() != null && !detalle.getUsuario().getEmail().trim().equals("")){
			String from = getParametro(Constantes.EMAIL_ENVIO_CORREO);
			String pass = getParametro(Constantes.PASS_ENVIO_CORREO);
			String alias = getParametro(Constantes.ALIAS_ENVIO_CORREO);
			NotificationBean notification = new NotificationBean(from, alias, pass);
			notification.sendHtml(new GeneradorHTML(
					Utilidades.getMensajePQR(detalle.getIdPqr(), pqr.getDescripcion(), getInfo(pqr),
							getEstado(pqr.getEstado()), detalle.getAccion())),
					Constantes.NOTIFICACION_ASUNTO, detalle.getUsuario().getEmail());
		}
	}

	private String getInfo(Pqr pqr) {
		return String.format("%d - %s", pqr.getInstalacion().getNumeroInstalacion(), pqr.getInstalacion().getUsuario().getNombreCompleto());
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
		response.setUsuarioAsignado(pqr.getDetalles().stream().sorted((a,b)->Long.compare(b.getId(), a.getId())).findFirst().orElse(new PqrDetalle()).getUsuario().getNombreCompleto());
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
	
	private String getParametro(String parametro){
		return parametrosRep.findByCdParametro(parametro).getDsValor();
	}
	
	private void validar(PqrRequest request, boolean esActualizacion) {
		if(request.getNumeroInstalacion() == null){
			throw new ExcepcionNegocio(Constantes.ERR_PQR_NUMERO_INSTALACION);
		}
		if(request.getNombreUsuario() == null){
			throw new ExcepcionNegocio(Constantes.ERR_PQR_USUARIO_CREACION);
		}
		if(request.getUsuarioAsignar() == null){
			throw new ExcepcionNegocio(Constantes.ERR_PQR_USUARIO_ASIGNACION);
		}
		if(request.getDescripcion() == null){
			throw new ExcepcionNegocio(Constantes.ERR_PQR_DESCRIPCION);
		}
		if(esActualizacion && request.getEstado() == null){
			throw new ExcepcionNegocio(Constantes.ERR_PQR_ESTADO);
		}
		if(request.getAccion() == null){
			throw new ExcepcionNegocio(Constantes.ERR_PQR_ACCION);
		}
	}


}
