package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.bean.builders.AuditoriaBuilder;
import co.com.siav.entities.ConsumoAuditoria;
import co.com.siav.repositories.IRepositoryConsumoAuditoria;

@Stateless
public class AuditoriaBean {
	
	@Inject
	private IRepositoryConsumoAuditoria audiRep;

	
	public void guardar(Long ciclo, Long instalacion, String usuario, Long consumo, Long consumoCorregido, Long lectura, Long lecturaCorregida, String observacion){
		ConsumoAuditoria auditoria = AuditoriaBuilder.create(ciclo, instalacion, usuario, consumo, consumoCorregido, lectura, lecturaCorregida, observacion);
		audiRep.save(auditoria);
	}

	public List<ConsumoAuditoria> consultarRiesgo(Long numeroDesde, Long numeroHasta) {
		return audiRep.findByCicloBetween(numeroDesde, numeroHasta);
	}
	
	
}
