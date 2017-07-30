package co.com.siav.response;

import co.com.siav.entities.CreditoMaestro;

public class CreditoMaestroInfo {
	
	private Long codigo;
	
	private String descripcion;
	
	private Long valor;
	
	private Long saldo;
	
	private Long inicial;
	
	private Long actual;
	
	private Double interes;
	
	private Long numeroCuotas;
	
	private String cancelado;
	
	public CreditoMaestroInfo(){
		super();
	}
	
	public CreditoMaestroInfo(CreditoMaestro credito){
		this.codigo = credito.getId();
		this.descripcion = credito.getConcepto().getDescripcion();
		this.valor = credito.getValor();
		this.saldo = credito.getSaldo();
		this.actual = credito.getActual();
		this.interes = credito.getInteres();
		this.numeroCuotas = credito.getNumeroCuotas();
		this.cancelado = credito.getFechaFinal() != null || actual == numeroCuotas ? "SI" : "NO";
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public Long getInicial() {
		return inicial;
	}

	public void setInicial(Long inicial) {
		this.inicial = inicial;
	}

	public Long getActual() {
		return actual;
	}

	public void setActual(Long actual) {
		this.actual = actual;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public Long getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(Long numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}
	
}
