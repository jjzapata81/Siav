package co.com.siav.repository.entities;

public class PrefacturaExcel {
	
	private Long numerofactura;
	private Long instalacion;
	private String nombre;
	private String cedula;
	private Long consumo;
	private Long cuentasVencidas;
	private String codigoConcepto;
	private String nombreConcepto;
	private String metros;
	private Long valor;
	private Long saldo;
	private String descripcion;
	private String codigoCredito;
	
	public Long getNumerofactura() {
		return numerofactura;
	}
	public void setNumerofactura(Long numerofactura) {
		this.numerofactura = numerofactura;
	}
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Long getConsumo() {
		return null == consumo ? 0L : consumo;
	}
	public void setConsumo(Long consumo) {
		this.consumo = consumo;
	}
	public Long getCuentasVencidas() {
		return null == cuentasVencidas ? 0L : cuentasVencidas;
	}
	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}
	public String getCodigoConcepto() {
		return codigoConcepto;
	}
	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}
	public String getNombreConcepto() {
		return nombreConcepto;
	}
	public void setNombreConcepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}
	public String getMetros() {
		return metros;
	}
	public void setMetros(String metros) {
		this.metros = metros;
	}
	public Long getValor() {
		return null == valor ? 0L : valor;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoCredito() {
		return codigoCredito;
	}
	public void setCodigoCredito(String codigoCredito) {
		this.codigoCredito = codigoCredito;
	}
	
}
