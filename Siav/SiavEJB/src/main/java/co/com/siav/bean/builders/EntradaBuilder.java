package co.com.siav.bean.builders;

import co.com.siav.entities.EntradaMaestro;
import co.com.siav.request.MaterialRequest;

public final class EntradaBuilder {
	
	private EntradaBuilder(){
		super();
	}

	public static EntradaMaestro crear(MaterialRequest request, Long codigo, Long ciclo) {
		EntradaMaestro entrada = new EntradaMaestro();
		entrada.setCodigo(codigo);
		entrada.setCiclo(ciclo);
		entrada.setCodFacturaCompra(request.getFactura());
		entrada.setCodProveedor(request.getProveedor().getCodigo());
		entrada.setFechaFacturaCompra(request.getFecha());
		return entrada;
	}

}
