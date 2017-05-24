package co.com.siav.pdf.dto;

import java.util.List;

public class InstalacionPDF{
	
	private Long numero;
	private String nombres;
	private String cedula;
	private String serialMedidor;
	private Long factura;
	private Long cuentasVencidas;
	private Long lecturaActual;
	private Long lecturaAnterior;
	private Long consumo;
	private List<DetalleInstalacionPDF> detalles;
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
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
	public Long getCuentasVencidas() {
		return cuentasVencidas;
	}
	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}
	public Long getLecturaActual() {
		return lecturaActual;
	}
	public void setLecturaActual(Long lecturaActual) {
		this.lecturaActual = lecturaActual;
	}
	public Long getLecturaAnterior() {
		return lecturaAnterior;
	}
	public void setLecturaAnterior(Long lecturaAnterior) {
		this.lecturaAnterior = lecturaAnterior;
	}
	public Long getConsumo() {
		return consumo;
	}
	public void setConsumo(Long consumo) {
		this.consumo = consumo;
	}
	public List<DetalleInstalacionPDF> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleInstalacionPDF> detalles) {
		this.detalles = detalles;
	}
	
	public String getSerialMedidor() {
		return serialMedidor;
	}
	
	public void setSerialMedidor(String serialMedidor) {
		this.serialMedidor = serialMedidor;
	}
	
}
