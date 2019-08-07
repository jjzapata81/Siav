package co.com.siav.repository.entities;

public class PrefacturaDetalle {
	
	private String codigo;
	private String nombre;
	private String detalle;
	private Long valor;
	private Long vencido;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public Long getVencido() {
		return vencido;
	}
	public void setVencido(Long vencido) {
		this.vencido = vencido;
	}

}
