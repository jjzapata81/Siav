package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Ciclo;
import co.com.siav.entities.Consumo;
import co.com.siav.entities.ConsumoPK;
import co.com.siav.entities.Instalacion;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryConsumos;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.request.CorreccionConsumoRequest;
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
	
	private Long cicloAnterior;

	public List<Consumo> consultarIncompletos() {
		Ciclo cicloAbierto = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
		return consumosRep.findByCicloAndIncompletos(cicloAbierto.getCiclo());
	}

	public MensajeResponse guardar(CorreccionConsumoRequest request) {
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
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
	public MensajeResponse guardarCorreccion(CorreccionConsumoRequest request) {
		try{
			Ciclo cicloAbierto = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
			ConsumoPK pk = new ConsumoPK();
			pk.setCiclo(cicloAbierto.getCiclo());
			pk.setInstalacion(request.getNumeroInstalacion());
			pk.setSerieMedidor(request.getAntiguoMedidor());
			Consumo consumoBD = consumosRep.findOne(pk);
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

	
	
}
