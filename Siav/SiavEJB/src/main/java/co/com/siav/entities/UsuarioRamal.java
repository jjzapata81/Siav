package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ta_usuario_ramal")
public class UsuarioRamal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UsuarioRamalPK usuarioRamalPK;
	
	@Column(name="fechafinal")
	private Date fechaFinal;

	public UsuarioRamalPK getUsuarioRamalPK() {
		return usuarioRamalPK;
	}

	public void setUsuarioRamalPK(UsuarioRamalPK usuarioRamalPK) {
		this.usuarioRamalPK = usuarioRamalPK;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
}
