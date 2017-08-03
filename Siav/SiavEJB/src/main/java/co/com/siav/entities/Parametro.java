package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ta_configuraciones_sistema")
public class Parametro implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nmconfiguracion")
	private Long id;
	
	@Column(name="llave")
	private String cdParametro;
	
	@Column(name="valor")
	private String dsValor;
	
	@Column(name="dsvalor")
	private String descripcionLarga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCdParametro() {
		return cdParametro;
	}

	public void setCdParametro(String cdParametro) {
		this.cdParametro = cdParametro;
	}

	public String getDsValor() {
		return dsValor;
	}

	public void setDsValor(String dsValor) {
		this.dsValor = dsValor;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	
}
