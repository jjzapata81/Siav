package co.com.siav.response;

import co.com.siav.entities.Articulo;

public class ArticuloResponse extends Articulo{
	
	private static final long serialVersionUID = 1L;

	private Long cantidadDisponible;
	
	private Double precioUnitario;
	
	private Double precioComercial;
	
	private Double ivaUnitario;

	public Long getCantidadDisponible() {
		return cantidadDisponible == null ? 0L : cantidadDisponible;
	}

	public void setCantidadDisponible(Long cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Double getPrecioUnitario() {
		return precioUnitario == null ? 0D : precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getIvaUnitario() {
		return ivaUnitario == null ? 0D : ivaUnitario;
	}

	public void setIvaUnitario(Double ivaUnitario) {
		this.ivaUnitario = ivaUnitario;
	}
	
	public Double getPrecioComercial() {
		return precioComercial == null ? 0D : precioComercial;
	}
	
	public void setPrecioComercial(Double precioComercial) {
		this.precioComercial = precioComercial;
	}
	
}
