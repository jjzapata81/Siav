package co.com.siav.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.ConsumoAuditoria;
import co.com.siav.repositories.IRepositoryConsumoAuditoria;

@Stateless
public class AuditoriaBean {
	
	@Inject
	private IRepositoryConsumoAuditoria audiRep;

	
	public void guardar(Long ciclo, Long instalacion, String usuario, Long consumo, Long consumoCorregido, Long lectura, Long lecturaCorregida, String observacion){
		ConsumoAuditoria auditoria = new ConsumoAuditoria();
		auditoria.setCiclo(ciclo);
		auditoria.setConsumo(consumo);
		auditoria.setConsumoCorregido(consumoCorregido);
		auditoria.setFecha(new Date());
		auditoria.setInstalacion(instalacion);
		auditoria.setLectura(lectura);
		auditoria.setLecturaCorregida(lecturaCorregida);
		auditoria.setObservacion(observacion);
		auditoria.setUsuario(usuario);
		audiRep.save(auditoria);
	}

	public List<ConsumoAuditoria> consultarRiesgo(Long numeroDesde, Long numeroHasta) {
		return audiRep.findByCicloBetween(numeroDesde, numeroHasta);
	}
	
	
}
