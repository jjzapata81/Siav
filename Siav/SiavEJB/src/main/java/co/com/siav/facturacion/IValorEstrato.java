package co.com.siav.facturacion;

import co.com.siav.entities.IEstrato;

public interface IValorEstrato {
	Long getValor(IEstrato tarifa, String estrato);
}
