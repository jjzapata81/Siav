package co.com.siav.request;

import co.com.siav.entities.UsuarioSistema;


public class PqrRequest {
	
	private Long id;
	
	private Long numeroInstalacion;
	
	private Long estado;
	
	private String descripcion;
	
	private String nombreUsuario;
	
	private String accion;
	
	private UsuarioSistema usuarioAsignar;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}
	
	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}

	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public UsuarioSistema getUsuarioAsignar() {
		return usuarioAsignar;
	}

	public void setUsuarioAsignar(UsuarioSistema usuarioAsignar) {
		this.usuarioAsignar = usuarioAsignar;
	}
	
}
