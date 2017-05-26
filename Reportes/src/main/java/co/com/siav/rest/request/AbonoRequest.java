package co.com.siav.rest.request;

public class AbonoRequest {
	
	private Long numeroInstalacion;
	private Long numeroCredito;
	private Long valor;
	private String cedula;

	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}

	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
	
	public Long getNumeroCredito() {
		return numeroCredito;
	}
	
	public void setNumeroFactura(Long numeroCredito) {
		this.numeroCredito = numeroCredito;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
}
