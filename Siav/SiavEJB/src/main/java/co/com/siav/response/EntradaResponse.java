package co.com.siav.response;

public class EntradaResponse extends MensajeResponse{

	private Long codEntrada;
	
	public EntradaResponse(EstadoEnum estado, String mensaje) {
		super(estado, mensaje);
	}

	public EntradaResponse(String mensaje) {
		super(mensaje);
	}
	
	public EntradaResponse(String mensaje, Long codEntrada) {
		super(mensaje);
		this.codEntrada = codEntrada;
	}
	
	public Long getCodEntrada() {
		return codEntrada;
	}
	
	public void setCodEntrada(Long codEntrada) {
		this.codEntrada = codEntrada;
	}

}
