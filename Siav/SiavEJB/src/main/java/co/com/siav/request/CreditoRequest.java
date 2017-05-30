package co.com.siav.request;

import co.com.siav.entities.CreditoMaestro;


public class CreditoRequest extends CreditoMaestro{
	
	private static final long serialVersionUID = 1L;
	
	private Long comprobante;
	
	public Long getComprobante() {
		return comprobante;
	}
	
	public void setComprobante(Long comprobante) {
		this.comprobante = comprobante;
	}
	
}
