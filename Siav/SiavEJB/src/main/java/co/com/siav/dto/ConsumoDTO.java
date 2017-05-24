package co.com.siav.dto;


public class ConsumoDTO {
	
	private Long numeroInstalacion;
	private Long ciclo;
	private String nombre;
	private String serialMedidor;
	private String fechaDesde;
	private String fechaHasta;
	private Long lecturaAnterior;
	private Long lecturaActual;
	private Long consumoCorregido;
	private Long codigoCausaNoLectura;
	private String direccion;
	private String observacion;
	private String estado;
    private String sincronizado;
    private String ramal;
	
    public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}
    
    public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
    
	public Long getCiclo() {
		return ciclo;
	}
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}
	public String getSerialMedidor() {
		return serialMedidor;
	}
	public void setSerialMedidor(String serialMedidor) {
		this.serialMedidor = serialMedidor;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Long getLecturaAnterior() {
		return lecturaAnterior;
	}
	public void setLecturaAnterior(Long lecturaAnterior) {
		this.lecturaAnterior = lecturaAnterior;
	}
	public Long getLecturaActual() {
		return lecturaActual;
	}
	public void setLecturaActual(Long lecturaActual) {
		this.lecturaActual = lecturaActual;
	}
	public Long getConsumoCorregido() {
		return consumoCorregido;
	}
	public void setConsumoCorregido(Long consumoCorregido) {
		this.consumoCorregido = consumoCorregido;
	}
	public Long getCodigoCausaNoLectura() {
		return codigoCausaNoLectura;
	}
	public void setCodigoCausaNoLectura(Long codigoCausaNoLectura) {
		this.codigoCausaNoLectura = codigoCausaNoLectura;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSincronizado() {
		return sincronizado;
	}
	public void setSincronizado(String sincronizado) {
		this.sincronizado = sincronizado;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
}
