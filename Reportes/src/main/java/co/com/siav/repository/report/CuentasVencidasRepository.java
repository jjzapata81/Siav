package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.CuentasVencidasExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.CuentasVencidas;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class CuentasVencidasRepository implements IReportType{
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		return new GeneradorPDF(getData(filter), Constantes.CUENTAS_VENCIDAS_JRXML, getParams(filter)).getStream();
	}

	private Map<String, Object> getParams(Filter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.CUENTAS_VENCIDAS_SUBTITULO + filter.getCiclo());
		return params;
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<CuentasVencidas> generator = new ExcelReportGeneratorXLSX<CuentasVencidas>();
		return generator.generate(getData(filter), Arrays.asList(CuentasVencidasExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.CUENTAS_VENCIDAS_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.CUENTAS_VENCIDAS_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(Reporte.CUENTAS_VENCIDAS, download(filter));
	}
	
	private List<CuentasVencidas> getData(Filter filter) {
		String query = QueryHelper.getCuentasVencidas(filter);
		ReportBDFactory<CuentasVencidas> factory = new ReportBDFactory<>();
		List<CuentasVencidas> data = factory.getReportResult(CuentasVencidas.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data;
	}

}
