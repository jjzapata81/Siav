package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.NotaCreditoAuditoria;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryNotaCreditoAud;
import co.com.siav.repositories.IRepositoryNovedades;
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
	
	@Inject
	private IRepositoryNotaCreditoAud audRep;
	
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

	public MensajeResponse guardarNotaCredito(NotaCreditoRequest request, String usuario) {
		try{
			Long ciclo = ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO);
			Novedad notaCredito = novedadesRep.findOne(new NovedadPK(ciclo, request.getNumeroInstalacion(), "888888"));
			if(notaCredito==null){
				notaCredito = new Novedad();
				notaCredito.setId(new NovedadPK(ciclo, request.getNumeroInstalacion(), "888888"));
			}
			notaCredito.setValor(notaCredito.getValor()-request.getValor());
			novedadesRep.save(notaCredito);
			audRep.save(new NotaCreditoAuditoria(ciclo, request.getNumeroInstalacion(), request.getValor(), usuario, request.getObservacion()));
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_CREAR_NOTA_CREDITO + e.getMessage());
		}
	}

	public List<NotaCreditoAuditoria> consultarNotaCredito(Long numeroInstalacion) {
		Long ciclo = ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO);
		return audRep.findByInstalacionAndCiclo(numeroInstalacion, ciclo);
	}

}
