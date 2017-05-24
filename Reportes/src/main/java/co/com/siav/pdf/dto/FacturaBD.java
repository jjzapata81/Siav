package co.com.siav.pdf.dto;

import java.util.Date;


public class FacturaBD {
	
	private String ciclo;
	private String nombre;
	private String instalacion;
	private String direccion;
	private String vereda;
	private String numeroFactura;
	private Long consumoActual;
	private Long promedioConsumo;
	private Long cuentasVencidas;
	private Long lecturaActual;
	private Long lecturaAnterior;
	private Long diasConsumo;
	private String historicoConsumo;
	private Long consumoAnterior;
	private Long valorMesAnterior;
	private Date fechaUltimoPago;
	private Date fechaAnterior;
	private Date fechaActual;
	private String estrato;
	private String medidor;
	
	public String getCiclo() {
		return ciclo;
	}
	
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(String instalacion) {
		this.instalacion = instalacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getVereda() {
		return vereda;
	}

	public void setVereda(String vereda) {
		this.vereda = vereda;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Long getConsumoActual() {
		return consumoActual;
	}

	public void setConsumoActual(Long consumoActual) {
		this.consumoActual = consumoActual;
	}

	public Long getPromedioConsumo() {
		return promedioConsumo;
	}

	public void setPromedioConsumo(Long promedioConsumo) {
		this.promedioConsumo = promedioConsumo;
	}

	public Long getCuentasVencidas() {
		return cuentasVencidas;
	}

	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}

	public Long getLecturaActual() {
		return lecturaActual;
	}

	public void setLecturaActual(Long lecturaActual) {
		this.lecturaActual = lecturaActual;
	}

	public Long getLecturaAnterior() {
		return lecturaAnterior;
	}

	public void setLecturaAnterior(Long lecturaAnterior) {
		this.lecturaAnterior = lecturaAnterior;
	}

	public Long getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(Long diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public String getHistoricoConsumo() {
		return historicoConsumo;
	}

	public void setHistoricoConsumo(String historicoConsumo) {
		this.historicoConsumo = historicoConsumo;
	}

	public Long getConsumoAnterior() {
		return consumoAnterior;
	}

	public void setConsumoAnterior(Long consumoAnterior) {
		this.consumoAnterior = consumoAnterior;
	}

	public Long getValorMesAnterior() {
		return valorMesAnterior;
	}

	public void setValorMesAnterior(Long valorMesAnterior) {
		this.valorMesAnterior = valorMesAnterior;
	}

	public Date getFechaUltimoPago() {
		return fechaUltimoPago;
	}

	public void setFechaUltimoPago(Date fechaUltimoPago) {
		this.fechaUltimoPago = fechaUltimoPago;
	}
	
	public String getEstrato() {
		return estrato;
	}
	
	public void setEstrato(String estrato) {
		this.estrato = estrato;
	}

	public Date getFechaAnterior() {
		return fechaAnterior;
	}

	public void setFechaAnterior(Date fechaAnterior) {
		this.fechaAnterior = fechaAnterior;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getMedidor() {
		return medidor;
	}

	public void setMedidor(String medidor) {
		this.medidor = medidor;
	}
	
}
