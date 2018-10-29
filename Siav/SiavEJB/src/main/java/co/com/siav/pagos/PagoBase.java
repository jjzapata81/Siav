package co.com.siav.pagos;

import javax.inject.Inject;

import co.com.siav.entities.Comprobante;
import co.com.siav.entities.Pago;
import co.com.siav.repositories.IRepositoryComprobante;
import co.com.siav.repositories.IRepositoryPago;

public class PagoBase {
	
	protected static final String RECAUDO_BANCO = "1";
	
	protected static final String PAGO_COMPROBANTE = "Pago de comprobante ";
	
	@Inject
	protected IRepositoryComprobante comprobanteRep;

	@Inject
	protected IRepositoryPago pagosRep;
	
	protected Pago fail(Pago pago, String message){
		pago.setMensaje(message);
		pago.setError(true);
		return pago;
	}
	
	protected void save(Pago pago){
		if(comprobanteRep.exists(pago.getNumeroFactura())){
			Comprobante comprobante = comprobanteRep.findOne(pago.getNumeroFactura());
			comprobante.setCancelado(true);
			comprobante.setFechaPago(pago.getFecha());
			comprobanteRep.save(comprobante);
		}
	}
	
}
