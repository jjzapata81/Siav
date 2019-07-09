package co.com.siav.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ta_articulo")
public class Articulo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nmarticulo")
	private Long codigo;

	@Column(name="codigocontable")
	private String codigoContable;

	@Column(name="nombre")
	private String nombre;

	@Column(name="unidad")
	private String unidad;

	@Column(name="observacion")
	private String observacion;

	@Column(name="porcentajeiva")
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
	
	public String getCodigoContable() {
		return codigoContable;
	}
	
	public void setCodigoContable(String codigoContable) {
		this.codigoContable = codigoContable;
	}

	public String getNombre() {
		return nombre == null ? "" : nombre.toUpperCase();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre == null ? "" : nombre.trim().toUpperCase();
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
		this.observacion = observacion == null ? "" : observacion.trim().toUpperCase();
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
