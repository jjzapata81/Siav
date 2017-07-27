package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.CarteraExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GenericoPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.Cartera;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class CarteraRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		GenericoPDF generador = new GenericoPDF(getData(), Constantes.CARTERA_JRXML, getParams());
		return generador.generarPDFStream();
	}

	private Map<String, Object> getParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.CARTERA_SUBTITULO);
		return params;
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<Cartera> generator = new ExcelReportGeneratorXLSX<Cartera>();
		return generator.generate(getData(), Arrays.asList(CarteraExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.CARTERA_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return Reporte.CARTERA_CUERPO;
	}

	private Attachment getFile(Filter filter){
		return new Attachment(Reporte.CARTERA, download(filter));
	}
	
	private List<Cartera> getData() {
		String query = QueryHelper.getCartera();
		ReportBDFactory<Cartera> factory = new ReportBDFactory<>();
		List<Cartera> data = factory.getReportResult(Cartera.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data;
	}

}
