package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.PrefacturaExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.dto.Articulo;
import co.com.siav.pdf.dto.DetalleInstalacion;
import co.com.siav.pdf.dto.DetalleInstalacionPDF;
import co.com.siav.pdf.dto.InstalacionPDF;
import co.com.siav.pdf.dto.InstalacionPDFBase;
import co.com.siav.pdf.dto.PrefacturaPDF;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.PrefacturaExcel;
import co.com.siav.repository.entities.Sistema;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;
import co.com.siav.utility.FacturaUtil;

public class PrefacturaRepository implements IReportType{

	@Inject
	private SendMail notifier;
	
	private List<Articulo> articulos;
	
	protected Sistema sistema;
		
	@Override
	public byte[] getPDF(Filter filter) {
		return new GeneradorPDF(getPrefactura(filter), Constantes.PREFACTURA_JRXML).getStream();
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
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.PREFACTURACION_CUERPO, ciclo);
	}

	private Attachment getFile(Filter filter){
		return new Attachment(Reporte.PREFACTURACION, download(filter));
	}
	
	private List<PrefacturaPDF> getPrefactura(Filter filter){
		articulos = getArticulos();
		sistema = Util.getSistema();
		PrefacturaPDF prefacturacion = new PrefacturaPDF();
		prefacturacion.setNombreAcueducto(Util.getEmpresa().getNombreCorto());
		prefacturacion.setNombreReporte(Reporte.PREFACTURACION_SUBTITULO + filter.getCiclo());
		prefacturacion.setInstalaciones(getInstalaciones(filter));
		return Arrays.asList(prefacturacion);
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
		List<DetalleInstalacionPDF> detalles = getDetalleInstalacion(instalacionBase).stream().map(this::setIva).collect(Collectors.toList());
		instalacionPDF.setDetalles(detalles);
		return instalacionPDF;
	}
	
	
	//TODO: quitar esto
	private DetalleInstalacionPDF setIva(DetalleInstalacion detalle){
		try{
			DetalleInstalacionPDF detallePDF = new DetalleInstalacionPDF();
			BeanUtils.copyProperties(detallePDF, detalle);
			if(FacturaUtil.contains(detalle.getCodigo(), articulos)){
				Double valorIva = detalle.getValor() * sistema.getIva();
				detallePDF.setIva(0L);
			}
			return detallePDF;
		}catch(Exception e){
			throw new TechnicalException(Constantes.ERR_DETALLE_PREFACTURA);
		}
	}

	private List<DetalleInstalacion> getDetalleInstalacion(InstalacionPDFBase instalacion) {
		String query = QueryHelper.getPrefacturaDetalle(instalacion);
		ReportBDFactory<DetalleInstalacion> factory = new ReportBDFactory<>();
		return factory.getReportResult(DetalleInstalacion.class, query);
	}
	
	private List<Articulo> getArticulos() {
		String query = QueryHelper.getArticulos();
		ReportBDFactory<Articulo> factory = new ReportBDFactory<>();
		return factory.getReportResult(Articulo.class, query);
	}
	
	private List<PrefacturaExcel> getPrefacturaExcel(Filter filter) {
		String query = QueryHelper.getPrefacturaExcel(filter);
		ReportBDFactory<PrefacturaExcel> factory = new ReportBDFactory<>();
		return factory.getReportResult(PrefacturaExcel.class, query);
	}

}
