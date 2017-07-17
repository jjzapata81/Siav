package co.com.siav.response;

public class CuentasVencidasResponse extends MensajeResponse{
	
	private boolean autorizado;

	public CuentasVencidasResponse(EstadoEnum estado, String mensaje, boolean autorizado) {
		super(estado, mensaje);
		this.autorizado = autorizado;
	}

	public CuentasVencidasResponse(String mensaje) {
		super(mensaje);
		this.autorizado = true;
	}

	public boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}

}
