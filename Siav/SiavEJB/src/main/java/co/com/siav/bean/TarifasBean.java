package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.entities.Tarifa;
import co.com.siav.repositories.IRepositoryTarifas;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class TarifasBean {
	
	@Inject
	private IRepositoryTarifas tarifasRep;

	public List<Tarifa> consultar() {
		return tarifasRep.findAll();
	}

	public MensajeResponse crear(Tarifa tarifa) {
		if(tarifasRep.exists(tarifa.getCodigo())){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.CODIGO_TARIFA_EXISTE, tarifa.getCodigo()));
		}
		tarifa.setOrden(99L);
		tarifasRep.save(tarifa);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	public Tarifa consultarPorCodigo(String codigoTarifa) {
		return tarifasRep.findOne(codigoTarifa);
	}

	public List<String> consultarDescripciones() {
		List<Tarifa> tarifas = tarifasRep.findAll();
		return tarifas.stream().map(Tarifa::getDescripcion).collect(Collectors.toList());
	}

	public MensajeResponse editar(Tarifa request) {
		Tarifa tarifaBD = tarifasRep.findOne(request.getCodigo());
		if(null == tarifaBD){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.CODIGO_TARIFA_NO_EXISTE, request.getCodigo()));
		}
		BeanUtils.copyProperties(request, tarifaBD);
		tarifasRep.save(tarifaBD);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	public List<Tarifa> consultarTarifaCredito() {
		return tarifasRep.findByEsCredito(Constantes.SI);
	}

	public List<Tarifa> consultarActivas() {
		return tarifasRep.findByActivo(Constantes.SI);
	}

}
