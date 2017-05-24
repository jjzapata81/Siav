package co.com.siav.pdf.dto;

public class ValoresFacturados {
	
	private String concepto;
	
	private Long m3;
	
	private Long tarifa;
	
	private Long valor;

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Long getM3() {
		return m3;
	}

	public void setM3(Long m3) {
		this.m3 = m3;
	}

	public Long getTarifa() {
		return tarifa;
	}

	public void setTarifa(Long tarifa) {
		this.tarifa = tarifa;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
}
