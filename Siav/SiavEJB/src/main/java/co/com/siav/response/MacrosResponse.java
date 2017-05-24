package co.com.siav.response;

import java.util.ArrayList;
import java.util.List;

import co.com.siav.dto.ConsumoDTO;
import co.com.siav.dto.Macromedidor;
import co.com.siav.entities.CausaNoLectura;

public class MacrosResponse {
	
	private List<Macromedidor> macros;

    private List<ConsumoDTO> consumos;
    
    private List<CausaNoLectura> causasNoLectura;

	public List<Macromedidor> getMacros() {
		if(null == macros){
			return new ArrayList<Macromedidor>();
		}
		return macros;
	}

	public void setMacros(List<Macromedidor> macros) {
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
