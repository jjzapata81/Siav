package co.com.siav.rest.request;

public class AbonoRequest {
	
	private Long numeroInstalacion;
	private Long numeroFactura;
	private Long valor;

	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}

	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
	
	public Long getNumeroFactura() {
		return numeroFactura;
	}
	
	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
}
