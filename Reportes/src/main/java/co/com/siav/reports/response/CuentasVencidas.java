package co.com.siav.reports.response;

public class CuentasVencidas {
	
	private Long instalacion;
	private String nombres;
	private Long total;
	private Long cuentasVencidas;

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

	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getCuentasVencidas() {
		return cuentasVencidas;
	}

	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}
	
}
