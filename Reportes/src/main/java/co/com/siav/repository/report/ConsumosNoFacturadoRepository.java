package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.ConsumoNoFacturadoExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.ConsumoNoFacturadoDescriptor;
import co.com.siav.file.pdf.utils.ConsumoNoFacturadoEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.ConsumoNoFacturado;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.utility.Util;

public class ConsumosNoFacturadoRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		ConsumoNoFacturadoEncabezado encabezado = new ConsumoNoFacturadoEncabezado(Util.getEmpresa().getNombreCorto(), filter.getCiclo());
		PdfGenerator<ConsumoNoFacturado> generator = new PdfGenerator<ConsumoNoFacturado>();
		return generator.generate(getData(filter), ConsumoNoFacturadoDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<ConsumoNoFacturado> generator = new ExcelReportGeneratorXLSX<ConsumoNoFacturado>();
		return generator.generate(getData(filter), Arrays.asList(ConsumoNoFacturadoExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.CONSUMO_NO_FACTURADO_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.CONSUMO_NO_FACTURADO_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.CONSUMO_NO_FACTURADO, filter.getCiclo()), download(filter));
	}
	
	private List<ConsumoNoFacturado> getData(Filter filter) {
		String query = QueryHelper.getConsumoNoFacturado(filter);
		ReportBDFactory<ConsumoNoFacturado> factory = new ReportBDFactory<>();
		return factory.getReportResult(ConsumoNoFacturado.class, query).stream().sorted((a, b)-> Long.compare(a.getOrden(), b.getOrden())).collect(Collectors.toList());
	}

}
