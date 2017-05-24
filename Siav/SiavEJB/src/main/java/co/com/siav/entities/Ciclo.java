package co.com.siav.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ta_ciclos")
public class Ciclo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ciclo")
	private Long ciclo;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="fesinrecargo")
	private Date fechaSinRecargo;
	
	@Column(name="felectura")
	private Date fechaFacturacion;
	
	@Column(name="feconrecargo")
	private Date fechaConRecargo;
	
	@Column(name="snestado")
	private String estado;
	
	@Column(name="mensaje")
	private String mensaje;
	
	@Column(name="mensajereclamo")
	private String mensajeReclamo;
	
	@Column(name="mensajepuntopago")
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

	public Date getFechaSinRecargo() {
		return fechaSinRecargo;
	}

	public void setFechaSinRecargo(Date fechaSinRecargo) {
		this.fechaSinRecargo = fechaSinRecargo;
	}

	public Date getFechaConRecargo() {
		return fechaConRecargo;
	}

	public void setFechaConRecargo(Date fechaConRecargo) {
		this.fechaConRecargo = fechaConRecargo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje == null ? "" : mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje.toUpperCase();
	}
	
	public String getMensajePuntoPago() {
		return mensajePuntoPago;
	}
	
	public void setMensajePuntoPago(String mensajePuntoPago) {
		this.mensajePuntoPago = mensajePuntoPago;
	}
	
	public String getMensajeReclamo() {
		return mensajeReclamo;
	}
	
	public void setMensajeReclamo(String mensajeReclamo) {
		this.mensajeReclamo = mensajeReclamo;
	}
	
	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public String getNombreMes(){
		SimpleDateFormat formateador = new SimpleDateFormat("MMMMM 'de' yyyy");
		return formateador.format(fecha).toString().toUpperCase();
	}
	
}
