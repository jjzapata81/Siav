package co.com.siav.pagos;

import javax.inject.Inject;

import co.com.siav.entities.Comprobante;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.Pago;
import co.com.siav.repositories.IRepositoryComprobante;
import co.com.siav.repositories.IRepositoryCreditoMaestro;
import co.com.siav.utils.Constantes;

public class PagoCredito extends PagoBase implements IPago {
	
	@Inject
	private IRepositoryComprobante comprobanteRep;
	
	@Inject
	private IRepositoryCreditoMaestro creditosRep;

	@Override
	public Pago process(Pago pago) {
		Comprobante comprobante = comprobanteRep.findOne(pago.getNumeroFactura());
		if(comprobante.getCancelado()){
			fail(pago, Constantes.getMensaje(Constantes.COMPROBANTE_CANCELADO, pago.getNumeroFactura(), comprobante.getFechaPago()));
		}else{
			CreditoMaestro credito = creditosRep.findOne(comprobante.getIdCredito());
			if(creditoValido(credito, pago, comprobante.getIdCredito())){
				Long nuevoSaldo = credito.getSaldo() - pago.getValor();
				credito.setSaldo(nuevoSaldo < 0L ? 0L : nuevoSaldo);
				credito.setFechaFinal(credito.getSaldo().equals(0L) ? pago.getFecha() : null);
				creditosRep.save(credito);
				pago.setMensaje(PAGO_COMPROBANTE + comprobante.getIdComprobante());
				pago.setEsCredito(true);
				save(pago);
			}
		}
		pagosRep.save(pago);
		return pago;
	}
	
	private boolean creditoValido(CreditoMaestro credito, Pago pago, Long id) {
		if(null == credito){
			fail(pago, Constantes.getMensaje(Constantes.CREDITO_NO_EXISTE, id));
			return false;
		}else if(credito.getSaldo()<=0L){
			fail(pago, Constantes.getMensaje(Constantes.CREDITO_CANCELADO, id, credito.getFechaFinal()));
			return false;
		}
		return true;
	}

}
