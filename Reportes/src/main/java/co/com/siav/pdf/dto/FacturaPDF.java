package co.com.siav.pdf.dto;

import java.util.Date;
import java.util.List;

public class FacturaPDF {
	
	private String ciclo;
	private String nombre;
	private String nombreAcueducto;
	private String nit;
	private String codigoBarras;
	private String referente;
	private String estrato;
	private String mensajePrincipal;
	private String instalacion;
	private String direccion;
	private String vereda;
	private String cedula;
	private String numeroFactura;
	private Date fePagoSinRecargo;
	private Date fePagoRecargo;
	private Long valorTotal;
	private String mensajeReclamo;
	private String mensajeCorte;
	private Long consumoAnterior;
	private Long consumoActual;
	private Long valorMesAnterior;
	private Long promedioConsumo;
	private Date fechaUltimoPago;
	private Long cuentasVencidas;
	private Long lecturaActual;
	private Long lecturaAnterior;
	private Long diasConsumo;
	private Date fechaFacturacion;
	private String resolucion;
	private String mensajePuntoPago;
	private Long totalVencido;
	private Date fechaAnterior;
	private Date fechaActual;
	private String medidor;
	
	private List<CreditoPDF> creditos;
	private List<ConsumoPDF> consumos;
	private List<ValoresFacturados> valoresFacturados;
	private List<CobroPDF> otrosCobros;
	
	private List<MaterialesPDF> materiales;
	
	
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
	
	public List<ValoresFacturados> getValoresFacturados() {
		return valoresFacturados;
	}
	
	public void setValoresFacturados(List<ValoresFacturados> valoresFacturados) {
		this.valoresFacturados = valoresFacturados;
	}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}
	
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public List<ConsumoPDF> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<ConsumoPDF> consumos) {
		this.consumos = consumos;
	}
	
	public String getNombreAcueducto() {
		return nombreAcueducto;
	}
	
	public void setNombreAcueducto(String nombreAcueducto) {
		this.nombreAcueducto = nombreAcueducto;
	}
	
	public String getNit() {
		return nit;
	}
	
	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getMensajePrincipal() {
		return mensajePrincipal;
	}

	public void setMensajePrincipal(String mensajePrincipal) {
		this.mensajePrincipal = mensajePrincipal;
	}
	
	public String getMensajeCorte() {
		return mensajeCorte;
	}
	
	public void setMensajeCorte(String mensajeCorte) {
		this.mensajeCorte = mensajeCorte;
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Date getFePagoSinRecargo() {
		return fePagoSinRecargo;
	}

	public void setFePagoSinRecargo(Date fePagoSinRecargo) {
		this.fePagoSinRecargo = fePagoSinRecargo;
	}

	public Date getFePagoRecargo() {
		return fePagoRecargo;
	}

	public void setFePagoRecargo(Date fePagoRecargo) {
		this.fePagoRecargo = fePagoRecargo;
	}

	public Long getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Long valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getMensajeReclamo() {
		return mensajeReclamo;
	}

	public void setMensajeReclamo(String mensajeReclamo) {
		this.mensajeReclamo = mensajeReclamo;
	}

	public Long getConsumoAnterior() {
		return consumoAnterior;
	}

	public void setConsumoAnterior(Long consumoAnterior) {
		this.consumoAnterior = consumoAnterior;
	}

	public Long getConsumoActual() {
		return consumoActual;
	}

	public void setConsumoActual(Long consumoActual) {
		this.consumoActual = consumoActual;
	}

	public Long getValorMesAnterior() {
		return valorMesAnterior;
	}

	public void setValorMesAnterior(Long valorMesAnterior) {
		this.valorMesAnterior = valorMesAnterior;
	}

	public Long getPromedioConsumo() {
		return promedioConsumo;
	}

	public void setPromedioConsumo(Long promedioConsumo) {
		this.promedioConsumo = promedioConsumo;
	}

	public Date getFechaUltimoPago() {
		return fechaUltimoPago;
	}

	public void setFechaUltimoPago(Date fechaUltimoPago) {
		this.fechaUltimoPago = fechaUltimoPago;
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

	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}
	
	public List<CobroPDF> getOtrosCobros() {
		return otrosCobros;
	}
	
	public void setOtrosCobros(List<CobroPDF> otrosCobros) {
		this.otrosCobros = otrosCobros;
	}
	
	public void setCreditos(List<CreditoPDF> creditos) {
		this.creditos = creditos;
	}
	
	public List<CreditoPDF> getCreditos() {
		return creditos;
	}
	
	public String getMensajePuntoPago() {
		return mensajePuntoPago;
	}
	
	public void setMensajePuntoPago(String mensajePuntoPago) {
		this.mensajePuntoPago = mensajePuntoPago;
	}
	
	public String getEstrato() {
		return estrato;
	}
	
	public void setEstrato(String estrato) {
		this.estrato = estrato;
	}
	
	public Long getTotalVencido() {
		return totalVencido;
	}
	
	public void setTotalVencido(Long totalVencido) {
		this.totalVencido = totalVencido;
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
	
	public List<MaterialesPDF> getMateriales() {
		return materiales;
	}
	
	public void setMateriales(List<MaterialesPDF> materiales) {
		this.materiales = materiales;
	}
	
}
