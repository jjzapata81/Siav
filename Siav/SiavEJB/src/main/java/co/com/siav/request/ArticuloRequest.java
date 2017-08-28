package co.com.siav.request;

import co.com.siav.entities.Maestros;


public class ArticuloRequest {
	
	private Long codigo;
	
	private String nombre;
	
	private Maestros unidad;
	
	private String observacion;
	
	private Double porcentajeIva;
	
	private Boolean tieneIva;
	
	private Boolean activo;

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

	public Maestros getUnidad() {
		return unidad;
	}

	public void setUnidad(Maestros unidad) {
		this.unidad = unidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Double getPorcentajeIva() {
		return porcentajeIva;
	}

	public void setPorcentajeIva(Double porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public Boolean getTieneIva() {
		return tieneIva;
	}

	public void setTieneIva(Boolean tieneIva) {
		this.tieneIva = tieneIva;
	}
	
}
