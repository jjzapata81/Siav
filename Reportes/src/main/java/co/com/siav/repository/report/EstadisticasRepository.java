package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.EstadisticasExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.EstadisticasDescriptor;
import co.com.siav.file.pdf.utils.EstadisticasEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.Estadistica;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.utility.Util;

public class EstadisticasRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		EstadisticasEncabezado encabezado = new EstadisticasEncabezado(Util.getEmpresa().getNombreCorto(), filter.getCiclo());
		PdfGenerator<Estadistica> generator = new PdfGenerator<Estadistica>();
		return generator.generate(getData(filter), EstadisticasDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<Estadistica> generator = new ExcelReportGeneratorXLSX<Estadistica>();
		return generator.generate(getData(filter), Arrays.asList(EstadisticasExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.ESTADISTICA_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.ESTADISTICA_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.ESTADISTICA, filter.getCiclo()), download(filter));
	}
	
	private List<Estadistica> getData(Filter filter) {
		String query = QueryHelper.getEstadisticas(filter);
		ReportFactory<Estadistica> factory = new ReportFactory<>();
		return factory.getReportResult(Estadistica.class, query).stream().sorted((a, b)-> Long.compare(a.getOrden(), b.getOrden())).collect(Collectors.toList());
	}

}
