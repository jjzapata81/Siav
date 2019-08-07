package co.com.siav.pagos;

import java.util.List;

import javax.inject.Inject;

import co.com.siav.bean.OrdenPago;
import co.com.siav.bean.Ordenador;
import co.com.siav.entities.Ciclo;
import co.com.siav.entities.Comprobante;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.entities.FacturaVencida;
import co.com.siav.entities.Pago;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryComprobante;
import co.com.siav.repositories.IRepositoryFacturaVencida;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.FacturaUtil;

public class PagoAbono extends PagoBase implements IPago{
	
	@Inject
	private Ordenador ordenador;
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryFacturaVencida vencidasRep;
	
	@Inject
	private IRepositoryComprobante comprobanteRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	private Long saldo;
	
	private Long saldoVencido;
	
	private boolean go;

	@Override
	public Pago process(Pago pago) {
		Comprobante comprobante = comprobanteRep.findOne(pago.getNumeroFactura());
		if(comprobante.getCancelado()){
			fail(pago, Constantes.getMensaje(Constantes.ABONO_CANCELADO, pago.getNumeroFactura(), comprobante.getFechaPago()));
		}else{
			ordenador.prepare();
			saldo = pago.getValor();
			saldoVencido = pago.getValor();
			Ciclo cicloAnterior = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO);
			Factura factura = facturasRep.findByNumeroInstalacionAndCiclo(comprobante.getInstalacion(), cicloAnterior.getCiclo());
			Long neto = FacturaUtil.getNeto(factura);
			factura.setCancelado(neto.equals(pago.getValor()));
			factura.setCodigoCuenta(pago.getCodigoCuenta());
			factura.setCodigoPuntoPago(RECAUDO_BANCO);
			factura.setFechaConsignacion(pago.getFecha());
			factura.setFechaPagoReal(pago.getFecha());
			factura.setAbono(true);
			actualizarVencidas(factura.getNumeroInstalacion());
			pago.setError(false);
			pago.setEsCredito(false);
			ordenador.get().stream().forEachOrdered(o -> partialPay(o, factura));
			factura.getDetalles().stream().filter(item-> !item.isUsed()).forEach(this::pay);
			facturasRep.save(factura);
			save(pago);
		}
		pagosRep.save(pago);
		return pago;
	}
	
	private void actualizarVencidas(Long instalacion){
		List<FacturaVencida> vencidas = vencidasRep.findByNumeroInstalacionOrderByCicloAsc(instalacion);
		go = true;
		vencidas.stream().forEach(this::checkValue);
	}

	private void checkValue(FacturaVencida vencida) {
		if(go){
			if(vencida.getValor() <= saldoVencido){
				saldoVencido = saldoVencido - vencida.getValor();
				vencidasRep.delete(vencida);
			}else{
				vencida.setValor(vencida.getValor() - saldoVencido);
				vencidasRep.save(vencida);
				go = false;
			}
		}
	}
	
	private void partialPay(OrdenPago orden, Factura factura) {
		factura.getDetalles().stream().filter(item -> item.getCodigo().equals(orden.getTarifa())).forEachOrdered(this::pay);
	}
	
	private void pay(DetalleFactura detalleFactura) {
		if(!detalleFactura.getCancelado()){
			Long netoRegistro = detalleFactura.getValor() + detalleFactura.getSaldo() - detalleFactura.getCartera();
			if(saldo < netoRegistro){
				detalleFactura.setCartera(saldo + detalleFactura.getCartera());
				saldo = 0L;
			}else{
				detalleFactura.setCartera(detalleFactura.getValor() + detalleFactura.getSaldo());
				saldo = saldo - netoRegistro;
			}
			detalleFactura.setUsed(true);
			detalleFactura.setCancelado(detalleFactura.getValor() + detalleFactura.getSaldo() == detalleFactura.getCartera());
		}
	}

}
