package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import co.com.siav.utils.Constantes;

@Entity
@Table(name="ta_factura_detalle")
public class DetalleFactura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String NOMBRE_SECUENCIA = "DetalleFactura.id";
	
	@Id
	@SequenceGenerator(name = DetalleFactura.NOMBRE_SECUENCIA, sequenceName = "sq_ta_detalle_factura", allocationSize=1)
	@GeneratedValue(generator = DetalleFactura.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="id ")
	private Long id;
	
	@JoinColumn(name="nmfactura",referencedColumnName="nmfactura", updatable=false, insertable=false)
	@ManyToOne
	private Factura factura;

	@Column(name="nmfactura")
	private Long idFactura;
	
	@Column(name="idcredito")
	private Long idCredito;
	
	@Column(name="cdconcepto")
	private String codigo;
	
	@Column(name="nombreconcepto")
	private String nombre;
	
	@Column(name="descripcion")
	private String detalle;
	
	@Column(name="saldo")
	private Long saldo;
	
	@Column(name="metros")
	private Long metros;
	
	@Column(name="valor")
	private Long valor;
	
	@Column(name="cartera")
	private Long cartera;
	
	@Column(name="snacumulado")
	private String acumulado;
	
	@Column(name="sncancelado")
	private String cancelado;
	
	@Column(name="ciclo")
	private Long ciclo;
	
	@Column(name="nminstalacion")
	private Long instalacion;
	
	public Long getIdFactura() {
		return idFactura;
	}
	
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Long getValor() {
		return valor == null ? 0L : valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Long getIdCredito() {
		return idCredito;
	}
	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}
	public Long getSaldo() {
		return null == saldo? 0L : saldo;
	}
	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}
	public Long getCartera() {
		return cartera == null ? 0L : cartera;
	}
	public void setCartera(Long cartera) {
		this.cartera = cartera;
	}
	
	public Boolean getAcumulado() {
		return "S".equals(acumulado);
	}

	public void setAcumulado(Boolean acumulado) {
		this.acumulado = acumulado ? "S" : "N";
	}

	public Boolean getCancelado() {
		return "S".equals(cancelado);
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado ? "S" : "N";
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}
	
	public Long getCiclo() {
		return ciclo;
	}
	
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	
	public Long getInstalacion() {
		return instalacion;
	}
	
	@Transient
	private transient boolean used;
	
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	
	public Long getMetros() {
		return metros;
	}
	
	public void setMetros(Long metros) {
		this.metros = metros;
	}
	
	@PrePersist
	private void onPrePersist(){
		cartera = 0L;
		valor = valor == null ? 0L : valor;
		saldo = saldo == null ? 0L : saldo;
		acumulado = Constantes.NO;
		cancelado = Constantes.NO;
	}
	
}
