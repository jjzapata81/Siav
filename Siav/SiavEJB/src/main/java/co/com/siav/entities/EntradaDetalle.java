package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ta_entrada_detalle")
public class EntradaDetalle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EntradaDetallePK codigo;
	
	private Long cantidad;
	
	private Double precioCompra;
	
	@JoinColumn(name="codigo.codEntrada",referencedColumnName="nmentrada", updatable=false, insertable=false)
	@ManyToOne
	private EntradaMaestro entrada;
	
	@OneToOne
	@JoinColumn(name="nmarticulo", updatable=false, insertable=false)
	private Articulo articulo;
	
	public EntradaDetallePK getCodigo() {
		return codigo;
	}

	public void setCodigo(EntradaDetallePK codigo) {
		this.codigo = codigo;
	}
	
	public Long getCantidad() {
		return cantidad == null ? 0L : cantidad;
	}
	
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getPrecioCompra() {
		return precioCompra == null ? 0D : precioCompra;
	}
	
	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}
	
	public Articulo getArticulo() {
		return articulo;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public EntradaMaestro getEntrada() {
		return entrada;
	}
	
	public void setEntrada(EntradaMaestro entrada) {
		this.entrada = entrada;
	}
		
}
