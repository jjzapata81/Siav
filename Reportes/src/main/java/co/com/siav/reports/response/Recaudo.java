package co.com.siav.reports.response;

import java.util.Date;
import java.util.List;

public class Recaudo {
	
	private Date fecha;
	
	private List<RecaudoDetalle> detalle;
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public List<RecaudoDetalle> getDetalle() {
		return detalle;
	}
	
	public void setDetalle(List<RecaudoDetalle> detalle) {
		this.detalle = detalle;
	}
	
	public Long getTotal(){
		if(detalle.isEmpty()){
			return 0L;
		}
		return detalle.stream().mapToLong(RecaudoDetalle::getValor).sum();
	}

}
