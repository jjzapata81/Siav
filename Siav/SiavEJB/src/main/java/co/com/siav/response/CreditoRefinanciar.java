package co.com.siav.response;


public class CreditoRefinanciar{

	private Long valor;
	private Long id;
	private Long inicial;
	private Long numeroCuotas;
	private Double interes;
	
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInicial() {
		return inicial;
	}
	public void setInicial(Long inicial) {
		this.inicial = inicial;
	}
	public Long getNumeroCuotas() {
		return numeroCuotas;
	}
	public void setNumeroCuotas(Long numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}
	
	public Double getInteres() {
		return interes;
	}
	
	public void setInteres(Double interes) {
		this.interes = interes;
	}

}
