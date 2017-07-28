package co.com.siav.repository.entities;

public class VariacionConsumo {
	
	private Long instalacion;
	private String nombre;
	private Long consumoActual;
	private Long consumoAnterior;
	private Double difPorcentaje;
	private Long difMetros;
	
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
	public Long getConsumoActual() {
		return consumoActual;
	}
	public void setConsumoActual(Long consumoActual) {
		this.consumoActual = consumoActual;
	}
	public Long getConsumoAnterior() {
		return consumoAnterior;
	}
	public void setConsumoAnterior(Long consumoAnterior) {
		this.consumoAnterior = consumoAnterior;
	}
	public Double getDifPorcentaje() {
		return difPorcentaje;
	}
	public void setDifPorcentaje(Double difPorcentaje) {
		this.difPorcentaje = difPorcentaje;
	}
	public Long getDifMetros() {
		return difMetros;
	}
	public void setDifMetros(Long difMetros) {
		this.difMetros = difMetros;
	}
	
}
