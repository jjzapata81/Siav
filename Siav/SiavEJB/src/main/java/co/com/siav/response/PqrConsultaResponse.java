package co.com.siav.response;

import java.util.List;

public class PqrConsultaResponse {
	
	private boolean puedeEditar;
	
	private List<PqrDetalleResponse> detalles;

	public boolean isPuedeEditar() {
		return puedeEditar;
	}

	public void setPuedeEditar(boolean puedeEditar) {
		this.puedeEditar = puedeEditar;
	}

	public List<PqrDetalleResponse> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<PqrDetalleResponse> detalles) {
		this.detalles = detalles;
	}
	

}
