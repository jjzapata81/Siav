package co.com.siav.response;

import java.util.Date;

import co.com.siav.entities.Factura;

public class FacturaInfo {
	
	private static final String SI = "SI";

	private static final String NO = "NO";

	private Long numeroFactura;
	
	private Long valor;
	
	private Date fechaPago;
	
	private Long cuentasVencidas;
	
	private Long ciclo;
	
	private String cancelado;
	
	private String medidor;
	
	public FacturaInfo(){
		super();
	}
	
	public FacturaInfo(Factura factura){
		this.numeroFactura = factura.getNumeroFactura();
		this.valor = factura.getDetalles().stream().mapToLong(item->{return item.getValor() + item.getSaldo() - item.getCartera();}).sum();
		this.fechaPago = factura.getFechaPagoReal();
		this.cuentasVencidas = factura.getCuentasVencidas();
		this.ciclo = factura.getCiclo();
		this.cancelado = factura.getCancelado() ? SI : NO;
		this.medidor = factura.getSerieMedidor();
	}
	
	public Long getNumeroFactura() {
		return numeroFactura;
	}
	
	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	
	public Long getValor() {
		return valor;
	}
	
	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Long getCuentasVencidas() {
		return cuentasVencidas;
	}

	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}
	
	public String getMedidor() {
		return medidor;
	}
	
	public void setMedidor(String medidor) {
		this.medidor = medidor;
	}
	
}
