package co.com.siav.pagos;

import co.com.siav.entities.Comprobante;
import co.com.siav.entities.Pago;
import co.com.siav.utils.Constantes;

public class PagoMatricula extends PagoBase implements IPago{
	
	@Override
	public Pago process(Pago pago) {
		Comprobante comprobante = comprobanteRep.findOne(pago.getNumeroFactura());
		if(comprobante.getCancelado()){
			fail(pago, Constantes.getMensaje(Constantes.COMPROBANTE_CANCELADO, pago.getNumeroFactura(), comprobante.getFechaPago()));
		}else{
			save(pago);
			pago.setMensaje(PAGO_COMPROBANTE + pago.getNumeroFactura());
		}
		pagosRep.save(pago);
		return pago;
	}

}
