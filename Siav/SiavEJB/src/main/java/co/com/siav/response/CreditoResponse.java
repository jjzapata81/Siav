package co.com.siav.response;

import java.util.List;

import co.com.siav.entities.CreditoMaestro;

public class CreditoResponse{

	private Long numeroInstalacion;
	
	private String nombreCompleto;
	
	private String cedula;
	
	private List<CreditoMaestroResponse> creditos;
	

	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}

	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public List<CreditoMaestroResponse> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<CreditoMaestroResponse> creditos) {
		this.creditos = creditos;
	}

}
