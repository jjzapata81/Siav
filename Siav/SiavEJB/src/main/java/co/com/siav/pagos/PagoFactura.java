package co.com.siav.pagos;

import javax.inject.Inject;

import co.com.siav.entities.Factura;
import co.com.siav.entities.Pago;
import co.com.siav.repositories.IRepositoryFacturaVencida;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.utils.Constantes;

public class PagoFactura extends PagoBase implements IPago{
	
	@Inject
	private IRepositoryInstalaciones instalacionRep;
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryFacturaVencida vencidasRep;
	
	@Override
	public Pago process(Pago pago) {
		Factura factura = facturasRep.findOne(pago.getNumeroFactura());
		if(factura.getCancelado()){
			fail(pago, Constantes.getMensaje(Constantes.FACTURA_YA_CANCELADA, pago.getNumeroFactura(), factura.getFechaConsignacion()));
		}else{
			factura.setCancelado(true);
			factura.setCodigoCuenta(pago.getCodigoCuenta());
			factura.setCodigoPuntoPago(RECAUDO_BANCO);
			factura.setFechaConsignacion(pago.getFecha());
			factura.setFechaPagoReal(pago.getFecha());
			factura.getDetalles().stream().forEach(item -> item.setCancelado(true));
			factura.setAbono(false);
			instalacionRep.updateCuentasVencidasInstalacion(factura.getNumeroInstalacion());
			vencidasRep.deleteByNumeroInstalacion(factura.getNumeroInstalacion());
			facturasRep.save(factura);
		}
		pagosRep.save(pago);
		return pago;
	}

}
