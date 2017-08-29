package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="ta_articulo")
public class Articulo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nmarticulo")
	private Long codigo;
	
	private String nombre;
	
	private String unidad;
	
	private String observacion;
	
	private Double porcentajeIva;
	
	@Column(name="sniva")
	private String tieneIva;
	
	@Column(name="snactivo")
	private String activo;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre == null ? "" : nombre.toUpperCase();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre == null ? "" : nombre.trim();
	}
	
	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getObservacion() {
		return observacion == null ? "" : observacion.toUpperCase();
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
	
	public Double getPorcentajeIva() {
		return porcentajeIva == null ? 0D : porcentajeIva;
	}

	public void setPorcentajeIva(Double porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public Boolean getTieneIva() {
		return "S".equals(tieneIva);
	}

	public void setTieneIva(Boolean tieneIva) {
		this.tieneIva = tieneIva ? "S" : "N";
	}

	@PrePersist
	private void onPrePersist(){
		activo = "S";
	}
	
}
