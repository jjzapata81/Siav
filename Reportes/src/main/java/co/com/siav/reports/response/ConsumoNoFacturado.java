package co.com.siav.reports.response;

public class ConsumoNoFacturado {
	
	private Long orden;
	private Long instalacion;
	private String nombre;
	private Long leActual;
	private Long leAnterior;
	private Long consumoDefinitivo;
	private String paga;
	private Long consumoPromedio;

	public Long getOrden() {
		return orden;
	}
	public void setOrden(Long orden) {
		this.orden = orden;
	}
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
	public Long getLeActual() {
		return leActual;
	}
	public void setLeActual(Long leActual) {
		this.leActual = leActual;
	}
	public Long getLeAnterior() {
		return leAnterior;
	}
	public void setLeAnterior(Long leAnterior) {
		this.leAnterior = leAnterior;
	}
	public Long getConsumoDefinitivo() {
		return consumoDefinitivo;
	}
	public void setConsumoDefinitivo(Long consumoDefinitivo) {
		this.consumoDefinitivo = consumoDefinitivo;
	}
	public String getPaga() {
		return paga;
	}
	public void setPaga(String paga) {
		this.paga = paga;
	}
	public Long getConsumoPromedio() {
		return consumoPromedio;
	}
	public void setConsumoPromedio(Long consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}
	
}
