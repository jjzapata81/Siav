package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EstructuraPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name="nmempresa",nullable=false)
	private Long empresa;
	
	@Column(name="cedula",nullable=false)
	private String cedula;
	
	@Column(name="cdcargo",nullable=false)
	private String cargo;
	
	@Column(name="fecha",nullable=false)
	private Date fecha;

	public Long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
