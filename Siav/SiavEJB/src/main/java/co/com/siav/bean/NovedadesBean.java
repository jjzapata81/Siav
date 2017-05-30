package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryNovedades;
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

}
