package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ta_log_pagos")
public class Pago implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "Pago.id";

	@Id
	@SequenceGenerator(name = Pago.NOMBRE_SECUENCIA, sequenceName = "sq_ta_log_pagos", allocationSize=1)
	@GeneratedValue(generator = Pago.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="fehasta")
	private Date fecha;
	
	@Column(name="valor")
	private Long valor;
	
	@Column(name="nmfactura")
	private Long numeroFactura;
	
	@Column(name="cdcuenta")
	private String codigoCuenta;
	
	@JoinColumn(name="cdcuenta",referencedColumnName="codigo", updatable=false, insertable=false)
	@ManyToOne
	private CuentaBanco cuenta;
	
	@Column(name="snerror")
	private String error;
	
	@Column(name="sncredito")
	private String esCredito;
	
	@Column(name="mensaje")
	private String mensaje;
	
	@Column(name="nombreusuario")
	private String usuario;
	
	@Column(name="rutapago")
	private String rutaPago;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = Long.valueOf(valor);
	}

	public Long getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = Long.valueOf(numeroFactura);
	}

	public boolean isError() {
		return "S".equals(error);
	}

	public void setError(boolean error) {
		this.error = error ? "S" : "N";
	}
	
	public boolean getEsCredito() {
		return "S".equals(esCredito);
	}

	public void setEsCredito(boolean esCredito) {
		this.esCredito = esCredito ? "S" : "N";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getCodigoCuenta() {
		return codigoCuenta;
	}
	
	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getRutaPago() {
		return rutaPago;
	}

	public void setRutaPago(String rutaPago) {
		this.rutaPago = rutaPago;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public CuentaBanco getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaBanco cuenta) {
		this.cuenta = cuenta;
	}
	
	

	
}
