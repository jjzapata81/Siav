package co.com.siav.repository.entities;

import java.util.Date;

public class Kardex {
	
	private Long ciclo;
	private Date fecha;
	private String codigo;
	private String articulo;
	private Long saldoInicial; 
	private Long entrada;
	private Double precioEntrada; 
	private Long salida;
	private Double precioSalida;
	private Long saldoFinal;
	private Double valorSaldo;
	public Long getCiclo() {
		return ciclo;
	}
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public Long getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(Long saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public Long getEntrada() {
		return entrada;
	}
	public void setEntrada(Long entrada) {
		this.entrada = entrada;
	}
	public Double getPrecioEntrada() {
		return precioEntrada;
	}
	public void setPrecioEntrada(Double precioEntrada) {
		this.precioEntrada = precioEntrada;
	}
	public Long getSalida() {
		return salida;
	}
	public void setSalida(Long salida) {
		this.salida = salida;
	}
	public Double getPrecioSalida() {
		return precioSalida;
	}
	public void setPrecioSalida(Double precioSalida) {
		this.precioSalida = precioSalida;
	}
	public Long getSaldoFinal() {
		return saldoFinal;
	}
	public void setSaldoFinal(Long saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	public Double getValorSaldo() {
		return valorSaldo;
	}
	public void setValorSaldo(Double valorSaldo) {
		this.valorSaldo = valorSaldo;
	}
	
}
