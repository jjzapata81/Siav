package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.DetalleRecaudoExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.dto.RecaudoPDF;
import co.com.siav.pdf.generador.GeneradorRecaudo;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.DetalleRecaudo;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Formatter;

public class DetalleRecaudoRepository implements IReportType{
	
	private String resumen;
	private String titulo;
	private Long ciclo;
	private static final String NOMBRE_REPORTE = "Recaudo detallado por pagos  -  Ciclo: "; 
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		titulo = Util.getEmpresa().getNombreCorto();
		ciclo = filter.getCiclo();
		resumen = getResumen(filter);
		List<DetalleRecaudo> data = getData(filter);
		GeneradorRecaudo generador = new GeneradorRecaudo(getData(data));
		return generador.generarPDFStream();
	}

	private String getResumen(Filter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("** Total recaudado para el ciclo ");
		sb.append(filter.getCiclo());
		sb.append(" entre el ");
		sb.append(Formatter.createStringFromDate(filter.getFechaDesde(), "yyyy-MM-dd"));
		sb.append(" y el ");
		sb.append(Formatter.createStringFromDate(filter.getFechaHasta(), "yyyy-MM-dd"));
		sb.append(".");
		return sb.toString();
	}

	private List<RecaudoPDF> getData(List<DetalleRecaudo> data) {
		return data.stream().map(this::transform).collect(Collectors.toList());
	}
	
	private RecaudoPDF transform(DetalleRecaudo recaudo){
		RecaudoPDF rePDF = new RecaudoPDF();
		rePDF.setTitulo(titulo);
		rePDF.setSubtitulo(NOMBRE_REPORTE + ciclo);
		rePDF.setBanco(recaudo.getBanco());
		rePDF.setNumeroCuenta(recaudo.getNumeroCuenta());
		rePDF.setFeHasta(recaudo.getFeHasta());
		rePDF.setInstalacion(recaudo.getInstalacion());
		rePDF.setNombre(recaudo.getNombres());
		rePDF.setValor(recaudo.getValor());
		rePDF.setResumen(resumen);
		return rePDF;
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
