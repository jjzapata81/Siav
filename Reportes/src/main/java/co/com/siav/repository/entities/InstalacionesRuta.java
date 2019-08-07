package co.com.siav.repository.entities;

public class InstalacionesRuta {
	
	private String ruta;
	private Long instalacion;
	private String nombre;
	private String tieneMedidor;
	private String serieMedidor;
	private String factura;
	private String vereda;
	private String direccion;
	private String telefono;
	
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTieneMedidor() {
		return tieneMedidor;
	}
	public void setTieneMedidor(String tieneMedidor) {
		this.tieneMedidor = tieneMedidor;
	}
	public String getSerieMedidor() {
		return serieMedidor;
	}
	public void setSerieMedidor(String serieMedidor) {
		this.serieMedidor = serieMedidor;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getVereda() {
		return vereda;
	}
	public void setVereda(String vereda) {
		this.vereda = vereda;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
