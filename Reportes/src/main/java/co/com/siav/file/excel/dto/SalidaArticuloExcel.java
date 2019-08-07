package co.com.siav.file.excel.dto;

public class SalidaArticuloExcel {
	
	private ArticuloExcel articulo;
	private Long cantidad;
	private Double precio;
	private Double precioUnitario;
	private Double valorConIva;
	private Double valorIva;
	
	public ArticuloExcel getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloExcel articulo) {
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
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
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

}
