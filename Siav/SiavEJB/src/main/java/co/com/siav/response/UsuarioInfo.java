package co.com.siav.response;

import java.util.List;

public class UsuarioInfo {
	
	private List<InstalacionInfo> instalaciones;
	
	public UsuarioInfo() {
	}
	
	public UsuarioInfo(List<InstalacionInfo> instalaciones) {
		this.instalaciones = instalaciones;
	}

	public List<InstalacionInfo> getInstalaciones() {
		return instalaciones;
	}
	
	public void setInstalaciones(List<InstalacionInfo> instalaciones) {
		this.instalaciones = instalaciones;
	}

}
