package co.com.siav.response;

import java.util.List;

import co.com.siav.dto.ConsumoDTO;

public class ConsumosResponse {
	
	private List<ConsumoDTO> consumos;
	
	public ConsumosResponse(List<ConsumoDTO> consumos){
		this.consumos = consumos;
	}

	public List<ConsumoDTO> getConsumos() {
		return consumos;
	}
	
	public void setConsumos(List<ConsumoDTO> consumos) {
		this.consumos = consumos;
	}

}
