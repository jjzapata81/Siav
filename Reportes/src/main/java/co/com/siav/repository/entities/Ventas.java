package co.com.siav.repository.entities;

public class Ventas {
	
	private String concepto;
	
	private String nombre;
	
	private Long venta;
	
	private Long cartera;

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getVenta() {
		return venta;
	}
	
	public void setVenta(Long venta) {
		this.venta = venta;
	}
	
	public Long getCartera() {
		return cartera;
	}
	
	public void setCartera(Long cartera) {
		this.cartera = cartera;
	}
	
}
