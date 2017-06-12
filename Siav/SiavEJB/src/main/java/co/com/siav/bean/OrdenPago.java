package co.com.siav.bean;

public class OrdenPago {
	
	private Long orden;
	
	private String tarifa;
	
	public OrdenPago(Long orden, String tarifa) {
		this.tarifa = tarifa;
		this.orden = orden;
	}

	public Long getOrden() {
		return orden;
	}
	
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	
	public String getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

}
