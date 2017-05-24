package co.com.siav.dto;

public class Ping {

	private String mensaje;

	public Ping(String mensaje) {
		this.setMensaje(mensaje);
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
