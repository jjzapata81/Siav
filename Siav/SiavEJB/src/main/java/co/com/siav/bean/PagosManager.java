package co.com.siav.bean;

import javax.inject.Inject;

import co.com.siav.entities.Comprobante;
import co.com.siav.entities.Pago;
import co.com.siav.pagos.IPago;
import co.com.siav.pagos.PagoFactory;
import co.com.siav.repositories.IRepositoryComprobante;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.utils.Constantes;


public class PagosManager {

	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryComprobante comprobanteRep;
	
	@Inject
	private PagoFactory factory;
	
	public IPago getPago(Pago pago){
		String tipo = Constantes.VACIO;
		tipo = facturasRep.exists(pago.getNumeroFactura()) ? Constantes.TIPO_FACTURA : getTipo(pago);
		return factory.getInstance(tipo);
	}
	
	private String getTipo(Pago pago) {
		if(comprobanteRep.exists(pago.getNumeroFactura())){
			Comprobante comprobante = comprobanteRep.findOne(pago.getNumeroFactura());
			if(comprobante.getAbono())
				return Constantes.TIPO_ABONO;
			if(comprobante.getEsMatricula())
				return Constantes.TIPO_MATRICULA;
			if(comprobante.esCredito())
				return Constantes.TIPO_CREDITO;
		}
		return Constantes.VACIO;
	}
}
