package co.com.siav.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

	@Column(name="tipo")
	private String tipo;

	@Column(name="ciclo")
	private Long ciclo;

	@Column(name="saldoanterior")
	private Long saldoAnterior;

	@Column(name="cantidadentrada")
	private Long cantidadEntrada;

	@Column(name="cantidadsalida")
	private Long cantidadSalida;

	@Column(name="saldoactual")
	private Long saldoActual;

	@Column(name="preciocompra")
	private Double precioCompra;

	@Column(name="ivapreciocompra")
	private Double ivaPrecioCompra;

	@Column(name="preciounitario")
	private Double precioUnitario;

	@Column(name="ivapreciounitario")
	private Double ivaPrecioUnitario;

	@Column(name="preciocomercial")
	private Double precioComercial;

	@Column(name="feingreso")
	private Date fechaIngreso;
	
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
	
	public Long getCiclo() {
		return ciclo;
	}
	
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public Long getSaldoAnterior() {
		return saldoAnterior == null ? 0L : saldoAnterior;
	}

	public void setSaldoAnterior(Long saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public Long getCantidadEntrada() {
		return cantidadEntrada == null ? 0L : cantidadEntrada;
	}

	public void setCantidadEntrada(Long cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}

	public Long getCantidadSalida() {
		return cantidadSalida == null ? 0L: cantidadSalida;
	}

	public void setCantidadSalida(Long cantidadSalida) {
		this.cantidadSalida = cantidadSalida;
	}

	public Long getSaldoActual() {
		return saldoActual == null ? 0L : saldoActual;
	}

	public void setSaldoActual(Long saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Double getPrecioCompra() {
		return precioCompra == null ? 0L : precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getPrecioComercial() {
		return precioComercial == null ? 0L : precioComercial;
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

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@PreUpdate
	private void ceroPorDefecto(){
		saldoAnterior = saldoAnterior == null ? 0L : saldoAnterior;
		cantidadEntrada = cantidadEntrada == null ? 0L : cantidadEntrada;
		cantidadSalida = cantidadSalida == null ? 0L : cantidadSalida;
		saldoActual = saldoActual == null ? 0L : saldoActual;
		precioCompra = precioCompra == null ? 0D : precioCompra;
		ivaPrecioCompra = ivaPrecioCompra == null ? 0D : ivaPrecioCompra;
		precioUnitario = precioUnitario == null ? 0D : precioUnitario;
		ivaPrecioUnitario = ivaPrecioUnitario == null ? 0D : ivaPrecioUnitario;
		precioComercial = precioComercial == null ? 0D : precioComercial;
	}

	@PrePersist
	private void presist(){
		ceroPorDefecto();
		fechaIngreso = new Date();
	}
		
}
