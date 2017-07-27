package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.CuentasVencidasExcelDescriptor;
import co.com.siav.file.pdf.PdfGenerator;
import co.com.siav.file.pdf.descriptor.CuentasVencidasDescriptor;
import co.com.siav.file.pdf.utils.CuentasVencidasEncabezado;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.CuentasVencidas;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.utility.Util;

public class CuentasVencidasRepository implements IReportType{
	
	private Empresa empresa;
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		getValoresGenerales();
		CuentasVencidasEncabezado encabezado = new CuentasVencidasEncabezado(empresa.getNombreCorto(), filter.getCiclo());
		PdfGenerator<CuentasVencidas> generator = new PdfGenerator<CuentasVencidas>();
		return generator.generate(getCuentasVencidas(filter), CuentasVencidasDescriptor.values(), encabezado);
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<CuentasVencidas> generator = new ExcelReportGeneratorXLSX<CuentasVencidas>();
		return generator.generate(getCuentasVencidas(filter), Arrays.asList(CuentasVencidasExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.CUENTAS_VENCIDAS_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	private void getValoresGenerales() {
		empresa = Util.getEmpresa();
	}
	
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.CUENTAS_VENCIDAS_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(Reporte.CUENTAS_VENCIDAS, download(filter));
	}
	
	private List<CuentasVencidas> getCuentasVencidas(Filter filter) {
		String query = QueryHelper.getCuentasVencidas(filter);
		ReportBDFactory<CuentasVencidas> factory = new ReportBDFactory<>();
		return factory.getReportResult(CuentasVencidas.class, query);
	}

}
