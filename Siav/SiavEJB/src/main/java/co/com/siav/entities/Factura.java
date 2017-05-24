package co.com.siav.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="ta_factura_maestro")
public class Factura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nmfactura")
	private Long numeroFactura;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="nminstalacion")
	private Long numeroInstalacion;
	
	@Column(name="nmcredito")
	private Long numeroCredito;
	
	@Column(name="cedula")
	private String cedula;
	
	@Column(name="seriemedidor")
	private String serieMedidor;
	
	@Column(name="nombrevereda")
	private String nombreVereda;
	
	@Column(name="leanterior")
	private Long lecturaAnterior;
	
	
	@Column(name="leactual")
	private Long lecturaActual;
	
	@Column(name="consumodefinitivo")
	private Long consumo;
	
	@Column(name="consumopromedio")
	private Long consumoPromedio;
	
	@Column(name="cdcausanolectura")
	private Long causaNoLectura;
	
	@Column(name="fedesde")
	private Date fechaDesde;
	
	
	@Column(name="fehasta")
	private Date fechaHasta;
	
	@Column(name="snajuste")
	private String ajustado;
	
	@Column(name="sncancelado")
	private String cancelado;
	
	@Column(name="cdppago")
	private String codigoPuntoPago;
	
	@Column(name="cdcuenta")
	private String codigoCuenta;
	
	@Column(name="snabono")
	private String abono;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="ciclo")
	private Long ciclo;
	
	@Column(name="cuentasvencidas")
	private Long cuentasVencidas;
	
	@Column(name="fepagoreal")
	private Date fechaPagoReal;
	
	@Column(name="feconsignacion")
	private Date fechaConsignacion;
	
	@Column(name="estrato")
	private String estrato;
	
	@Column(name="consumoanterior")
	private Long consumoAnterior;
	
	@Column(name="hria_consumos")
	private String historicoConsumos;
	
	@Column(name="feultimopago")
	private Date fechaUltimoPago;
	
	@Column(name="valorultimopago")
	private Long valorUltimoPago;
	
	@OneToMany(mappedBy = "idFactura", fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<DetalleFactura> detalles;
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}
	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Long getCiclo() {
		return ciclo;
	}
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}
		
	public Long getCuentasVencidas() {
		return cuentasVencidas == null ? 0L : cuentasVencidas;
	}
	
	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas == null ? 0L : cuentasVencidas;
	}
	
	public Date getFechaPagoReal() {
		return fechaPagoReal;
	}
	
	public void setFechaPagoReal(Date fechaPagoReal) {
		this.fechaPagoReal = fechaPagoReal;
	}
	
	public Date getFechaConsignacion() {
		return fechaConsignacion;
	}
	
	public void setFechaConsignacion(Date fechaConsignacion) {
		this.fechaConsignacion = fechaConsignacion;
	}
	
	public List<DetalleFactura> getDetalles() {
		if(null == detalles){
			detalles = new ArrayList<DetalleFactura>();
		}
		return detalles;
	}
	
	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}
	
	public Long getNumeroCredito() {
		return numeroCredito;
	}
	
	public void setNumeroCredito(Long numeroCredito) {
		this.numeroCredito = numeroCredito;
	}
	
	public String getSerieMedidor() {
		return serieMedidor;
	}
	
	public void setSerieMedidor(String serieMedidor) {
		this.serieMedidor = serieMedidor;
	}
	
	public String getNombreVereda() {
		return nombreVereda;
	}
	
	public void setNombreVereda(String nombreVereda) {
		this.nombreVereda = nombreVereda;
	}
	
	public Long getLecturaAnterior() {
		return lecturaAnterior;
	}
	
	public void setLecturaAnterior(Long lecturaAnterior) {
		this.lecturaAnterior = lecturaAnterior;
	}
	
	public Long getLecturaActual() {
		return lecturaActual;
	}
	
	public void setLecturaActual(Long lecturaActual) {
		this.lecturaActual = lecturaActual;
	}
	
	public Long getConsumo() {
		return consumo;
	}
	
	public void setConsumo(Long consumo) {
		this.consumo = consumo;
	}
	
	public Long getConsumoPromedio() {
		return consumoPromedio;
	}
	
	public void setConsumoPromedio(Long consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}
	
	public Long getCausaNoLectura() {
		return causaNoLectura;
	}
	
	public void setCausaNoLectura(Long causaNoLectura) {
		this.causaNoLectura = causaNoLectura;
	}
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	public Date getFechaHasta() {
		return fechaHasta;
	}
	
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public Boolean getAjustado() {
		return "S".equals(ajustado);
	}
	
	public void setAjustado(Boolean ajustado) {
		this.ajustado = ajustado ? "S" : "N";
	}
	
	public Boolean getCancelado() {
		return "S".equals(cancelado);
	}
	
	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado ? "S" : "N";
	}
	
	public String getCodigoPuntoPago() {
		return codigoPuntoPago;
	}
	
	public void setCodigoPuntoPago(String codigoPuntoPago) {
		this.codigoPuntoPago = codigoPuntoPago;
	}
	
	public String getCodigoCuenta() {
		return codigoCuenta;
	}
	
	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}
	
	public Boolean getAbono() {
		return "S".equals(abono);
	}
	
	public void setAbono(Boolean abono) {
		this.abono = abono ? "S" : "N";
	}
	public String getEstrato() {
		return estrato;
	}
	public void setEstrato(String estrato) {
		this.estrato = estrato;
	}
	public Long getConsumoAnterior() {
		return consumoAnterior;
	}
	public void setConsumoAnterior(Long consumoAnterior) {
		this.consumoAnterior = consumoAnterior;
	}
	public String getHistoricoConsumos() {
		return historicoConsumos;
	}
	public void setHistoricoConsumos(String historicoConsumos) {
		this.historicoConsumos = historicoConsumos;
	}
	public Date getFechaUltimoPago() {
		return fechaUltimoPago;
	}
	public void setFechaUltimoPago(Date fechaUltimoPago) {
		this.fechaUltimoPago = fechaUltimoPago;
	}
	public Long getValorUltimoPago() {
		return valorUltimoPago;
	}
	public void setValorUltimoPago(Long valorUltimoPago) {
		this.valorUltimoPago = valorUltimoPago;
	}
	
}
