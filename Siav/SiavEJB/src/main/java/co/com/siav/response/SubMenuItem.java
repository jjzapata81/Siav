package co.com.siav.response;

public class SubMenuItem {
	
	private String titulo;
	private String accion;
	private Long orden;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public Long getOrden() {
		return orden;
	}
	
	public void setOrden(Long orden) {
		this.orden = orden;
	}

}
