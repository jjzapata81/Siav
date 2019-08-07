package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.KardexExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Kardex;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class KardexRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		return new GeneradorPDF(getData(filter), Constantes.KARDEX_JRXML, getParams(filter)).getStream();
	}

	

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<Kardex> generator = new ExcelReportGeneratorXLSX<Kardex>();
		return generator.generate(getData(filter), Arrays.asList(KardexExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.KARDEX_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.KARDEX_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.KARDEX, filter.getCiclo()), download(filter));
	}
	
	private List<Kardex> getData(Filter filter) {
		String query = QueryHelper.getKardex(filter.getCiclo());
		ReportBDFactory<Kardex> factory = new ReportBDFactory<>();
		List<Kardex> data = factory.getReportResult(Kardex.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data;
	}
	
	private Map<String, Object> getParams(Filter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.KARDEX_SUBTITULO + filter.getCiclo());
		return params;
	}

}
