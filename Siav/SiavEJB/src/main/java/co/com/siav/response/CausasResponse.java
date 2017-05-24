package co.com.siav.response;

import java.util.List;

import co.com.siav.entities.CausaNoLectura;

public class CausasResponse {
	
	private List<CausaNoLectura> causas;
	
	public List<CausaNoLectura> getCausas() {
		return causas;
	}
	
	public void setCausas(List<CausaNoLectura> causas) {
		this.causas = causas;
	}

}
