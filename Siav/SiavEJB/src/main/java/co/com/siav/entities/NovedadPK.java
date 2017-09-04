package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NovedadPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public NovedadPK() {
	}
	
	public NovedadPK(Long instalacion, String codigoConcepto) {
		super();
		this.instalacion = instalacion;
		this.codigoConcepto = codigoConcepto;
	}
	
	public NovedadPK(Long ciclo, Long instalacion, String codigoConcepto) {
		super();
		this.ciclo = ciclo;
		this.instalacion = instalacion;
		this.codigoConcepto = codigoConcepto;
	}

	@Column(name="ciclo", nullable=false)
	private Long ciclo;
	
	@Column(name="nminstalacion", nullable=false)
	private Long instalacion;
	
	@Column(name="cdconcepto", nullable=false)
	private String codigoConcepto;

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public Long getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}

	public String getCodigoConcepto() {
		return codigoConcepto;
	}

	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}
	
}
