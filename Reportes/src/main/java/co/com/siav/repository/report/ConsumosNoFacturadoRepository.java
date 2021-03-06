package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.ConsumoNoFacturadoExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.ConsumoNoFacturado;
import co.com.siav.repository.entities.Parametro;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class ConsumosNoFacturadoRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		return new GeneradorPDF(getData(filter), Constantes.CONSUMO_NO_FACTURADO_JRXML, getParams(filter)).getStream();
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
	
	private Map<String, Object> getParams(Filter filter){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.CONSUMO_NO_FACTURADO_SUBTITULO + filter.getCiclo());
		params.put(Constantes.RESUMEN, getTotalConsumo(filter));
		return params;
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
		List<ConsumoNoFacturado> data = factory.getReportResult(ConsumoNoFacturado.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data;
	}
	
	private Long getTotalConsumo(Filter filter) {
		String query = QueryHelper.getTotalConsumo(filter);
		ReportBDFactory<Parametro> factory = new ReportBDFactory<>();
		return Long.valueOf(factory.getReportResult(Parametro.class, query).get(0).getParametro());
	}

}
