package co.com.siav.request;

public class NotaCreditoRequest {

	private Long numeroInstalacion;
	private Long valor;
	private String observacion;
	
	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}
	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
