package co.com.siav.request;

import java.util.Date;

public class FiltroRequest {
	
	private Date fechaDesde;
	private Date fechaHasta;
	private Long numeroDesde;
	private Long numeroHasta;
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	public Date getFechaHasta() {
		return fechaHasta;
	}
	
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getNumeroDesde() {
		return numeroDesde;
	}

	public void setNumeroDesde(Long numeroDesde) {
		this.numeroDesde = numeroDesde;
	}

	public Long getNumeroHasta() {
		return numeroHasta;
	}

	public void setNumeroHasta(Long numeroHasta) {
		this.numeroHasta = numeroHasta;
	}
	
}
