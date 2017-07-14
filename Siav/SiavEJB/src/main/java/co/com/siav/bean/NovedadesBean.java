package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Novedad;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryNovedades;
import co.com.siav.repositories.IRepositorySistema;
import co.com.siav.request.NotaCreditoRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class NovedadesBean {
	
	@Inject
	private IRepositoryNovedades novedadesRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	public MensajeResponse guardar(Novedad request) {
		request.getId().setCiclo(consultarCiclo());
		novedadesRep.save(request);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	private Long consultarCiclo() {
		return ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO).getCiclo();
	}

	public List<Novedad> consultarPorCedula(Long numeroInstalacion) {
		Long ciclo = consultarCiclo();
		return novedadesRep.findByIdInstalacionAndIdCiclo(numeroInstalacion, ciclo);
	}

	public MensajeResponse eliminar(Novedad request) {
		try{
			novedadesRep.delete(request.getId());
			return new MensajeResponse(Constantes.ELIMINAR_NOVEDAD_OK);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ELIMINAR_NOVEDAD + e.getMessage());
		}
	}

	public MensajeResponse guardarNotaCredito(NotaCreditoRequest request) {
		Long ciclo = ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO);
		List<Novedad> novedades = novedadesRep.findByIdInstalacionAndIdCiclo(request.getNumeroInstalacion(), ciclo);
		Novedad notaCredito = novedades.stream().filter(novedad -> "888888".equals(novedad.getId().getCodigoConcepto())).findFirst().orElse(new Novedad());
		if(notaCredito.getId()==null){
			notaCredito.setId(getNovedadPk(request.getNumeroInstalacion(), "888888"));
		}
		return null;
	}

}
