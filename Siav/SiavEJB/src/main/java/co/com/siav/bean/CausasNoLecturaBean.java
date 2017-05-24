package co.com.siav.bean;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.dto.CausasNoLecturaDTO;
import co.com.siav.entities.CausaNoLectura;
import co.com.siav.repositories.IRepositoryCausasNoLectura;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class CausasNoLecturaBean {
	
	@Inject
	private IRepositoryCausasNoLectura causasRep;

	public List<CausaNoLectura> consultar() {
		return causasRep.findAll();
	}

	public MensajeResponse crear(CausasNoLecturaDTO causa) {
		if(null == causasRep.findByDescripcion(causa.getDescripcion().trim())){
			causasRep.save(crearCausa(causa));
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}
		return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_CAUSAS_EXISTE);
	}

	private CausaNoLectura crearCausa(CausasNoLecturaDTO causa) {
		CausaNoLectura causaBD = new CausaNoLectura();
		BeanUtils.copyProperties(causa, causaBD);
		return causaBD;
	}

	public List<String> consultarDescripciones() {
		List<CausaNoLectura> causas = causasRep.findAll();
		return causas.stream().map(CausaNoLectura::getDescripcion).collect(Collectors.toList());
	}

}
