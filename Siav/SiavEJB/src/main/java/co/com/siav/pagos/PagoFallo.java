package co.com.siav.pagos;

import javax.inject.Inject;

import co.com.siav.entities.Comprobante;
import co.com.siav.entities.Pago;
import co.com.siav.repositories.IRepositoryComprobante;
import co.com.siav.utils.Constantes;

public class PagoFallo extends PagoBase implements IPago{
	
	
	@Inject
	private IRepositoryComprobante comprobanteRep;

	@Override
	public Pago process(Pago pago) {
		fail(pago, getMensaje(pago));
		pagosRep.save(pago);
		return pago;
	}
	
	private String getMensaje(Pago pago) {
		if(!comprobanteRep.exists(pago.getNumeroFactura()))
			return Constantes.FACTURA_NO_EXISTE;
		Comprobante comprobante = comprobanteRep.findOne(pago.getNumeroFactura());
		if(comprobante.getCancelado())
			return Constantes.getMensaje(Constantes.COMPROBANTE_CANCELADO, pago.getNumeroFactura(), comprobante.getFechaPago());
		return Constantes.getMensaje(Constantes.PAGO_ERROR, comprobante.getIdComprobante());
	}

}
