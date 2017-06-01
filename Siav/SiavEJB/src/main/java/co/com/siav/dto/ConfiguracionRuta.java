package co.com.siav.dto;


public class ConfiguracionRuta {
	
	private Long instalacion;
	private Long instalacionAnterior;
	private String nombre;
	private String apellido;
	private String ramal;
	private String serieMedidor;
	private boolean cambiarOrden;
	
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public Long getInstalacionAnterior() {
		return instalacionAnterior;
	}
	public void setInstalacionAnterior(Long instalacionAnterior) {
		this.instalacionAnterior = instalacionAnterior;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getSerieMedidor() {
		return serieMedidor;
	}
	public void setSerieMedidor(String serieMedidor) {
		this.serieMedidor = serieMedidor;
	}
	public boolean isCambiarOrden() {
		return cambiarOrden;
	}
	public void setCambiarOrden(boolean cambiarOrden) {
		this.cambiarOrden = cambiarOrden;
	}
	
}
