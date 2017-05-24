package co.com.siav.response;

import co.com.siav.entities.Instalacion;


public class InstalacionResponse extends MensajeResponse{

	private Instalacion instalacion;
	
	public InstalacionResponse() {
	}
	
	public InstalacionResponse(String mensaje) {
		super(mensaje);
	}
	
	public InstalacionResponse(EstadoEnum estado, String mensaje){
		super(estado, mensaje);
	}

	public InstalacionResponse(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}
	
	public Instalacion getInstalacion() {
		return instalacion;
	}
}
