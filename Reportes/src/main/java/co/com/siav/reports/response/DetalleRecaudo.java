package co.com.siav.reports.response;

import java.util.Date;

public class DetalleRecaudo {
	
	private Date fechaFactura;
	private String banco;
	private String numeroCuenta;
	private Date feHasta;
	private String nombres;
	private Long instalacion;
	private Long valor;
	
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Date getFeHasta() {
		return feHasta;
	}
	public void setFeHasta(Date feHasta) {
		this.feHasta = feHasta;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	
}
