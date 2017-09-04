package co.com.siav.request;

import java.util.Date;
import java.util.List;

import co.com.siav.entities.Proveedor;

public class MaterialRequest {
	
	private Long factura;
	
	private Long instalacion;
	
	private Date fecha;
	
	private Proveedor proveedor;
	
	private List<MaterialDetalleRequest> detalles;

	public Long getFactura() {
		return factura;
	}

	public void setFactura(Long factura) {
		this.factura = factura;
	}
	
	public Long getInstalacion() {
		return instalacion;
	}
	
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<MaterialDetalleRequest> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<MaterialDetalleRequest> detalles) {
		this.detalles = detalles;
	}
	
}
