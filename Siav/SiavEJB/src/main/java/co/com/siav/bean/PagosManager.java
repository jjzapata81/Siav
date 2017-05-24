package co.com.siav.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;
import co.com.siav.entities.OrdenAbono;
import co.com.siav.entities.Pago;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryNovedades;
import co.com.siav.repositories.IRepositoryOrdenAbono;
import co.com.siav.repositories.IRepositoryPago;
import co.com.siav.repositories.IRepositorySistema;
import co.com.siav.utils.Constantes;


public class PagosManager {

	@Inject
	private IRepositoryOrdenAbono abonoRep;
	
	@Inject
	private IRepositoryPago pagosRep;
	
	@Inject
	private IRepositoryNovedades novedadRep;
	
	@Inject
	private IRepositorySistema sistemaRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	private List<Pago> fails;
	
	private List<OrdenAbono> orden;
	
	private Long controlPago;
	
	private String codigoPuntoPago; 
	
	public void run(String codigoPuntoPago){
		orden = abonoRep.findAll();
		this.codigoPuntoPago = codigoPuntoPago;
	}

	public void addFail(Pago pago, String mensaje) {
		init();
		pago.setError(true);
		pago.setMensaje(mensaje);
		pagosRep.save(pago);
		fails.add(pago);
	}
	
	public List<Pago> getFails(){
		return fails;
	}
	
	public int getFailsSize() {
		return null == fails ? 0 : fails.size();
	}

	public Factura process(Factura factura, Pago pago) {
		Long saldo = factura.getDetalles().stream().mapToLong(DetalleFactura::getSaldo).sum();
		Long valor = factura.getDetalles().stream().mapToLong(DetalleFactura::getValor).sum();
		factura.setCancelado(true);
		factura.setCodigoCuenta(pago.getCodigoCuenta());
		factura.setCodigoPuntoPago(codigoPuntoPago);
		factura.setFechaConsignacion(pago.getFecha());
		factura.setFechaPagoReal(pago.getFecha());
		if(pago.getValor() == saldo + valor){
			factura.getDetalles().stream().forEach(item -> item.setCancelado(true));
		}else{
			controlPago = pago.getValor();
			orden.stream().forEachOrdered(o -> partialPay(o, factura));
			factura.getDetalles().stream().filter(detalle -> !detalle.getAcumulado()).forEachOrdered(this::pay);
			saveExcedent(controlPago, factura);
		}
		pagosRep.save(pago);
		instalacionRep.updateCuentasVencidasInstalacion(factura.getNumeroInstalacion());
		return factura;
	}
	
	private void saveExcedent(Long controlPago, Factura factura) {
		if(controlPago > 0){
			Novedad novedad = new Novedad();
			novedad.setId(buildId(factura));
			novedad.setBorrable(false);
			novedad.setValor(-1 * controlPago);
			novedadRep.save(novedad);
		}
	}

	private NovedadPK buildId(Factura factura) {
		NovedadPK pk = new NovedadPK();
		pk.setInstalacion(factura.getNumeroInstalacion());
		pk.setCiclo(ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO));
		pk.setCodigoConcepto(sistemaRep.findOne(1L).getIdSaldoFavor());
		return pk;
	}

	private void partialPay(OrdenAbono orden, Factura factura) {
		factura.getDetalles().stream().filter(item -> item.getCodigo().equals(orden.getCodigoConcepto())).forEachOrdered(this::pay);
	}

	private void pay(DetalleFactura detalleFactura) {
		detalleFactura.setAcumulado(true);
		detalleFactura.setCartera(
					controlPago < detalleFactura.getValor() + detalleFactura.getSaldo()
					? controlPago
					: detalleFactura.getValor() + detalleFactura.getSaldo()
				);
		controlPago = controlPago < detalleFactura.getCartera() ? 0L : controlPago - detalleFactura.getCartera();
		detalleFactura.setCancelado(detalleFactura.getValor() + detalleFactura.getSaldo() == detalleFactura.getCartera());
	}

	private void init(){
		if(fails == null){
			fails = new ArrayList<Pago>();
		}
	}

}
