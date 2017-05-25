package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.LecturasConsumosExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.LecturasConsumosDescriptor;
import co.com.siav.file.pdf.utils.LecturasConsumosEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.LecturasConsumos;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.utility.Util;

public class LecturasConsumosRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		LecturasConsumosEncabezado encabezado = new LecturasConsumosEncabezado(Util.getEmpresa().getNombreCorto(), filter.getCiclo());
		PdfGenerator<LecturasConsumos> generator = new PdfGenerator<LecturasConsumos>();
		return generator.generate(getLecturasConsumos(filter), LecturasConsumosDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<LecturasConsumos> generator = new ExcelReportGeneratorXLSX<LecturasConsumos>();
		return generator.generate(getLecturasConsumos(filter), Arrays.asList(LecturasConsumosExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.LECTURAS_CONSUMOS_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.LECTURAS_CONSUMOS_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.LECTURAS_CONSUMOS, filter.getCiclo()), download(filter));
	}
	
	private List<LecturasConsumos> getLecturasConsumos(Filter filter) {
		String query = QueryHelper.getLecturasConsumos(filter);
		ReportFactory<LecturasConsumos> factory = new ReportFactory<>();
		return factory.getReportResult(LecturasConsumos.class, query);
	}

}
