package co.com.siav.request;

import co.com.siav.entities.Estructura;

public class EstructuraRequest extends Estructura{

	private static final long serialVersionUID = 1L;
	
	private String nuevoCargo;
	
	public String getNuevoCargo() {
		return nuevoCargo;
	}
	public void setNuevoCargo(String nuevoCargo) {
		this.nuevoCargo = nuevoCargo;
	}

}
