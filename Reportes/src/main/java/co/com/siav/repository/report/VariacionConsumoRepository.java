package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.VariacionConsumoExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.VariacionConsumoDescriptor;
import co.com.siav.file.pdf.utils.VariacionConsumoEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.entities.VariacionConsumoBD;
import co.com.siav.repository.utility.Util;

public class VariacionConsumoRepository implements IReportType{
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		Empresa empresa = getValoresGenerales();
		VariacionConsumoEncabezado encabezado = new VariacionConsumoEncabezado(empresa.getNombreCorto(), filter.getCiclo());
		PdfGenerator<VariacionConsumoBD> generator = new PdfGenerator<VariacionConsumoBD>();
		return generator.generate(getVariacionBD(filter), VariacionConsumoDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<VariacionConsumoBD> generator = new ExcelReportGeneratorXLSX<VariacionConsumoBD>();
		return generator.generate(getVariacionBD(filter), Arrays.asList(VariacionConsumoExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.VARIACION_CONSUMO_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private List<VariacionConsumoBD> getVariacionBD(Filter filter) {
		filter.setValorDesde((-1L)*filter.getValorHasta());
		String query = QueryHelper.getVariacionConsumo(filter);
		ReportFactory<VariacionConsumoBD> factory = new ReportFactory<>();
		return factory.getReportResult(VariacionConsumoBD.class, query);
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.VARIACION_CONSUMO_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.VARIACION_CONSUMO, filter.getCiclo()), download(filter));
	}
	
	private Empresa getValoresGenerales() {
		return Util.getEmpresa();
	}

}
