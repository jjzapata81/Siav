package co.com.siav.request;

public class CorreccionConsumoRequest {
	
	private Long numeroInstalacion;
	private Long consumo;
	private Long lecturaCorregida;
	private String antiguoMedidor;
	private String nuevoMedidor;
	private String observacion;
	
	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}
	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
	public Long getConsumo() {
		return consumo;
	}
	public void setConsumo(Long consumo) {
		this.consumo = consumo;
	}
	public Long getLecturaCorregida() {
		return lecturaCorregida;
	}
	public void setLecturaCorregida(Long lecturaCorregida) {
		this.lecturaCorregida = lecturaCorregida;
	}
	public String getAntiguoMedidor() {
		return antiguoMedidor;
	}
	public void setAntiguoMedidor(String antiguoMedidor) {
		this.antiguoMedidor = antiguoMedidor;
	}
	public String getNuevoMedidor() {
		return nuevoMedidor;
	}
	public void setNuevoMedidor(String nuevoMedidor) {
		this.nuevoMedidor = nuevoMedidor;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
