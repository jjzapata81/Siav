package co.com.siav.rest.request;

public class AbonoRequest {
	
	private String usuario;
	private Long numeroInstalacion;
	private Long numeroCredito;
	private Long valor;
	private String cedula;
	private String esMatricula;
	private String esAbono;

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
	
	public String getEsMatricula() {
		return esMatricula;
	}
	
	public void setEsMatricula(String esMatricula) {
		this.esMatricula = esMatricula;
	}
	
	public String getEsAbono() {
		return esAbono;
	}
	
	public void setEsAbono(String esAbono) {
		this.esAbono = esAbono;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
