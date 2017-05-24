package co.com.siav.request;

import java.util.List;

import co.com.siav.dto.ConsumoDTO;

public class ConsumosRequest {
	
	private List<ConsumoDTO> consumos;
	
	private String usuario;

	public List<ConsumoDTO> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<ConsumoDTO> consumos) {
		this.consumos = consumos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
