package co.com.siav.repository.entities;

public class Instalacion {
	
	private Long instalacion;
	private Long cuentasVencidas;
	private String ramal;
	private String medidor;
	
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public Long getCuentasVencidas() {
		return cuentasVencidas;
	}
	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getMedidor() {
		return medidor;
	}
	public void setMedidor(String medidor) {
		this.medidor = medidor;
	}
	
	

}
