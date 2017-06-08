package co.com.siav.response;

import java.util.ArrayList;
import java.util.List;

import co.com.siav.dto.ConsumoDTO;
import co.com.siav.dto.Ruta;
import co.com.siav.entities.CausaNoLectura;

public class MacrosResponse {
	
	private List<Ruta> macros;

    private List<ConsumoDTO> consumos;
    
    private List<CausaNoLectura> causasNoLectura;

	public List<Ruta> getMacros() {
		if(null == macros){
			return new ArrayList<Ruta>();
		}
		return macros;
	}

	public void setMacros(List<Ruta> macros) {
		this.macros = macros;
	}

	public List<ConsumoDTO> getConsumos() {
		if(null == consumos){
			return new ArrayList<ConsumoDTO>();
		}
		return consumos;
	}

	public void setConsumos(List<ConsumoDTO> consumos) {
		this.consumos = consumos;
	}
	
	public List<CausaNoLectura> getCausasNoLectura() {
		if(null == causasNoLectura){
			causasNoLectura = new ArrayList<CausaNoLectura>();
		}
		return causasNoLectura;
	}
	
	public void setCausasNoLectura(List<CausaNoLectura> causasNoLectura) {
		this.causasNoLectura = causasNoLectura;
	}
    
    

}
