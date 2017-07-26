package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.CarteraExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.dto.CarteraPDF;
import co.com.siav.pdf.generador.GenericoPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.Cartera;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.utility.Constantes;

public class CarteraRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
//		titulo = Util.getEmpresa().getNombreCorto();
//		ciclo = filter.getCiclo();
//		resumen = getResumen(filter);
		List<CarteraPDF> data = getData().stream().map(CarteraPDF::new).collect(Collectors.toList());
		GenericoPDF generador = new GenericoPDF(data, Constantes.CARTERA_JRXML);
		return generador.generarPDFStream();
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
		ReportFactory<Cartera> factory = new ReportFactory<>();
		return factory.getReportResult(Cartera.class, query);
	}

}
