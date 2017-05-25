package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.InstalacionesRutaExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.InstalacionesRutaDescriptor;
import co.com.siav.file.pdf.utils.InstalacionesRutaEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.InstalacionesRuta;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.utility.Util;

public class InstalacionesRutaRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		InstalacionesRutaEncabezado encabezado = new InstalacionesRutaEncabezado(Util.getEmpresa().getNombreCorto());
		PdfGenerator<InstalacionesRuta> generator = new PdfGenerator<InstalacionesRuta>();
		return generator.generate(getRutas(filter), InstalacionesRutaDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<InstalacionesRuta> generator = new ExcelReportGeneratorXLSX<InstalacionesRuta>();
		return generator.generate(getRutas(filter), Arrays.asList(InstalacionesRutaExcelDescriptor.values()));
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
	
	private List<InstalacionesRuta> getRutas(Filter filter) {
		String query = QueryHelper.getRutas(filter);
		ReportFactory<InstalacionesRuta> factory = new ReportFactory<>();
		return factory.getReportResult(InstalacionesRuta.class, query);
	}

}
