package co.com.siav.response;

import co.com.siav.entities.Vereda;

public class VeredaResponse extends Vereda{
	
	private static final long serialVersionUID = 1L;
	
	private Long cantidadUsuarios;
	
	public Long getCantidadUsuarios() {
		return cantidadUsuarios;
	}
	
	public void setCantidadUsuarios(Long cantidadUsuarios) {
		this.cantidadUsuarios = cantidadUsuarios;
	}

}
