package co.com.siav.pagos;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.utils.Constantes;

public class PagoFactory {
	
	@Inject
	private PagoFactura factura;
	
	@Inject
	private PagoCredito credito;
	
	@Inject
	private PagoMatricula matricula;
	
	@Inject
	private PagoAbono abono;
	
	@Inject
	private PagoExcedente excedente;
	
	@Inject
	private PagoFallo fallo;
	
	private Map<String, IPago> pagosMap;
	
	public IPago getInstance(String tipoPago){
		createMap();
		return pagosMap.get(tipoPago) != null ? pagosMap.get(tipoPago) : fallo;
	}

	private void createMap() {
		pagosMap = new HashMap<String, IPago>();
		pagosMap.put(Constantes.TIPO_FACTURA, factura);
		pagosMap.put(Constantes.TIPO_CREDITO, credito);
		pagosMap.put(Constantes.TIPO_MATRICULA, matricula);
		pagosMap.put(Constantes.TIPO_ABONO, abono);
		pagosMap.put(Constantes.TIPO_EXCEDENTE, excedente);
		
	}
	
}
