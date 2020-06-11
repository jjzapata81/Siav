package co.com.siav.repository.entities;

import java.util.Date;

public class CarteraTotal {
	
	private int orden;
    private String cedula;
    private String nombre;
    private Long instalacion;
    private Date fecha;
    private Long comprobante;
    private Long valor;
    private String cancelado;
    private Long cuentasVencidas;
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	public Long getComprobante() {
		return comprobante;
	}
	
	public void setComprobante(Long comprobante) {
		this.comprobante = comprobante;
	}
	
	public String getCancelado() {
		return cancelado;
	}
	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}
	public Long getCuentasVencidas() {
		return cuentasVencidas;
	}
	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}
   

}
