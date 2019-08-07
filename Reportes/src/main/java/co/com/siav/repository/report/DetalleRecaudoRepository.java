package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.DetalleRecaudoExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.DetalleRecaudo;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;
import co.com.siav.utility.Formatter;

public class DetalleRecaudoRepository implements IReportType{
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		return new GeneradorPDF(getData(filter),Constantes.RECAUDO_JRXML, getParams(filter)).getStream();
	}
	
	private Map<String, Object> getParams(Filter filter){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.DETALLE_RECAUDO_SUBTITULO + filter.getCiclo());
		params.put(Constantes.RESUMEN, getResumen(filter));
		return params;
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
		if(filter.getFechaHasta().before(filter.getFechaDesde())){
			throw new TechnicalException(Constantes.ERR_DATE);
		}
		String query = QueryHelper.getDetalleRecaudo(filter);
		ReportBDFactory<DetalleRecaudo> factory = new ReportBDFactory<>();
		List<DetalleRecaudo> data = factory.getReportResult(DetalleRecaudo.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data;
	}

}
