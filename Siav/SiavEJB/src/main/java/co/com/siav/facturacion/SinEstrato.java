package co.com.siav.facturacion;

import co.com.siav.entities.IEstrato;

public class SinEstrato implements IValorEstrato{

	@Override
	public Long getValor(IEstrato tarifa, String estrato) {
		return tarifa.getEstrato0();
	}

}
