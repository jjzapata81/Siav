package co.com.siav.reports.response;

public class CuentasVencidas {
	
	private Long factura;
	
	private String nombres;
	
	private Long valor;
	
	private Long cuentasvencidas;

	public Long getFactura() {
		return factura;
	}

	public void setFactura(Long factura) {
		this.factura = factura;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Long getCuentasvencidas() {
		return cuentasvencidas;
	}

	public void setCuentasvencidas(Long cuentasvencidas) {
		this.cuentasvencidas = cuentasvencidas;
	}
	
}
