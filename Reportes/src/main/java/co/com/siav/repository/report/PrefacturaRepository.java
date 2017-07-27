package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.PrefacturaExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.dto.DetalleInstalacionPDF;
import co.com.siav.pdf.dto.InstalacionPDF;
import co.com.siav.pdf.dto.InstalacionPDFBase;
import co.com.siav.pdf.dto.PrefacturaPDF;
import co.com.siav.pdf.generador.GeneradorPreFactura;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.PrefacturaExcel;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.utility.Util;

public class PrefacturaRepository implements IReportType{
	
	private static final String PREFACTURACION_CICLO = "PREFACTURACIÓN CICLO ";

	private Empresa empresa;
	
	@Inject
	private SendMail notifier;
		
	@Override
	public byte[] getPDF(Filter filter) {
		GeneradorPreFactura generador = new GeneradorPreFactura(getPrefactura(filter));
		return generador.generarPDFStream();
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<PrefacturaExcel> generator = new ExcelReportGeneratorXLSX<PrefacturaExcel>();
		return generator.generate(getPrefacturaExcel(filter), Arrays.asList(PrefacturaExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.PREFACTURACION_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private void getValoresGenerales() {
		empresa = Util.getEmpresa();
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.PREFACTURACION_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(Reporte.PREFACTURACION, download(filter));
	}
	
	private PrefacturaPDF getPrefactura(Filter filter){
		getValoresGenerales();
		PrefacturaPDF prefacturacion = new PrefacturaPDF();
		prefacturacion.setNombreAcueducto(empresa.getNombreCorto());
		prefacturacion.setNombreReporte(PREFACTURACION_CICLO + filter.getCiclo());
		prefacturacion.setInstalaciones(getInstalaciones(filter));
		return prefacturacion;
	}

	private List<InstalacionPDF> getInstalaciones(Filter filter) {
		String query = QueryHelper.getPrefacturaEncabezado(filter);
		ReportBDFactory<InstalacionPDFBase> factory = new ReportBDFactory<>();
		List<InstalacionPDFBase> instalaciones = factory.getReportResult(InstalacionPDFBase.class, query);
		return instalaciones.stream().map(this::transform).collect(Collectors.toList());
	}
	
	private InstalacionPDF transform(InstalacionPDFBase instalacionBase){
		InstalacionPDF instalacionPDF = new InstalacionPDF();
		instalacionPDF.setNumero(instalacionBase.getNumero());
		instalacionPDF.setNombres(instalacionBase.getNombres());
		instalacionPDF.setCedula(instalacionBase.getCedula());
		instalacionPDF.setSerialMedidor(instalacionBase.getSerialMedidor());
		instalacionPDF.setFactura(instalacionBase.getFactura());
		instalacionPDF.setCuentasVencidas(instalacionBase.getCuentasVencidas());
		instalacionPDF.setLecturaActual(instalacionBase.getLecturaActual());
		instalacionPDF.setLecturaAnterior(instalacionBase.getLecturaAnterior());
		instalacionPDF.setConsumo(instalacionBase.getConsumo());
		instalacionPDF.setDetalles(getDetalleInstalacion(instalacionBase));
		return instalacionPDF;
	}

	private List<DetalleInstalacionPDF> getDetalleInstalacion(InstalacionPDFBase instalacion) {
		String query = QueryHelper.getPrefacturaDetalle(instalacion);
		ReportBDFactory<DetalleInstalacionPDF> factory = new ReportBDFactory<>();
		return factory.getReportResult(DetalleInstalacionPDF.class, query);
	}
	
	private List<PrefacturaExcel> getPrefacturaExcel(Filter filter) {
		String query = QueryHelper.getPrefacturaExcel(filter);
		ReportBDFactory<PrefacturaExcel> factory = new ReportBDFactory<>();
		return factory.getReportResult(PrefacturaExcel.class, query);
	}

}
