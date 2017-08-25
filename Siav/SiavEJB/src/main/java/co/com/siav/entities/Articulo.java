package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ta_articulo")
public class Articulo implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "Articulo.codigo";
	
	@Id
	@SequenceGenerator(name = Articulo.NOMBRE_SECUENCIA, sequenceName = "sq_ta_articulo", allocationSize=1)
	@GeneratedValue(generator = Articulo.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="nmarticulo")
	private Long codigo;
	
	private String nombre;
	
	private String unidad;
	
	private Double precioUnitario;
	
	private Double precioInventario;
	
	private Double porcentajeGanancia;
	
	private Double precioComercial;
	
	private String observacion;
	
	@Column(name="snactivo")
	private String activo;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario == null ? 0D : precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getPrecioInventario() {
		return precioInventario == null ? 0D : precioInventario;
	}

	public void setPrecioInventario(Double precioInventario) {
		this.precioInventario = precioInventario;
	}

	public Double getPorcentajeGanancia() {
		return porcentajeGanancia == null ? 0D : porcentajeGanancia;
	}

	public void setPorcentajeGanancia(Double porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}

	public Double getPrecioComercial() {
		return precioComercial == null ? 0D : precioComercial;
	}

	public void setPrecioComercial(Double precioComercial) {
		this.precioComercial = precioComercial;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Boolean getActivo() {
		return "S".equals(activo);
	}

	public void setActivo(Boolean activo) {
		this.activo = activo ? "S" : "N";
	}

	@PrePersist
	private void onPrePersist(){
		activo = "S";
	}
	
}
