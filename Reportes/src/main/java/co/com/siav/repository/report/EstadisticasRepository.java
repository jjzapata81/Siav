package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.EstadisticasExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.Estadistica;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class EstadisticasRepository implements IReportType{
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		return new GeneradorPDF(getData(filter), Constantes.ESTADISTICAS_JRXML, getParams(filter)).getStream();
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
	
	private Map<String, Object> getParams(Filter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.ESTADISTICA_SUBTITULO + filter.getCiclo());
		return params;
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.ESTADISTICA_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.ESTADISTICA, filter.getCiclo()), download(filter));
	}
	
	private List<Estadistica> getData(Filter filter) {
		String query = QueryHelper.getEstadisticas(filter);
		ReportBDFactory<Estadistica> factory = new ReportBDFactory<>();
		List<Estadistica> data = factory.getReportResult(Estadistica.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data.stream().sorted((a, b)-> Long.compare(a.getOrden(), b.getOrden())).collect(Collectors.toList());
	}

}
