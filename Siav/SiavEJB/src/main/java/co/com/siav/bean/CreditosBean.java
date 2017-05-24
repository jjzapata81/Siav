package co.com.siav.bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.Instalacion;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryCreditoMaestro;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryTarifas;
import co.com.siav.response.CreditoMaestroResponse;
import co.com.siav.response.CreditoRefinanciar;
import co.com.siav.response.CreditoResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class CreditosBean {
	
	@Inject
	private IRepositoryCreditoMaestro creditoMaestroRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryTarifas tarifasRep;

	public void guardar(CreditoMaestro request) {
		request.setInicial(null == request.getInicial() ? 0L : request.getInicial());
		request.setInteres(null == request.getInteres() ? 0D : request.getInteres());
		request.setFecha(new Date());
		request.setSaldo(request.getValor() - request.getInicial());
		request.setActual(0L);
		creditoMaestroRep.save(request);
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

	public void refinanciar(CreditoRefinanciar request) {
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
		creditoNuevo.setActual(0L);
		creditoAnterior.setSaldo(0L);
		creditoAnterior.setEsFinanciado(true);
		creditoAnterior.setFechaFinal(creditoNuevo.getFecha());
		creditoMaestroRep.save(creditoNuevo);
		creditoMaestroRep.save(creditoAnterior);
		
	}

}
