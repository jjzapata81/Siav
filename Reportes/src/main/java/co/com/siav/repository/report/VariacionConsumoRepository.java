package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.VariacionConsumoExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.VariacionConsumo;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class VariacionConsumoRepository implements IReportType{
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		return new GeneradorPDF(getData(filter), Constantes.VARIACION_CONSUMO_JRXML, getParams(filter)).getStream();
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<VariacionConsumo> generator = new ExcelReportGeneratorXLSX<VariacionConsumo>();
		return generator.generate(getData(filter), Arrays.asList(VariacionConsumoExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.VARIACION_CONSUMO_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private Map<String, Object> getParams(Filter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.VARIACION_CONSUMO_SUBTITULO + filter.getCiclo());
		return params;
	}
	
	private List<VariacionConsumo> getData(Filter filter) {
		filter.setValorDesde((-1L)*filter.getValorHasta());
		String query = QueryHelper.getVariacionConsumo(filter);
		ReportBDFactory<VariacionConsumo> factory = new ReportBDFactory<>();
		List<VariacionConsumo> data = factory.getReportResult(VariacionConsumo.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data;
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.VARIACION_CONSUMO_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.VARIACION_CONSUMO, filter.getCiclo()), download(filter));
	}

}
