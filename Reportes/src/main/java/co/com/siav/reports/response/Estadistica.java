package co.com.siav.reports.response;

public class Estadistica {
	
	private Long orden;
	private String vereda;
	private Long instalaciones;
	private Long consumoTotal;
	private Double porcentaje;
	private Double consumoPromedio;
	
	public Long getOrden() {
		return orden;
	}
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	public String getVereda() {
		return vereda;
	}
	public void setVereda(String vereda) {
		this.vereda = vereda;
	}
	public Long getInstalaciones() {
		return instalaciones;
	}
	public void setInstalaciones(Long instalaciones) {
		this.instalaciones = instalaciones;
	}
	public Long getConsumoTotal() {
		return consumoTotal;
	}
	public void setConsumoTotal(Long consumoTotal) {
		this.consumoTotal = consumoTotal;
	}
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public Double getConsumoPromedio() {
		return consumoPromedio;
	}
	public void setConsumoPromedio(Double consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}
}
