package co.com.siav.response;

import co.com.siav.entities.Ramal;

public class RamalResponse extends Ramal{

	private static final long serialVersionUID = 1L;
	
	private Long usuarios;
	
	private Long instalaciones;
	
	public Long getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(Long usuarios) {
		this.usuarios = usuarios;
	}
	
	public Long getInstalaciones() {
		return instalaciones;
	}
	
	public void setInstalaciones(Long instalaciones) {
		this.instalaciones = instalaciones;
	}

}
