package co.com.siav.reports.filters;

import java.util.Date;

public class Comprobante {
	
	private Long instalacion;
	private Long comprobante;
	private String cedula;
	private Long valor;
	private String usuario;
	private Date fecha;
	private Long credito;
	
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public Long getComprobante() {
		return comprobante;
	}
	public void setComprobante(Long comprobante) {
		this.comprobante = comprobante;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getCredito() {
		return credito;
	}
	public void setCredito(Long credito) {
		this.credito = credito;
	}
	
}
