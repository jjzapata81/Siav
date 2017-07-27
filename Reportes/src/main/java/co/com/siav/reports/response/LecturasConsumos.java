package co.com.siav.reports.response;

public class LecturasConsumos {
	
	private Long instalacion;
	private String nombres;
	private Long leAnterior;
	private Long leActual;
	private Long consumoDefinitivo;
	private Long consumoPromedio;
	private String paga;
	
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
	public Long getLeAnterior() {
		return leAnterior;
	}
	public void setLeAnterior(Long leAnterior) {
		this.leAnterior = leAnterior;
	}
	public Long getLeActual() {
		return leActual;
	}
	public void setLeActual(Long leActual) {
		this.leActual = leActual;
	}
	public Long getConsumoDefinitivo() {
		return consumoDefinitivo;
	}
	public void setConsumoDefinitivo(Long consumoDefinitivo) {
		this.consumoDefinitivo = consumoDefinitivo;
	}
	public Long getConsumoPromedio() {
		return consumoPromedio;
	}
	public void setConsumoPromedio(Long consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}
	
	public String getPaga() {
		return paga;
	}
	public void setPaga(String paga) {
		this.paga = paga;
	}
	
}
