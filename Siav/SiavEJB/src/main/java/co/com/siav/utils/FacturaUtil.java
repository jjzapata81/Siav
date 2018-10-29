package co.com.siav.utils;

import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;

public final class FacturaUtil {

	private FacturaUtil() {
		super();
	}
	
	public static Long getNeto(Factura factura) {
		Long totalValor = factura.getDetalles().stream().mapToLong(DetalleFactura::getValor).sum();
		Long totalSaldo = factura.getDetalles().stream().mapToLong(DetalleFactura::getSaldo).sum();
		Long totalCartera = factura.getDetalles().stream().mapToLong(DetalleFactura::getCartera).sum();
		return totalValor + totalSaldo - totalCartera;
	}
	
	public static Long getValor(Factura factura) {
		return factura.getDetalles().stream().mapToLong(DetalleFactura::getValor).sum();
	}
}
