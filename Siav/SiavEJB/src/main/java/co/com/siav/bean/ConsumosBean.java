package co.com.siav.bean;

import java.util.ArrayList;
import java.util.Date;
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
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryConsumoAuditoria;
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
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private IRepositoryConsumoAuditoria audiRep;
	
	private Long cicloAnterior;

	public List<Consumo> consultarIncompletos() {
		Ciclo cicloAbierto = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
		return consumosRep.findByCicloAndIncompletos(cicloAbierto.getCiclo());
	}

	public MensajeResponse guardar(CorreccionConsumoRequest request, String usuario) {
		try{
			Ciclo cicloAbierto = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
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
			Instalacion instalacion = instalacionesRep.findOne(request.getNumeroInstalacion());
			instalacion.setSerieMedidor(request.getNuevoMedidor());
			instalacionesRep.save(instalacion);
			guardarAuditoria(cicloAbierto.getCiclo(), instalacion.getNumeroInstalacion(), usuario, 
					consumoBD.getConsumoMes(), request.getConsumo(), null, null, request.getObservacion());
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
	
	public MensajeResponse guardarCorreccionConsumo(CorreccionConsumoRequest request, String usuario) {
		try{
			Ciclo cicloAbierto = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
			ConsumoPK pk = new ConsumoPK();
			pk.setCiclo(cicloAbierto.getCiclo());
			pk.setInstalacion(request.getNumeroInstalacion());
			pk.setSerieMedidor(request.getAntiguoMedidor());
			Consumo consumoBD = consumosRep.findOne(pk);
			guardarAuditoria(cicloAbierto.getCiclo(), request.getNumeroInstalacion(), usuario, 
					consumoBD.getConsumoMes(), request.getConsumo(), null, null, request.getObservacion());
			consumoBD.setConsumoDefinitivo(request.getConsumo());
			consumoBD.setAjustado(true);
			consumosRep.save(consumoBD);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
	
	public MensajeResponse guardarCorreccion(CorreccionConsumoRequest request, String usuario) {
		try{
			Ciclo cicloAbierto = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
			ConsumoPK pk = new ConsumoPK();
			pk.setCiclo(cicloAbierto.getCiclo());
			pk.setInstalacion(request.getNumeroInstalacion());
			pk.setSerieMedidor(request.getAntiguoMedidor());
			Consumo consumoBD = consumosRep.findOne(pk);
			guardarAuditoria(cicloAbierto.getCiclo(), request.getNumeroInstalacion(), usuario, 
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
		Long ciclo = ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO);
		cicloAnterior = ciclosRep.findMaximoCicloPorEstado(Constantes.CERRADO);
		return consumosRep.findByRango(ciclo, (-1L)*request.getConsumo(), request.getConsumo()).stream().map(this::transform).collect(Collectors.toList());
	}
	
	private ConsumoRiesgo transform(Consumo consumoBD){
		ConsumoRiesgo consumo = new ConsumoRiesgo();
		consumo.setInstalacion(consumoBD.getId().getInstalacion());
		consumo.setNombre(consumoBD.getInstalacion().getUsuario().getNombreCompleto());
		consumo.setRamal(consumoBD.getInstalacion().getRamal());
		consumo.setSerieMedidor(consumoBD.getInstalacion().getSerieMedidor());
		consumo.setLecturaAnterior(consumoBD.getLecturaAnterior());
		consumo.setLecturaActual(consumoBD.getLecturaActual());
		consumo.setConsumoAnterior(consumosRep.findByIdInstalacionAndIdCiclo(consumoBD.getId().getInstalacion(), cicloAnterior).getConsumoDefinitivo());
		consumo.setConsumoActual(consumoBD.getConsumoDefinitivo());
		consumo.setConsumoPromedio(consumoBD.getConsumoPromedio());
		consumo.setFecha(consumoBD.getFechaHasta());
		consumo.setDiferencia(consumo.getConsumoActual() - consumo.getConsumoAnterior());
		return consumo;
	}
	
	private void guardarAuditoria(Long ciclo, Long instalacion, String usuario, Long consumo, Long consumoCorregido, Long lectura, Long lecturaCorregida, String observacion){
		ConsumoAuditoria auditoria = new ConsumoAuditoria();
		auditoria.setCiclo(ciclo);
		auditoria.setConsumo(consumo);
		auditoria.setConsumoCorregido(consumoCorregido);
		auditoria.setFecha(new Date());
		auditoria.setInstalacion(instalacion);
		auditoria.setLectura(lectura);
		auditoria.setLecturaCorregida(lecturaCorregida);
		auditoria.setObservacion(observacion);
		auditoria.setUsuario(usuario);
		audiRep.save(auditoria);
	}

	public List<ConsumoAuditoriaResponse> consultarAuditoria(FiltroRequest request) {
		Long ciclo = ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO);
		if(request.getNumeroDesde() == null)
			request.setNumeroDesde(ciclo);
		if(request.getNumeroHasta() == null)
			request.setNumeroHasta(ciclo);
		List<ConsumoAuditoria> consumos = audiRep.findByCicloBetween(request.getNumeroDesde(), request.getNumeroHasta());
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
