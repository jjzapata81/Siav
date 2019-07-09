package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.dto.FacturaBD;
import co.com.siav.pdf.dto.FacturaPDF;
import co.com.siav.pdf.dto.FacturacionPDF;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.ConsultaRango;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class FacturaRepository extends FacturaBase implements IReportType{
	
	@Inject
	private SendMail notifier;

	@Override
	public byte[] getPDF(Filter filter) {
		getValoresGenerales(filter);
		if(ciclo.getSnestado().equals(Constantes.ABIERTO)){
			throw new TechnicalException(Constantes.ERR_CICLO_ABIERTO);
		}
		return new GeneradorPDF(getFactura(filter), Constantes.FACTURA_JRXML).getStream();
	}

	@Override
	public byte[] download(Filter filter) {
		return null;
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.FACTURACION_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.FACTURACION_CUERPO, ciclo);
	}
	
	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.FACTURACION, filter.getCiclo()), getPDF(filter));
	}
	
	private void getValoresGenerales(Filter filter) {
		empresa = Util.getEmpresa();
		ciclo = Util.getCiclo(filter);
		sistema = Util.getSistema();
		resolucion = Util.getParametro(new ConsultaRango(Constantes.ATT_RESOLUCION, Constantes.ESTADO_VIGENTE));
		articulos = getArticulos();
	}
	
	private List<FacturacionPDF> getFactura(Filter filter) {
		FacturacionPDF factura = new FacturacionPDF();
		factura.setFacturas(getFacturas(filter));
		return Arrays.asList(factura);
	}

	private List<FacturaPDF> getFacturas(Filter filter) {
		String query = QueryHelper.getFacturaEncabezado(filter);
		ReportBDFactory<FacturaBD> factory = new ReportBDFactory<>();
		List<FacturaBD> instalaciones = factory.getReportResult(FacturaBD.class, query);
		if(null == instalaciones || instalaciones.isEmpty()){
			throw new TechnicalException(Constantes.FACTURA_NO_EXISTE);
		}
		return instalaciones.stream().map(this::transform).collect(Collectors.toList());
	}


}
