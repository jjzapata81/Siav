package co.com.siav.pdf.dto;

public class FacturaDetalleBD {
	
	private String codigoConcepto;
	private String nombreConcepto;
	private Long metros;
	private Long valor;
	private Long saldo;
	private String descripcion;
	private Long codigoCredito;
	
	public String getCodigoConcepto() {
		return codigoConcepto;
	}
	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}
	public String getNombreConcepto() {
		return nombreConcepto;
	}
	public void setNombreConcepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}
	public Long getMetros() {
		return null == metros ? 0L : metros;
	}
	public void setMetros(Long metros) {
		this.metros = metros;
	}
	public Long getValor() {
		return null == valor ? 0L : valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public Long getSaldo() {
		return null == saldo ? 0L : saldo;
	}
	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getCodigoCredito() {
		return codigoCredito;
	}
	public void setCodigoCredito(Long codigoCredito) {
		this.codigoCredito = codigoCredito;
	}
	
}
