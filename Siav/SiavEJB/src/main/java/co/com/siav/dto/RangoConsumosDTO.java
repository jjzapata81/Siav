package co.com.siav.dto;

import java.util.ArrayList;
import java.util.List;

public class RangoConsumosDTO {
	
	private List<ExcesoDTO> excesos;
	
	public List<ExcesoDTO> getExcesos() {
		return null == excesos ? new ArrayList<ExcesoDTO>() : excesos;
	}
	
	public void setExcesos(List<ExcesoDTO> excesos) {
		this.excesos = excesos;
	}

}
