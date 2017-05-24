package co.com.siav.response;


public class FacturaResponse extends MensajeResponse{

	private Long numeroFactura;
	
	private String nombres;
	
	private Long numeroInstalacion;
	
	private Long valor;

	public FacturaResponse(EstadoEnum estado, String mensaje) {
		super(estado, mensaje);
	}
	
	public FacturaResponse(String mensaje) {
		super(mensaje);
	}
	
	public FacturaResponse() {
	}

	public Long getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
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
	
}
