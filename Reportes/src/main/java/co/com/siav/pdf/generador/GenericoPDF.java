package co.com.siav.pdf.generador;

import java.io.InputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import co.com.siav.exception.TechnicalException;

public class GenericoPDF extends GeneradorPDFBase{
	
	private String jrxmlFile;
	private List<?> data;

	public GenericoPDF(List<?> data, String jrxmlFile) {
		this.data = data;
		this.jrxmlFile = jrxmlFile;
	}

	@Override
	public JasperReport getReporte() throws JRException {
		try {
			InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(RUTA_ARCHIVO + jrxmlFile);
			return JasperCompileManager.compileReport(resourceAsStream);
		} catch (JRException e) {
			throw new TechnicalException("No se encontro el jasper");
		}
	}

	@Override
	public List<?> getData() throws JRException {
		return this.data;
	}

	

}
