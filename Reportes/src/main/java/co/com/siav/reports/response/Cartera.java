package co.com.siav.reports.response;

public class Cartera {
	
	private Long instalacion;
	private String nombre;
	private String codigo;
	private String concepto;
	private Long saldo;
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public Long getSaldo() {
		return saldo;
	}
	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}
	
}
