package co.com.siav.response;

import java.util.Date;

import co.com.siav.entities.Tarifa;

public class CreditoMaestroResponse{

	private Long id;
	
	private Tarifa concepto;

	private Long valor;

	private Long saldo;

	private Long inicial;

	private Long actual;
	
	private Double interes;
	
	private Long numeroCuotas;
	
	private Date fechaFinal;
	
	private String esFinanciado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tarifa getConcepto() {
		return concepto;
	}

	public void setConcepto(Tarifa concepto) {
		this.concepto = concepto;
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

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Boolean getEsFinanciado() {
		return "S".equals(esFinanciado);
	}

	public void setEsFinanciado(Boolean esFinanciado) {
		this.esFinanciado = esFinanciado ? "S" : "N";
	}
		
}
