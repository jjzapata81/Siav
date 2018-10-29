package co.com.siav.pagos;

import javax.inject.Inject;

import co.com.siav.entities.Factura;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;
import co.com.siav.entities.Pago;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryNovedades;
import co.com.siav.repositories.IRepositorySistema;
import co.com.siav.utils.Constantes;

public class PagoExcedente extends PagoBase implements IPago{
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private IRepositoryNovedades novedadRep;
	
	@Inject
	private IRepositorySistema sistemaRep;

	@Override
	public Pago process(Pago pago) {
		Factura factura = facturasRep.findOne(pago.getNumeroFactura());
		Novedad novedad = new Novedad();
		novedad.setId(buildId(factura));
		novedad.setBorrable(false);
		novedad.setValor(-1 * pago.getValor());
		pago.setMensaje(Constantes.getMensaje(Constantes.FACTURA_YA_CANCELADA, factura.getNumeroFactura()));
		novedadRep.save(novedad);
		pagosRep.save(pago);
		return pago;
	}
	
	private NovedadPK buildId(Factura factura) {
		NovedadPK pk = new NovedadPK();
		pk.setInstalacion(factura.getNumeroInstalacion());
		pk.setCiclo(ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO));
		pk.setCodigoConcepto(sistemaRep.findOne(1L).getIdSaldoFavor());
		return pk;
	}

}
