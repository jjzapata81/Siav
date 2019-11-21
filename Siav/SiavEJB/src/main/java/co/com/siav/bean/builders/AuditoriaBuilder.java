package co.com.siav.bean.builders;

import java.util.Date;

import co.com.siav.entities.ConsumoAuditoria;

public final class AuditoriaBuilder {
	
	private AuditoriaBuilder(){
		super();
	}

	public static ConsumoAuditoria create(Long ciclo, Long instalacion, String usuario, Long consumo, Long consumoCorregido, Long lectura, Long lecturaCorregida, String observacion) {
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
		return auditoria;
	}

}
