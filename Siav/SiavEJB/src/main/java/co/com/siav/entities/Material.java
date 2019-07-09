package co.com.siav.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ta_materiales")
public class Material implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "Material.id";
	
	@SequenceGenerator(name = Material.NOMBRE_SECUENCIA, sequenceName = "sq_ta_materiales", allocationSize=1)
	@GeneratedValue(generator = Material.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Id
	private Long id;
	
	@Column(name="nmcodigo")
	private String codigo;
	
	@Column(name="nminstalacion")	
	private Long instalacion;

	@Column(name="ciclo")
	private Long ciclo;

	@Column(name="descripcion")
	private String descripcion;

	@Column(name="valor_sin_iva")
	private Double valorSinIva;
	
	@Column(name="cantidad")
	private Long cantidad;
	
	@Column(name="valor_iva")
	private Double valorIva;

	@Column(name="nmsalida")
	private Long idSalida;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValorSinIva() {
		return valorSinIva;
	}

	public void setValorSinIva(Double valorSinIva) {
		this.valorSinIva = valorSinIva;
	}

	public Double getValorIva() {
		return valorIva;
	}

	public void setValorIva(Double valorIva) {
		this.valorIva = valorIva;
	}

	public Long getIdSalida() {
		return idSalida;
	}

	public void setIdSalida(Long idSalida) {
		this.idSalida = idSalida;
	}
	
	public Long getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
}
