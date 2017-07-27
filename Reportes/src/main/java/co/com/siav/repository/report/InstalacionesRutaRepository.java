package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.InstalacionesRutaExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GenericoPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.InstalacionesRuta;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class InstalacionesRutaRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		return new GenericoPDF(getData(filter), Constantes.INSTALACIONES_RUTA_JRXML, getParams()).generarPDFStream();
	}

	private Map<String, Object> getParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.INSTALACIONES_RUTA_SUBTITULO);
		return params;
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<InstalacionesRuta> generator = new ExcelReportGeneratorXLSX<InstalacionesRuta>();
		return generator.generate(getData(filter), Arrays.asList(InstalacionesRutaExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.INSTALACIONES_RUTA_ASUNTO, getTextoMensaje(), getFile(filter));
	}
	
	private String getTextoMensaje() {
		return Reporte.INSTALACIONES_RUTA_CUERPO;
	}

	private Attachment getFile(Filter filter){
		return new Attachment(Reporte.INSTALACIONES_RUTA, download(filter));
	}
	
	private List<InstalacionesRuta> getData(Filter filter) {
		String query = QueryHelper.getRutas(filter);
		ReportBDFactory<InstalacionesRuta> factory = new ReportBDFactory<>();
		List<InstalacionesRuta> data = factory.getReportResult(InstalacionesRuta.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data;
	}

}
