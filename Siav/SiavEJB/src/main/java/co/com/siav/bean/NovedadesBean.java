package co.com.siav.bean;

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

	private NovedadPK crearId(Long instalacion, String codigoConcepto) {
		NovedadPK id = new NovedadPK();
		id.setCiclo(consultarCiclo());
		id.setCodigoConcepto(codigoConcepto);
		id.setInstalacion(instalacion);
		return id;
	}

	private Long consultarCiclo() {
		return ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO).getCiclo();
	}

}
