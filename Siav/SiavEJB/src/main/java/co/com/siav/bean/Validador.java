package co.com.siav.bean;

import java.util.List;

import co.com.siav.entities.Consumo;
import co.com.siav.entities.Exceso;
import co.com.siav.entities.IEstrato;
import co.com.siav.entities.Tarifa;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.utils.Constantes;

public class Validador {
	
	public void ejecutar(List<Consumo> consumos, Long instalacionesActivas, List<Exceso> excesos, List<Tarifa> tarifas, boolean esPorEstrato) {
		Tarifa tarifaNula = null;
		Exceso excesoNulo = null;
		if(consumos.isEmpty()){
			throw new ExcepcionNegocio(Constantes.ERR_SIN_INSTALACION_ACTIVA);
		}
		if(consumos.size()!=instalacionesActivas.intValue()){
			throw new ExcepcionNegocio(Constantes.ERR_LECTURAS_PENDIENTES);
		}
		if(esPorEstrato){
			tarifaNula = conEstrato(tarifas);
			excesoNulo = conEstrato(excesos);
			if(tarifaNula != null || excesoNulo != null){
				throw new ExcepcionNegocio(Constantes.ERR_FALTA_VALOR_ESTRATOS);
			}
		}else{
			tarifaNula = sinEstrato(tarifas);
			excesoNulo = sinEstrato(excesos);
			if(tarifaNula != null || excesoNulo != null){
				throw new ExcepcionNegocio(Constantes.ERR_FALTA_VALOR_TARIFAS);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T sinEstrato(List<? extends IEstrato>conceptos ){
		IEstrato conceptoNulo = conceptos.stream().filter(concepto -> concepto.getEstrato0().equals(0L)
				&& concepto.getTipo().equalsIgnoreCase(Constantes.FIJO)).findAny().orElse(null);
		return (T)conceptoNulo;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T conEstrato(List<? extends IEstrato>conceptos ){
		IEstrato conceptoNulo = conceptos.stream().filter(concepto -> concepto.getTipo().equalsIgnoreCase(Constantes.FIJO)
				&& (concepto.getEstrato1().equals(0L)
				|| concepto.getEstrato2().equals(0L)
				|| concepto.getEstrato3().equals(0L)
				|| concepto.getEstrato4().equals(0L)
				|| concepto.getEstrato5().equals(0L)
				|| concepto.getEstrato6().equals(0L))).findAny().orElse(null);
		return (T)conceptoNulo;
	}

}
