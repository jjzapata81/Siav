package co.com.siav.response;

import java.util.Date;

import co.com.siav.entities.UsuarioSistema;

public class PqrDetalleResponse {
	

	private Long id;

	private Long idPqr;

	private Date fechaAccion;
	
	private UsuarioSistema usuario;
	
	private String accion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdPqr() {
		return idPqr;
	}
	public void setIdPqr(Long idPqr) {
		this.idPqr = idPqr;
	}

	public Date getFechaAccion() {
		return fechaAccion;
	}
	public void setFechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}
	public UsuarioSistema getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioSistema usuario) {
		this.usuario = usuario;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
}
