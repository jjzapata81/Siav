package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioRamalPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="usuario",nullable=false)
	private String usuario;
	
	@Column(name="cdramal",nullable=false)
	private String codigoRamal;
	
	@Column(name="fechainicial",nullable=false)
	private Date fechaInicial;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
