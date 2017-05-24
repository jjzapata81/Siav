package co.com.siav.pdf.dto;

public class CreditoPDF {
	
	private Long numero;
	
	private String nombre;
	
	private Double tasa;
	
	private Long cuotaActual;
	
	private Long cuotasPendientes;
	
	private Long interes;
	
	private Long saldo;
	
	private Long valor;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	public Long getCuotaActual() {
		return cuotaActual;
	}

	public void setCuotaActual(Long cuotaActual) {
		this.cuotaActual = cuotaActual;
	}

	public Long getCuotasPendientes() {
		return cuotasPendientes;
	}

	public void setCuotasPendientes(Long cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}

	public Long getInteres() {
		return interes;
	}

	public void setInteres(Long interes) {
		this.interes = interes;
	}

	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
}
