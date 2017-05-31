package co.com.siav.response;

import co.com.siav.entities.Vereda;

public class VeredaResponse extends Vereda{
	
	private static final long serialVersionUID = 1L;
	
	private Long cantidadUsuarios;
	
	private Long cantidadInstalaciones;
	
	public Long getCantidadUsuarios() {
		return cantidadUsuarios;
	}
	
	public void setCantidadUsuarios(Long cantidadUsuarios) {
		this.cantidadUsuarios = cantidadUsuarios;
	}
	
	public Long getCantidadInstalaciones() {
		return cantidadInstalaciones;
	}
	
	public void setCantidadInstalaciones(Long cantidadInstalaciones) {
		this.cantidadInstalaciones = cantidadInstalaciones;
	}

}
