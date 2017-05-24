package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Rango;
import co.com.siav.repositories.IRepositoryRangos;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class RangosBean {
	
	@Inject
	private IRepositoryRangos rangosRep;

	public List<Rango> consultar() {
		return rangosRep.findAll();
	}

	public MensajeResponse crear(Rango rango) {
		if(rangoEsValido(rango)){
			rangosRep.save(rango);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}
		return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_RANGO_NO_VALIDO);
	}

	private boolean rangoEsValido(Rango rango) {
		List<Rango> rangos = rangosRep.findAll();
		List<Rango> filtro = rangos.stream().filter(r -> (r.getLimiteInicial() <= rango.getLimiteInicial() && r.getLimiteFinal() >= rango.getLimiteInicial())
				|| (r.getLimiteInicial() <= rango.getLimiteFinal() && r.getLimiteFinal() >= rango.getLimiteFinal())
				|| (rango.getLimiteInicial() <= r.getLimiteInicial() && rango.getLimiteFinal() >= r.getLimiteFinal()) ).collect(Collectors.toList());
		return filtro.isEmpty();
	}

	public MensajeResponse eliminar(Rango rango) {
		if(Constantes.DISPONIBLE.equalsIgnoreCase(rango.getEstado())){
			rangosRep.delete(rango);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}
		return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_RANGO_NO_DISPONIBLE);
	}

}
