package co.com.siav.reports.response;

import java.util.Date;

public class RecaudoDetalle {
	
	private Long factura;
	
	private String nombres;
	
	private Date fechaPago;
	
	private Date fechaConsignacion;
	
	private Long valor;

	public Long getFactura() {
		return factura;
	}

	public void setFactura(Long factura) {
		this.factura = factura;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaConsignacion() {
		return fechaConsignacion;
	}

	public void setFechaConsignacion(Date fechaConsignacion) {
		this.fechaConsignacion = fechaConsignacion;
	}

	public Long getValor() {
		if(null == valor){
			return 0L;
		}
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
}
