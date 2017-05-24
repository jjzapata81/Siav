package co.com.siav.repository.entities;

import java.util.Date;

public class Ciclo {
	
	private Long ciclo;
	private Date fecha;
	private Date feFactura;
	private Date fesinrecargo;
	private Date feconrecargo;
	private String snestado;
	private String mensaje;
	private String mensajeReclamo;
	private String mensajePuntoPago;
	
	public Long getCiclo() {
		return ciclo;
	}
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Date getFeFactura() {
		return feFactura;
	}
	
	public void setFeFactura(Date feFactura) {
		this.feFactura = feFactura;
	}
	
	public Date getFesinrecargo() {
		return fesinrecargo;
	}
	public void setFesinrecargo(Date fesinrecargo) {
		this.fesinrecargo = fesinrecargo;
	}
	public Date getFeconrecargo() {
		return feconrecargo;
	}
	public void setFeconrecargo(Date feconrecargo) {
		this.feconrecargo = feconrecargo;
	}
	public String getSnestado() {
		return snestado;
	}
	public void setSnestado(String snestado) {
		this.snestado = snestado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getMensajeReclamo() {
		return mensajeReclamo;
	}
	public void setMensajeReclamo(String mensajeReclamo) {
		this.mensajeReclamo = mensajeReclamo;
	}
	public String getMensajePuntoPago() {
		return mensajePuntoPago;
	}
	public void setMensajePuntoPago(String mensajePuntoPago) {
		this.mensajePuntoPago = mensajePuntoPago;
	}
}
