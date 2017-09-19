package co.com.siav.response;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PqrResponse {
	
	private Long id;
	private Long numeroInstalacion;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private String descripcion;
	private String nombreCompleto;
	private String nombreUsuario;
	private String usuarioAsignado;
	private Long diasAtencion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}
	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}
	
	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}
	
	public Long getDiasAtencion() {
		LocalDate before = convertDateToLocalDate(fechaInicio);
		LocalDate after = convertDateToLocalDate(fechaFin == null ? new Date() : fechaFin);
		return DAYS.between(before,after);
	}
	
	public void setDiasAtencion(Long diasAtencion) {
		this.diasAtencion = diasAtencion;
	}
	
	private LocalDate convertDateToLocalDate(Date date){
		return  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
	}
	
}
