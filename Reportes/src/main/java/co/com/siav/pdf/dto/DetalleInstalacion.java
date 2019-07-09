package co.com.siav.pdf.dto;

public class DetalleInstalacion {
	
	private String codigo;
	private String concepto;
	private String descripcion;
	private Long valor;
	private Long saldo;
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getValor() {
		return null == valor ? 0L :valor;
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
	
}
