package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ta_credito_detalle")
public class CreditoDetalle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CreditoDetallePK id;
	
	@Column(name="nminstalacion")
	private Long instalacion;
	
	@Column(name="nmfactura")
	private Long factura;

	@Column(name="capital")
	private Long capital;
	
	@Column(name="interes")
	private Long interes;
	
	@Column(name="cuota")
	private Long cuota;
	
	@JoinColumn(name = "nmcredito", referencedColumnName="nmcredito", insertable=false, updatable=false)
	@ManyToOne
	private CreditoMaestro credito;

	public CreditoDetallePK getId() {
		return id;
	}

	public void setId(CreditoDetallePK id) {
		this.id = id;
	}

	public Long getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}

	public Long getFactura() {
		return factura;
	}

	public void setFactura(Long factura) {
		this.factura = factura;
	}

	public Long getCapital() {
		return capital == null ? 0L : capital;
	}

	public void setCapital(Long capital) {
		this.capital = capital;
	}

	public Long getInteres() {
		return interes == null ? 0L : interes;
	}

	public void setInteres(Long interes) {
		this.interes = interes;
	}

	public Long getCuota() {
		return cuota == null ? 0L : cuota;
	}

	public void setCuota(Long cuota) {
		this.cuota = cuota;
	}
	
	public CreditoMaestro getCredito() {
		return credito;
	}
	
	public void setCredito(CreditoMaestro credito) {
		this.credito = credito;
	}
	
}
