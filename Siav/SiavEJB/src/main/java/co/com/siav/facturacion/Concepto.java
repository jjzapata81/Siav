package co.com.siav.facturacion;

public class Concepto {
	
	private String codigo;
	private String nombre;
	private String detalle;
	private Long metros;
	private Long valor;
	private Long vencido;
	
	public Concepto() {
	}
	
	public Concepto(String codigo, String nombre, String detalle, Long metros, Long valor, Long vencido) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.detalle = detalle;
		this.valor = valor;
		this.metros = metros;
		this.vencido = vencido == null ? 0L : vencido;
	}
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
	
	public Long getMetros() {
		return metros;
	}
	
	public void setMetros(Long metros) {
		this.metros = metros;
	}
	
}
