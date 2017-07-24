package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.EstadisticaConsumoExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.EstadisticaConsumoDescriptor;
import co.com.siav.file.pdf.utils.EstadisticaConsumoEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.EstadisticaConsumo;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.utility.Util;

public class EstadisticaConsumoRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		EstadisticaConsumoEncabezado encabezado = new EstadisticaConsumoEncabezado(Util.getEmpresa().getNombreCorto(), filter.getCiclo());
		PdfGenerator<EstadisticaConsumo> generator = new PdfGenerator<EstadisticaConsumo>();
		return generator.generate(getData(filter), EstadisticaConsumoDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<EstadisticaConsumo> generator = new ExcelReportGeneratorXLSX<EstadisticaConsumo>();
		return generator.generate(getData(filter), Arrays.asList(EstadisticaConsumoExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.ESTADISTICA_CONSUMO_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.ESTADISTICA_CONSUMO_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.ESTADISTICA_CONSUMO, filter.getCiclo()), download(filter));
	}
	
	private List<EstadisticaConsumo> getData(Filter filter) {
		String query = QueryHelper.getEstadisticaConsumo(filter);
		ReportFactory<EstadisticaConsumo> factory = new ReportFactory<>();
		return factory.getReportResult(EstadisticaConsumo.class, query).stream().sorted((a, b)-> Long.compare(a.getOrden(), b.getOrden())).collect(Collectors.toList());
	}

}
