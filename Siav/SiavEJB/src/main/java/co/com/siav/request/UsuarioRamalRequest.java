package co.com.siav.request;

import java.util.Date;

public class UsuarioRamalRequest {
	
	private String usuarioNuevo;
	
	private String usuarioActual;
	
	private String codigoRamal;
	
	private Date fechaInicial;

	public String getUsuarioNuevo() {
		return usuarioNuevo;
	}
	
	public void setUsuarioNuevo(String usuarioNuevo) {
		this.usuarioNuevo = usuarioNuevo;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public String getCodigoRamal() {
		return codigoRamal;
	}

	public void setCodigoRamal(String codigoRamal) {
		this.codigoRamal = codigoRamal;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
}
