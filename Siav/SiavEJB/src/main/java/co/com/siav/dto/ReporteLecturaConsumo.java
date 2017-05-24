package co.com.siav.dto;

public class ReporteLecturaConsumo {
	
	
	private Long instalacion;
	private String propietario;
	private Long lecturaActual;
	private Long lecturaAnterior;
	private Long consumo;
	private Long consumoPromedio;
	
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
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
	public Long getConsumoPromedio() {
		return consumoPromedio;
	}
	public void setConsumoPromedio(Long consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}

	
}
