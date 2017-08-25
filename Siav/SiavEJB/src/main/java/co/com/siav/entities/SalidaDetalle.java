package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ta_salida_detalle")
public class SalidaDetalle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SalidaDetallePK codigo;
	
	private Long cantidad;
	
	private Double precioComercial;
	
	@JoinColumn(name="codigo.codSalida",referencedColumnName="nmsalida", updatable=false, insertable=false)
	@ManyToOne
	private SalidaMaestro salida;
	
	@OneToOne
	@JoinColumn(name="nmarticulo", updatable=false, insertable=false)
	private Articulo articulo;
	
	public SalidaDetallePK getCodigo() {
		return codigo;
	}

	public void setCodigo(SalidaDetallePK codigo) {
		this.codigo = codigo;
	}
	
	public Long getCantidad() {
		return cantidad == null ? 0L : cantidad;
	}
	
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getPrecioComercial() {
		return precioComercial == null ? 0D : precioComercial;
	}
	
	public void setPrecioComercial(Double precioComercial) {
		this.precioComercial = precioComercial;
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
		
}
