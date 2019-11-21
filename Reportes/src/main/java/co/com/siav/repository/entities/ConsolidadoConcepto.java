package co.com.siav.repository.entities;

public class ConsolidadoConcepto {
	
	private Long orden;
	private Long ciclo;
	private String codigo;
	private String nombreConcepto;
	private Long venta;
	private Long cartera;
	
	public Long getOrden() {
		return orden;
	}
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	public Long getCiclo() {
		return ciclo;
	}
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombreConcepto() {
		return nombreConcepto;
	}
	public void setNombreConcepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}

	public Long getCartera() {
		return cartera;
	}
	public void setCartera(Long cartera) {
		this.cartera = cartera;
	}
	public Long getVenta() {
		return venta;
	}
	public void setVenta(Long venta) {
		this.venta = venta;
	}

}
