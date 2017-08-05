package co.com.siav.response;

import java.util.List;

import co.com.siav.entities.ConsumoAuditoria;

public class ConsumoAuditoriaResponse {
	
	private Long ciclo;
	private List<ConsumoAuditoria> consumos;
	
	public ConsumoAuditoriaResponse(Long ciclo, List<ConsumoAuditoria> consumos) {
		this.ciclo = ciclo;
		this.consumos = consumos;
	}
	public Long getCiclo() {
		return ciclo;
	}
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}
	public List<ConsumoAuditoria> getConsumos() {
		return consumos;
	}
	public void setConsumos(List<ConsumoAuditoria> consumos) {
		this.consumos = consumos;
	}
	
	

}
