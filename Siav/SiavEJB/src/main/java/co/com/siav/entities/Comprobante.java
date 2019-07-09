package co.com.siav.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ta_comprobante_pago")
public class Comprobante implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nmcomprobante")
	private Long idComprobante;
	
	@Column(name="nminstalacion")
	private Long instalacion;

	@Column(name="cedula")
	private String cedula;

	@Column(name="valor")
	private Long valor;

	@Column(name="usuario")
	private String usuario;

	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="fepago")
	private Date fechaPago;
	
	@Column(name="nmcredito")
	private Long idCredito;
	
	@Column(name="snmatricula")
	private String esMatricula;
	
	@Column(name="sncancelado")
	private String cancelado;
	
	@Column(name="snabono")
	private String abono;

	public Long getIdComprobante() {
		return null == idComprobante ? 0L : idComprobante;
	}

	public void setIdComprobante(Long idComprobante) {
		this.idComprobante = idComprobante;
	}

	public Long getInstalacion() {
		return null == instalacion ? 0L : instalacion;
	}

	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Long getValor() {
		return null == valor ? 0L : valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Date getFechaPago() {
		return fechaPago;
	}
	
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}

	public Boolean getEsMatricula() {
		return "S".equals(esMatricula);
	}

	public void setEsMatricula(Boolean esMatricula) {
		this.esMatricula = esMatricula ? "S" : "N";
	}

	public Boolean getCancelado() {
		return "S".equals(cancelado);
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado ? "S" : "N";
	}
	
	public Boolean getAbono() {
		return "S".equals(abono);
	}

	public void setAbono(Boolean abono) {
		this.abono = abono ? "S" : "N";
	}
	
	public boolean esCredito(){
		return null != idCredito;
	}
	
}
