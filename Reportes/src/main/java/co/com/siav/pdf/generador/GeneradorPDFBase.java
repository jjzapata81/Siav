package co.com.siav.pdf.generador;


import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import co.com.siav.exception.TechnicalException;


public abstract class GeneradorPDFBase {
	
	private static final String ERRPDF ="ERRPDF";
	private static final String ERRPDFMG ="No se puede generar el PDF";
	
	protected static final String SUBREPORT_DIR ="SUBREPORT_DIR";
	protected static final String RUTA_ARCHIVO ="facturacion/";

	public abstract JasperReport getReporte() throws JRException;
	public abstract List<?> getData() throws JRException;
	
	public byte[] generarPDFStream() {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(getReporte(), getParameters(), new JRBeanCollectionDataSource(getData()));
			return generarArrayStream(jasperPrint);
		} catch (JRException e) {
			throw new TechnicalException(ERRPDF, ERRPDFMG, e);
		}
	}
	
	public byte[] generarArrayStream(JasperPrint print) throws JRException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setEncrypted(true);
		configuration.set128BitKey(true);
		configuration.setPermissions(SimplePdfExporterConfiguration.ALL_PERMISSIONS);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
		return baos.toByteArray();
	}
	
	protected Map<String, Object> getParameters() throws JRException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SUBREPORT_DIR, RUTA_ARCHIVO);
		return params;
	}
	
}
