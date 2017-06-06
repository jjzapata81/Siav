package co.com.siav.dto;


public class CuentaBancoDTO {
	
	private Long codigoBanco;
	
	private String nombreBanco;
	
	private Long codigo;
	
	private String nombre;
	
	private String numeroCuenta;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Long getCodigoBanco() {
		return codigoBanco;
	}
	
	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombreBanco() {
		return nombreBanco;
	}
	
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
}
