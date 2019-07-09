package co.com.siav.repository.report;

import java.util.List;

import co.com.siav.exception.TechnicalException;
import co.com.siav.pdf.dto.Articulo;
import co.com.siav.pdf.dto.FacturaBD;
import co.com.siav.pdf.dto.FacturaDetalleBD;
import co.com.siav.pdf.dto.FacturaPDF;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Ciclo;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.entities.Sistema;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;
import co.com.siav.utility.FacturaUtil;

public abstract class FacturaBase {
	
	protected Empresa empresa;
	protected Ciclo ciclo;
	protected Sistema sistema;
	protected String resolucion;
	protected List<Articulo> articulos;
	
	protected List<Articulo> getArticulos() {
		String query = QueryHelper.getArticulos();
		ReportBDFactory<Articulo> factory = new ReportBDFactory<>();
		return factory.getReportResult(Articulo.class, query);
	}
	
	protected FacturaPDF transform(FacturaBD facturaBD){
		List<FacturaDetalleBD> detalles = getDetalles(facturaBD.getNumeroFactura());
		FacturaPDF facturaPDF = new FacturaPDF();
		facturaPDF.setCiclo(facturaBD.getCiclo());
		facturaPDF.setNombre(facturaBD.getNombre());
		facturaPDF.setInstalacion(facturaBD.getInstalacion());
		facturaPDF.setDireccion(facturaBD.getDireccion());
		facturaPDF.setVereda(facturaBD.getVereda());
		facturaPDF.setNumeroFactura(facturaBD.getNumeroFactura());
		facturaPDF.setConsumoActual(facturaBD.getConsumoActual());
		facturaPDF.setPromedioConsumo(facturaBD.getPromedioConsumo());
		facturaPDF.setCuentasVencidas(facturaBD.getCuentasVencidas());
		facturaPDF.setDiasConsumo(facturaBD.getDiasConsumo());
		facturaPDF.setLecturaActual(facturaBD.getLecturaActual());
		facturaPDF.setLecturaAnterior(facturaBD.getLecturaAnterior());
		facturaPDF.setReferente(facturaBD.getInstalacion() + facturaBD.getNumeroFactura() + facturaBD.getCiclo());
		facturaPDF.setFePagoRecargo(ciclo.getFeconrecargo());
		facturaPDF.setFePagoSinRecargo(ciclo.getFesinrecargo());
		facturaPDF.setMensajePrincipal(ciclo.getMensaje());
		facturaPDF.setNit(empresa.getNit());
		facturaPDF.setNombreAcueducto(empresa.getNombreCorto());
		facturaPDF.setValoresFacturados(FacturaUtil.getValoresFacturados(detalles, sistema));
		facturaPDF.setTotalVencido((detalles.stream().mapToLong(FacturaDetalleBD::getSaldo)).sum());
		facturaPDF.setValorTotal(getValorTotal(detalles, facturaPDF));
		facturaPDF.setCodigoBarras(Util.getCodigoBarras(facturaPDF.getReferente(), facturaPDF.getValorTotal(), facturaPDF.getFePagoRecargo() == null ? facturaPDF.getFePagoSinRecargo() : facturaPDF.getFePagoRecargo()));
		facturaPDF.setResolucion(resolucion);
		facturaPDF.setConsumoAnterior(facturaBD.getConsumoAnterior());
		facturaPDF.setConsumos(FacturaUtil.getConsumos(facturaBD.getHistoricoConsumo(), facturaBD.getInstalacion()));
		facturaPDF.setEstrato(facturaBD.getEstrato());
		facturaPDF.setFechaFacturacion(ciclo.getFeFactura());
		facturaPDF.setFechaActual(facturaBD.getFechaActual());
		facturaPDF.setFechaAnterior(facturaBD.getFechaAnterior());
		facturaPDF.setMedidor(facturaBD.getMedidor());
		facturaPDF.setFechaUltimoPago(facturaBD.getFechaUltimoPago());
		facturaPDF.setMensajePuntoPago(ciclo.getMensajePuntoPago());
		facturaPDF.setMensajeReclamo(ciclo.getMensajeReclamo());
		facturaPDF.setMensajeCorte(sistema.getCuentasCorte() <= facturaBD.getCuentasVencidas() ? ciclo.getMensajeCorte() : Constantes.EMPTY);
		facturaPDF.setOtrosCobros(FacturaUtil.getOtrosCobros(detalles, sistema, articulos));
		facturaPDF.setValorMesAnterior(facturaBD.getValorMesAnterior());
		facturaPDF.setCreditos(FacturaUtil.getCreditos(facturaBD.getInstalacion()));
		facturaPDF.setMateriales(FacturaUtil.getMateriales(detalles, sistema, articulos));
		return facturaPDF;
	}

	private Long getValorTotal(List<FacturaDetalleBD> detalles, FacturaPDF facturaPDF) {
		return (long) ((detalles.stream().mapToLong(FacturaDetalleBD::getValor)).sum()
				+ facturaPDF.getTotalVencido());
	}
	
	private List<FacturaDetalleBD> getDetalles(String numeroFactura){
		try{
			String query = QueryHelper.getFacturaDetalle(numeroFactura);
			ReportBDFactory<FacturaDetalleBD> factory = new ReportBDFactory<>();
			return factory.getReportResult(FacturaDetalleBD.class, query);
		}catch(Exception e){
			throw new TechnicalException(Constantes.ERR_DETALLE_FACTURA + numeroFactura + ". " + e.getMessage());
		}
	}

}
