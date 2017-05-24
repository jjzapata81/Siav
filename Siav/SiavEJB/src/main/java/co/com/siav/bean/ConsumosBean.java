package co.com.siav.bean;

import java.util.List;

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
			Instalacion instalacion = instalacionesRep.findOne(request.getNumeroInstalacion());
			instalacion.setSerieMedidor(request.getNuevoMedidor());
			instalacionesRep.save(instalacion);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
		
	}
	
}
