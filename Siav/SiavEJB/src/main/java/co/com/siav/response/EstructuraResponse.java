package co.com.siav.response;

import java.util.Date;


public class EstructuraResponse {
	
	private String cedula;
	private String nombre;
	private String email;
	private String telefono;
	private String cargo;
	private String acta;
	private Date fecha;
	private Long empresa;
	private Boolean activo;
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getActa() {
		return acta;
	}
	public void setActa(String acta) {
		this.acta = acta;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Long getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
}
