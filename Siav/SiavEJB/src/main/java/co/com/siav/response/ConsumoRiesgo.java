package co.com.siav.response;

import java.util.Date;

public class ConsumoRiesgo {
	
	private Long instalacion;
	private String nombre;
	private String serieMedidor;
	private String ramal;
	private Long lecturaAnterior;
	private Long lecturaActual;
	private Long consumoAnterior;
	private Long consumoActual;
	private Long consumoPromedio;
	private Long diferencia;
	private Date fecha;
	
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSerieMedidor() {
		return serieMedidor;
	}
	public void setSerieMedidor(String serieMedidor) {
		this.serieMedidor = serieMedidor;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
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
	public Long getConsumoAnterior() {
		return consumoAnterior;
	}
	public void setConsumoAnterior(Long consumoAnterior) {
		this.consumoAnterior = consumoAnterior;
	}
	public Long getConsumoActual() {
		return consumoActual;
	}
	public void setConsumoActual(Long consumoActual) {
		this.consumoActual = consumoActual;
	}
	public Long getConsumoPromedio() {
		return consumoPromedio;
	}
	public void setConsumoPromedio(Long consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setDiferencia(Long diferencia) {
		this.diferencia = diferencia;
	}
	public Long getDiferencia() {
		return diferencia;
	}
}
