package co.com.siav.pdf.dto;

import java.util.Date;

public class RecaudoPDF {
	
	private String titulo;
	private String subtitulo;
	private String banco;
	private String numeroCuenta;
	private Date feHasta;
	private Long instalacion;
	private String nombres;
	private Long valor;
	private String resumen;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombre(String nombres) {
		this.nombres = nombres;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public Date getFeHasta() {
		return feHasta;
	}
	public void setFeHasta(Date feHasta) {
		this.feHasta = feHasta;
	}
	
	public String getResumen() {
		return resumen;
	}
	
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
}
