package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.DetalleRecaudoExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.DetalleRecaudoDescriptor;
import co.com.siav.file.pdf.utils.DetalleRecaudoEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.DetalleRecaudo;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.utility.Util;

public class DetalleRecaudoRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		DetalleRecaudoEncabezado encabezado = new DetalleRecaudoEncabezado(Util.getEmpresa().getNombreCorto(), filter.getCiclo());
		PdfGenerator<DetalleRecaudo> generator = new PdfGenerator<DetalleRecaudo>();
		return generator.generate(getData(filter), DetalleRecaudoDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<DetalleRecaudo> generator = new ExcelReportGeneratorXLSX<DetalleRecaudo>();
		return generator.generate(getData(filter), Arrays.asList(DetalleRecaudoExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.DETALLE_RECAUDO_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.DETALLE_RECAUDO_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.DETALLE_RECAUDO, filter.getCiclo()), download(filter));
	}
	
	private List<DetalleRecaudo> getData(Filter filter) {
		String query = QueryHelper.getDetalleRecaudo(filter);
		ReportFactory<DetalleRecaudo> factory = new ReportFactory<>();
		return factory.getReportResult(DetalleRecaudo.class, query);
	}

}
