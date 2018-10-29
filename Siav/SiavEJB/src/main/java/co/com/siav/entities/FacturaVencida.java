package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ta_facturas_vencidas")
public class FacturaVencida implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nmfactura")
	private Long numeroFactura;
	
	@Column(name="nminstalacion")
	private Long numeroInstalacion;
	
	@Column(name="ciclo")
	private Long ciclo;
	
	@Column(name="valor")
	private Long valor;

	public Long getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

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

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
}
