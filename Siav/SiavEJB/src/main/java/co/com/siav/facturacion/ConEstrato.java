package co.com.siav.facturacion;

import co.com.siav.entities.IEstrato;
import co.com.siav.enums.Estrato;

public class ConEstrato implements IValorEstrato{

	@Override
	public Long getValor(IEstrato tarifa, String estrato) {

		if(Estrato.UNO.getValor().equalsIgnoreCase(estrato)){
			return tarifa.getEstrato1();
		}
		if(Estrato.DOS.getValor().equalsIgnoreCase(estrato)){
			return tarifa.getEstrato2();
		}
		if(Estrato.TRES.getValor().equalsIgnoreCase(estrato)){
			return tarifa.getEstrato3();
		}
		if(Estrato.CUATRO.getValor().equalsIgnoreCase(estrato)){
			return tarifa.getEstrato4();
		}
		if(Estrato.CINCO.getValor().equalsIgnoreCase(estrato)){
			return tarifa.getEstrato5();
		}
		if(Estrato.SEIS.getValor().equalsIgnoreCase(estrato)){
			return tarifa.getEstrato6();
		}
		return 0L;
	}
}
