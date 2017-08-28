package co.com.siav.request;

import co.com.siav.entities.Articulo;

public class MaterialDetalleRequest {
	
	private Articulo articulo;
	
	private Long cantidad;
	
	private Double precio;

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
}
