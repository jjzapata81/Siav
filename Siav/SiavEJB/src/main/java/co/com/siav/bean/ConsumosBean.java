package co.com.siav.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Ciclo;
import co.com.siav.entities.Consumo;
import co.com.siav.entities.ConsumoAuditoria;
import co.com.siav.entities.ConsumoPK;
import co.com.siav.entities.Instalacion;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryConsumos;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.request.CorreccionConsumoRequest;
import co.com.siav.request.FiltroRequest;
import co.com.siav.response.ConsumoAuditoriaResponse;
import co.com.siav.response.ConsumoRiesgo;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class ConsumosBean {
	
	@Inject
	private IRepositoryConsumos consumosRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionRep;
	
	@Inject
	private CiclosBean ciclosBean;
	
	@Inject
	private AuditoriaBean audiBean;
	
	private Ciclo cicloAnterior;

	public List<Consumo> consultarIncompletos() {
		Ciclo cicloAbierto = ciclosBean.getPorEstado(Constantes.ABIERTO);
		return consumosRep.findByCicloAndIncompletos(cicloAbierto.getCiclo());
	}

	public MensajeResponse guardar(CorreccionConsumoRequest request, String usuario) {
		try{
			Ciclo cicloAbierto = ciclosBean.getPorEstado(Constantes.ABIERTO);
			ConsumoPK pk = new ConsumoPK();
			pk.setCiclo(cicloAbierto.getCiclo());
			pk.setInstalacion(request.getNumeroInstalacion());
			pk.setSerieMedidor(request.getAntiguoMedidor());
			Consumo consumoBD = consumosRep.findOne(pk);
			consumoBD.setConsumoDefinitivo(request.getConsumo());
			consumoBD.setLecturaActualCorregido(request.getLecturaCorregida());
			consumoBD.setAjustado(true);
			consumosRep.save(consumoBD);
			consumosRep.updateMedidor(cicloAbierto.getCiclo(), request.getNumeroInstalacion(), request.getNuevoMedidor());
			Instalacion instalacion = instalacionRep.findOne(request.getNumeroInstalacion());
			instalacion.setSerieMedidor(request.getNuevoMedidor());
			instalacionRep.save(instalacion);
			audiBean.guardar(cicloAbierto.getCiclo(), instalacion.getNumeroInstalacion(), usuario, 
					consumoBD.getConsumoMes(), request.getConsumo(), null, null, request.getObservacion());
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
	
	public MensajeResponse guardarCorreccionConsumo(CorreccionConsumoRequest request, String usuario) {
		try{
			Ciclo cicloAbierto = ciclosBean.getPorEstado(Constantes.ABIERTO);
			ConsumoPK pk = new ConsumoPK();
			pk.setCiclo(cicloAbierto.getCiclo());
			pk.setInstalacion(request.getNumeroInstalacion());
			pk.setSerieMedidor(request.getAntiguoMedidor());
			Consumo consumoBD = consumosRep.findOne(pk);
			audiBean.guardar(cicloAbierto.getCiclo(), request.getNumeroInstalacion(), usuario, 
					consumoBD.getConsumoMes(), request.getConsumo(), null, null, request.getObservacion());
			consumoBD.setConsumoDefinitivo(request.getConsumo());
			consumoBD.setAjustado(true);
			consumosRep.save(consumoBD);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
	
	public MensajeResponse guardarCorreccionLectura(CorreccionConsumoRequest request, String usuario) {
		try{
			Ciclo cicloAbierto = ciclosBean.getPorEstado(Constantes.ABIERTO);
			ConsumoPK pk = new ConsumoPK();
			pk.setCiclo(cicloAbierto.getCiclo());
			pk.setInstalacion(request.getNumeroInstalacion());
			pk.setSerieMedidor(request.getAntiguoMedidor());
			Consumo consumoBD = consumosRep.findOne(pk);
			audiBean.guardar(cicloAbierto.getCiclo(), request.getNumeroInstalacion(), usuario, 
					consumoBD.getConsumoMes(), request.getConsumo(), consumoBD.getLecturaActual(), request.getLecturaCorregida(), request.getObservacion());
			consumoBD.setConsumoDefinitivo(request.getConsumo());
			consumoBD.setLecturaActualCorregido(consumoBD.getLecturaActual());
			consumoBD.setLecturaActual(request.getLecturaCorregida());
			consumoBD.setAjustado(true);
			consumosRep.save(consumoBD);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

	public List<ConsumoRiesgo> consultarRango(CorreccionConsumoRequest request) {
		Ciclo ciclo = ciclosBean.getPorEstado(Constantes.ABIERTO);
		cicloAnterior = ciclosBean.getPorEstado(Constantes.CERRADO);
		return consumosRep.findByRango(ciclo.getCiclo(), (-1L)*request.getConsumo(), request.getConsumo()).stream().map(this::transform).collect(Collectors.toList());
	}
	
	private ConsumoRiesgo transform(Consumo consumoBD){
		ConsumoRiesgo consumo = new ConsumoRiesgo();
		consumo.setInstalacion(consumoBD.getId().getInstalacion());
		consumo.setNombre(consumoBD.getInstalacion().getUsuario().getNombreCompleto());
		consumo.setRamal(consumoBD.getInstalacion().getRamal());
		consumo.setSerieMedidor(consumoBD.getInstalacion().getSerieMedidor());
		consumo.setLecturaAnterior(consumoBD.getLecturaAnterior());
		consumo.setLecturaActual(consumoBD.getLecturaActual());
		consumo.setConsumoAnterior(consumosRep.findByIdInstalacionAndIdCiclo(consumoBD.getId().getInstalacion(), cicloAnterior.getCiclo()).getConsumoDefinitivo());
		consumo.setConsumoActual(consumoBD.getConsumoDefinitivo());
		consumo.setConsumoPromedio(consumoBD.getConsumoPromedio());
		consumo.setFecha(consumoBD.getFechaHasta());
		consumo.setDiferencia(consumo.getConsumoActual() - consumo.getConsumoAnterior());
		return consumo;
	}

	public List<ConsumoAuditoriaResponse> consultarAuditoria(FiltroRequest request) {
		Ciclo ciclo = ciclosBean.getPorEstado(Constantes.ABIERTO);
		if(request.getNumeroDesde() == null)
			request.setNumeroDesde(ciclo.getCiclo());
		if(request.getNumeroHasta() == null)
			request.setNumeroHasta(ciclo.getCiclo());
		List<ConsumoAuditoria> consumos = audiBean.consultarRiesgo(request.getNumeroDesde(), request.getNumeroHasta());
		if(consumos.isEmpty()){
			throw new ExcepcionNegocio(Constantes.ERR_CONSULTA);
		}
		List<ConsumoAuditoriaResponse> response = new ArrayList<ConsumoAuditoriaResponse>();
		consumos.stream().collect(Collectors.groupingBy(ConsumoAuditoria::getCiclo)).forEach((key, value) -> response.add(new ConsumoAuditoriaResponse(key, value)));
		return response;
	}
	
	public List<Consumo> porInstalacion(Long numeroInstalacion) {
		return consumosRep.findByIdInstalacion(numeroInstalacion);
	}

}
