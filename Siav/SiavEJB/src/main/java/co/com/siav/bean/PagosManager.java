package co.com.siav.bean;

import javax.inject.Inject;

import co.com.siav.entities.Comprobante;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;
import co.com.siav.entities.Pago;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryCreditoMaestro;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryNovedades;
import co.com.siav.repositories.IRepositoryPago;
import co.com.siav.repositories.IRepositorySistema;
import co.com.siav.utils.Constantes;


public class PagosManager {

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
	
	@Inject
	private IRepositoryCreditoMaestro creditosRep;
	
	@Inject
	private Ordenador ordenador;
	
	private Long valorRecaudo;
	
	private String codigoPuntoPago; 
	
	public void run(String codigoPuntoPago){
		ordenador.prepare();
		this.codigoPuntoPago = codigoPuntoPago;
		
	}

	public void addFail(Pago pago, String mensaje) {
		if(null == pago.getMensaje() || pago.getMensaje().length() == 0){
			pago.setError(true);
			pago.setMensaje(mensaje);
		}
		pagosRep.save(pago);
	}
	
	public Factura process(Factura factura, Pago pago) {
		Long neto = factura.getDetalles().stream().mapToLong(DetalleFactura::getValor).sum()
				+ factura.getDetalles().stream().mapToLong(DetalleFactura::getSaldo).sum()
				- factura.getDetalles().stream().mapToLong(DetalleFactura::getCartera).sum();
		factura.setCancelado(true);
		factura.setCodigoCuenta(pago.getCodigoCuenta());
		factura.setCodigoPuntoPago(codigoPuntoPago);
		factura.setFechaConsignacion(pago.getFecha());
		factura.setFechaPagoReal(pago.getFecha());
		if(pago.getValor() >= neto){
			factura.getDetalles().stream().forEach(item -> item.setCancelado(true));
			factura.setAbono(false);
			saveExcedent(pago.getValor(), neto, factura);
		}else{
			factura.setAbono(true);
			valorRecaudo = pago.getValor();
			ordenador.get().stream().forEachOrdered(o -> partialPay(o, factura));
		}
		pagosRep.save(pago);
		instalacionRep.updateCuentasVencidasInstalacion(factura.getNumeroInstalacion());
		return factura;
	}
	
	private void saveExcedent(Long valorRecaudo, Long neto, Factura factura) {
		Long excedente = valorRecaudo - neto;
		if(excedente > 0){
			Novedad novedad = new Novedad();
			novedad.setId(buildId(factura));
			novedad.setBorrable(false);
			novedad.setValor(-1 * excedente);
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

	private void partialPay(OrdenPago orden, Factura factura) {
		factura.getDetalles().stream().filter(item -> item.getCodigo().equals(orden.getTarifa())).forEachOrdered(this::pay);
	}

	private void pay(DetalleFactura detalleFactura) {
		if(!detalleFactura.getCancelado()){
			Long netoRegistro = detalleFactura.getValor() + detalleFactura.getSaldo() - detalleFactura.getCartera();
			if(valorRecaudo < netoRegistro){
				detalleFactura.setCartera(valorRecaudo + detalleFactura.getCartera());
				valorRecaudo = 0L;
			}else{
				detalleFactura.setCartera(detalleFactura.getValor() + detalleFactura.getSaldo());
				valorRecaudo = valorRecaudo - netoRegistro;
			}
			detalleFactura.setCancelado(detalleFactura.getValor() + detalleFactura.getSaldo() == detalleFactura.getCartera());
		}
	}

	public void addOtherPay(Pago pago, Comprobante comprobante) {
		pago.setEsCredito(null != comprobante.getIdCredito());
		if(!comprobante.getCancelado()){
			addComprobantPay(pago, comprobante);
		}else{
			addFail(pago, Constantes.getMensaje(Constantes.COMPROBANTE_CANCELADO, pago.getNumeroFactura()));
		}
	}

	private void addComprobantPay(Pago pago, Comprobante comprobante) {
		if(comprobante.getEsMatricula()){
			pago.setMensaje("Pago de comprobante " + comprobante.getIdComprobante());
			pagosRep.save(pago);
		}else{
			CreditoMaestro credito = creditosRep.findOne(comprobante.getIdCredito());
			if(null != credito){
				if(credito.getSaldo()>0L){
					Long nuevoSaldo = credito.getSaldo()-pago.getValor();
					credito.setSaldo(nuevoSaldo < 0L ? 0L : nuevoSaldo);
					if(credito.getSaldo().equals(0L)){
						credito.setFechaFinal(pago.getFecha());
					}
					creditosRep.save(credito);
					pago.setMensaje("Pago de comprobante " + comprobante.getIdComprobante());
					pagosRep.save(pago);
				}
				else{
					addFail(pago, Constantes.getMensaje(Constantes.CREDITO_CANCELADO, comprobante.getIdCredito()));
				}
			}else{
				addFail(pago, Constantes.getMensaje(Constantes.CREDITO_NO_EXISTE, comprobante.getIdCredito()));
			}
		}
	}

}
