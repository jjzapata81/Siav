package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ta_kardex")
public class Kardex implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String NOMBRE_SECUENCIA = "Kardex.codigo";
	
	@Id
	@SequenceGenerator(name = Kardex.NOMBRE_SECUENCIA, sequenceName = "sq_ta_kardex", allocationSize=1)
	@GeneratedValue(generator = Kardex.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="nmkardex")
	private Long codigo;
	
	private String tipo;
	
	@Column(name="nmdocumento")
	private Long codDocumento;
	
	private Long saldoAnterior;
	
	private Long cantidadEntrada;
	
	private Long cantidadSalida;
	
	private Long saldoActual;
	
	private Double valorSaldoAnterior;
	
	private Double precioCompra;
	
	private Double precioComercial;
	
	private Double valorSaldoActual;
	

	@OneToOne
	@JoinColumn(name="nmentrada", updatable=false, insertable=false)
	private EntradaMaestro entrada;

	@OneToOne
	@JoinColumn(name="nmsalida", updatable=false, insertable=false)
	private SalidaMaestro salida;
	
	@OneToOne
	@JoinColumn(name="nmarticulo", updatable=false, insertable=false)
	private Articulo articulo;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Articulo getArticulo() {
		return articulo;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public SalidaMaestro getSalida() {
		return salida;
	}
	
	public void setSalida(SalidaMaestro salida) {
		this.salida = salida;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCodDocumento() {
		return codDocumento;
	}

	public void setCodDocumento(Long codDocumento) {
		this.codDocumento = codDocumento;
	}

	public Long getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(Long saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public Long getCantidadEntrada() {
		return cantidadEntrada;
	}

	public void setCantidadEntrada(Long cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}

	public Long getCantidadSalida() {
		return cantidadSalida;
	}

	public void setCantidadSalida(Long cantidadSalida) {
		this.cantidadSalida = cantidadSalida;
	}

	public Long getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Long saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Double getValorSaldoAnterior() {
		return valorSaldoAnterior;
	}

	public void setValorSaldoAnterior(Double valorSaldoAnterior) {
		this.valorSaldoAnterior = valorSaldoAnterior;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getPrecioComercial() {
		return precioComercial;
	}

	public void setPrecioComercial(Double precioComercial) {
		this.precioComercial = precioComercial;
	}

	public Double getValorSaldoActual() {
		return valorSaldoActual;
	}

	public void setValorSaldoActual(Double valorSaldoActual) {
		this.valorSaldoActual = valorSaldoActual;
	}

	public EntradaMaestro getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaMaestro entrada) {
		this.entrada = entrada;
	}
		
}
