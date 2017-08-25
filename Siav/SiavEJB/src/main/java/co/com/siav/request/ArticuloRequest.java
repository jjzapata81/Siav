package co.com.siav.request;


public class ArticuloRequest {
	
	private Long codigo;
	
	private String nombre;
	
	private String unidad;
	
	private Double precioUnitario;
	
	private Double precioInventario;
	
	private Double porcentajeGanancia;
	
	private Double precioComercial;
	
	private String observacion;
	
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

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getPrecioInventario() {
		return precioInventario;
	}

	public void setPrecioInventario(Double precioInventario) {
		this.precioInventario = precioInventario;
	}

	public Double getPorcentajeGanancia() {
		return porcentajeGanancia;
	}

	public void setPorcentajeGanancia(Double porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}

	public Double getPrecioComercial() {
		return precioComercial;
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
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
}
