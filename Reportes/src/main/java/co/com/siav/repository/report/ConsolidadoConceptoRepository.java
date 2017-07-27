package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.ConsolidadoConceptoExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.ConsolidadoConceptoDescriptor;
import co.com.siav.file.pdf.utils.ConsolidadoConceptoEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.ConsolidadoConcepto;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.utility.Util;

public class ConsolidadoConceptoRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		ConsolidadoConceptoEncabezado encabezado = new ConsolidadoConceptoEncabezado(Util.getEmpresa().getNombreCorto(), filter.getCiclo());
		PdfGenerator<ConsolidadoConcepto> generator = new PdfGenerator<ConsolidadoConcepto>();
		return generator.generate(getData(filter), ConsolidadoConceptoDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<ConsolidadoConcepto> generator = new ExcelReportGeneratorXLSX<ConsolidadoConcepto>();
		return generator.generate(getData(filter), Arrays.asList(ConsolidadoConceptoExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.CONSOLIDADO_CONCEPTO_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.CONSOLIDADO_CONCEPTO_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.CONSOLIDADO_CONCEPTO, filter.getCiclo()), download(filter));
	}
	
	private List<ConsolidadoConcepto> getData(Filter filter) {
		String query = QueryHelper.getConsolidadoConcepto(filter);
		ReportBDFactory<ConsolidadoConcepto> factory = new ReportBDFactory<>();
		return factory.getReportResult(ConsolidadoConcepto.class, query).stream().sorted((a, b)-> Long.compare(a.getOrden(), b.getOrden())).collect(Collectors.toList());
	}

}
