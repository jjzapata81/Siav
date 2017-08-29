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
	
	private Long saldoAnterior;
	
	private Long cantidadEntrada;
	
	private Long cantidadSalida;
	
	private Long saldoActual;
	
	private Double precioCompra;
	
	private Double ivaPrecioCompra;
	
	private Double precioUnitario;
	
	private Double ivaPrecioUnitario;
	
	private Double precioComercial;
	
	@Column(name="nmentrada")
	private Long codEntrada;
	
	@Column(name="nmsalida")
	private Long codSalida;

	@Column(name="nmarticulo")
	private Long codArticulo;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodSalida() {
		return codSalida;
	}

	public void setCodSalida(Long codSalida) {
		this.codSalida = codSalida;
	}

	public Long getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(Long codArticulo) {
		this.codArticulo = codArticulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Double getIvaPrecioCompra() {
		return ivaPrecioCompra == null ? 0D : ivaPrecioCompra;
	}

	public void setIvaPrecioCompra(Double ivaPrecioCompra) {
		this.ivaPrecioCompra = ivaPrecioCompra;
	}

	public Double getPrecioUnitario() {
		return precioUnitario == null ? 0D : precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getIvaPrecioUnitario() {
		return ivaPrecioUnitario == null ? 0D : ivaPrecioUnitario;
	}

	public void setIvaPrecioUnitario(Double ivaPrecioUnitario) {
		this.ivaPrecioUnitario = ivaPrecioUnitario;
	}
	
	public Long getCodEntrada() {
		return codEntrada;
	}
	
	public void setCodEntrada(Long codEntrada) {
		this.codEntrada = codEntrada;
	}
		
}