package co.com.siav.reports.filters;

import java.util.Date;

public class Filter {
	
	private Long ciclo;
	
	private String email;
	
	private Long valorDesde;
	
	private Long valorHasta;
	
	private Date fechaDesde;
	
	private Date fechaHasta;
	
	private String criterio;
	
	private String cedula;
	private String nombres;
	private String apellidos;
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	public Long getValorDesde() {
		return valorDesde;
	}
	public void setValorDesde(Long valorDesde) {
		this.valorDesde = valorDesde;
	}
	public Long getValorHasta() {
		return valorHasta;
	}
	public void setValorHasta(Long valorHasta) {
		this.valorHasta = valorHasta;
	}
	
}
