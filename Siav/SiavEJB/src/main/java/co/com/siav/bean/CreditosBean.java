package co.com.siav.bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.entities.Comprobante;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.Instalacion;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryComprobante;
import co.com.siav.repositories.IRepositoryCreditoDetalle;
import co.com.siav.repositories.IRepositoryCreditoMaestro;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryTarifas;
import co.com.siav.request.CreditoRequest;
import co.com.siav.response.CreditoMaestroResponse;
import co.com.siav.response.CreditoResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class CreditosBean {
	
	@Inject
	private IRepositoryCreditoMaestro creditoMaestroRep;
	
	@Inject
	private IRepositoryCreditoDetalle creditoDetalleRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject 
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private IRepositoryComprobante comprobanteRep;
	
	@Inject
	private IRepositoryTarifas tarifasRep;

	public void guardar(CreditoRequest request) {
		validarComprobante(request);
		CreditoMaestro credito = new CreditoMaestro();
		BeanUtils.copyProperties(request, credito);
		credito.setInicial(null == request.getInicial() ? 0L : request.getInicial());
		credito.setInteres(null == request.getInteres() ? 0D : request.getInteres());
		credito.setFecha(new Date());
		credito.setCiclo(ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO));
		credito.setSaldo(request.getValor() - request.getInicial());
		credito.setActual(0L);
		creditoMaestroRep.save(credito);
	}

	private void validarComprobante(CreditoRequest request) {
		Comprobante comprobantePago;
		if(null != request.getComprobante()){
			comprobantePago = comprobanteRep.findOne(request.getComprobante());
			if(null == comprobantePago){
				throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_FALTA_COMPROBANTE, request.getComprobante()));
//			}else if(!comprobantePago.getEsMatricula()){
//				throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_COMPROBANTE_NO_MATRICULA, request.getComprobante()));
			}else if(!request.getInicial().equals(comprobantePago.getValor())){
				throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_COMPROBANTE_VALOR, comprobantePago.getValor(), request.getInicial()));
			}
		}
	}

	public CreditoResponse buscar(Long numeroInstalacion) {
		Instalacion instalacion = instalacionesRep.findOne(numeroInstalacion);
		if(null==instalacion){
			throw new ExcepcionNegocio(Constantes.ERR_SIN_INSTALACION);
		}
		List<CreditoMaestro> creditos = creditoMaestroRep.findByInstalacionSinCancelar(numeroInstalacion);
		if(null == creditos || creditos.isEmpty()){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_NO_HAY_CREDITOS, numeroInstalacion));
		}
		CreditoResponse response = new CreditoResponse();
		response.setNumeroInstalacion(instalacion.getNumeroInstalacion());
		response.setNombreCompleto(instalacion.getUsuario().getNombreCompleto());
		response.setCedula(instalacion.getUsuario().getCedula());
		response.setCreditos(buildCreditos(creditos));
		return response;
	}

	private List<CreditoMaestroResponse> buildCreditos(List<CreditoMaestro> creditos) {
		return creditos.stream().map(this::transform).collect(Collectors.toList());
	}
	
	private CreditoMaestroResponse transform(CreditoMaestro creditoBD){
		CreditoMaestroResponse credito = new CreditoMaestroResponse();
		credito.setConcepto(tarifasRep.findOne(creditoBD.getCodigoConcepto()));
		credito.setActual(creditoBD.getActual());
		credito.setNumeroCuotas(creditoBD.getNumeroCuotas());
		credito.setValor(creditoBD.getValor());
		credito.setSaldo(creditoBD.getSaldo());
		credito.setInteres(creditoBD.getInteres());
		credito.setId(creditoBD.getId());
		return credito;
	}

	public void refinanciar(CreditoRequest request) {
		validarComprobante(request);
		CreditoMaestro creditoAnterior = creditoMaestroRep.findOne(request.getId());
		CreditoMaestro creditoNuevo = new CreditoMaestro();
		creditoNuevo.setInstalacion(creditoAnterior.getInstalacion());
		creditoNuevo.setCodigoConcepto(creditoAnterior.getCodigoConcepto());
		creditoNuevo.setFecha(new Date());
		creditoNuevo.setValor(request.getValor());
		creditoNuevo.setSaldo(request.getValor() - request.getInicial());
		creditoNuevo.setInicial(request.getInicial());
		creditoNuevo.setInteres(request.getInteres());
		creditoNuevo.setNumeroCuotas(request.getNumeroCuotas());
		creditoNuevo.setCiclo(ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO));
		creditoNuevo.setActual(0L);
		creditoNuevo.setReferencia(creditoAnterior.getId());
		creditoAnterior.setSaldo(0L);
		creditoAnterior.setEsFinanciado(true);
		creditoAnterior.setFechaFinal(creditoNuevo.getFecha());
		creditoMaestroRep.save(creditoNuevo);
		creditoMaestroRep.save(creditoAnterior);
		
	}

	public void eliminar(CreditoRequest request) {
		CreditoMaestro credito = creditoMaestroRep.findOne(request.getId());
		if(credito.getCuotas().size() > 1 || credito.getReferencia() != null){
			throw new ExcepcionNegocio(Constantes.ERR_ELIMINAR_CREDITO);
		}
		credito.getCuotas().stream().forEach(item -> creditoDetalleRep.delete(item));
		creditoMaestroRep.delete(credito);
		
	}

}
