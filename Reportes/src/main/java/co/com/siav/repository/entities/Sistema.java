package co.com.siav.repository.entities;

public class Sistema {
	
	private String cargoFijo;
	private String basico;
	private String complementario;
	private String suntuario;
	private String envioFactura;
	private Long cuentasVencidas;
	private Long cuentasCorte;
	private Double iva;
	
	public String getCargoFijo() {
		return cargoFijo;
	}
	public void setCargoFijo(String cargoFijo) {
		this.cargoFijo = cargoFijo;
	}
	public String getBasico() {
		return basico;
	}
	public void setBasico(String basico) {
		this.basico = basico;
	}
	public String getComplementario() {
		return complementario;
	}
	public void setComplementario(String complementario) {
		this.complementario = complementario;
	}
	public String getSuntuario() {
		return suntuario;
	}
	public void setSuntuario(String suntuario) {
		this.suntuario = suntuario;
	}
	
	public String getEnvioFactura() {
		return envioFactura;
	}
	
	public void setEnvioFactura(String envioFactura) {
		this.envioFactura = envioFactura;
	}
	
	public Long getCuentasVencidas() {
		return null == cuentasVencidas ? 0L: cuentasVencidas;
	}
	
	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}
	
	public Long getCuentasCorte() {
		return null == cuentasCorte ? 0L: cuentasCorte;
	}
	
	public void setCuentasCorte(Long cuentasCorte) {
		this.cuentasCorte = cuentasCorte;
	}
	
	public Double getIva() {
		return iva;
	}
	
	public void setIva(Double iva) {
		this.iva = iva;
	}

}
