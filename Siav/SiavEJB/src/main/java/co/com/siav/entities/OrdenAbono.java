package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ta_orden_abono")
public class OrdenAbono implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="orden")
	private Long orden;
	
	@Column(name="cdconcepto")
	private String codigoConcepto;

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public String getCodigoConcepto() {
		return codigoConcepto;
	}

	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}
	
}
