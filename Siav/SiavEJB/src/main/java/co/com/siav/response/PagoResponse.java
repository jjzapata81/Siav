package co.com.siav.response;

import java.util.Date;
import java.util.List;

import co.com.siav.entities.Pago;

public class PagoResponse {
	
	private Date fecha;
	private List<Pago> pagos;
	
	public PagoResponse(Date fecha, List<Pago> pagos) {
		this.fecha = fecha;
		this.pagos = pagos;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public List<Pago> getPagos() {
		return pagos;
	}
	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

}
