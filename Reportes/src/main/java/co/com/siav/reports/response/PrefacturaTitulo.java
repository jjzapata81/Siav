package co.com.siav.reports.response;

public class PrefacturaTitulo {
	
	private Long instalacion;
	private String nombres;
	private String cedula;
	private Long factura;
	private Long lecturaAnterior;
	private Long lecturaActual;
	private Long consumo;
	
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Long getFactura() {
		return factura;
	}
	public void setFactura(Long factura) {
		this.factura = factura;
	}
	public Long getLecturaAnterior() {
		return lecturaAnterior;
	}
	public void setLecturaAnterior(Long lecturaAnterior) {
		this.lecturaAnterior = lecturaAnterior;
	}
	public Long getLecturaActual() {
		return lecturaActual;
	}
	public void setLecturaActual(Long lecturaActual) {
		this.lecturaActual = lecturaActual;
	}
	public Long getConsumo() {
		return consumo;
	}
	public void setConsumo(Long consumo) {
		this.consumo = consumo;
	}

}
