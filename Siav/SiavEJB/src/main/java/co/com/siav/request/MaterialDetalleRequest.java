package co.com.siav.request;

import co.com.siav.entities.Articulo;

public class MaterialDetalleRequest {
	
	private Articulo articulo;
	
	private Long cantidad;
	
	private Double precio;
	
	private Double precioUnitario;
	
	private Double valorIva;
	
	private Double valorConIva;

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
	
	public Double getValorConIva() {
		return valorConIva;
	}
	
	public void setValorConIva(Double valorConIva) {
		this.valorConIva = valorConIva;
	}
	
	public Double getValorIva() {
		return valorIva;
	}
	
	public void setValorIva(Double valorIva) {
		this.valorIva = valorIva;
	}
	
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
}
