package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConsumoPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name="nminstalacion",nullable=false)
	private Long instalacion;
	
	@Column(name="ciclo",nullable=false)
	private Long ciclo;
	
	@Column(name="seriemedidor",nullable=false)
	private String serieMedidor;

	public Long getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}
	
	public String getSerieMedidor() {
		return serieMedidor;
	}
	
	public void setSerieMedidor(String serieMedidor) {
		this.serieMedidor = serieMedidor;
	}
	
}
