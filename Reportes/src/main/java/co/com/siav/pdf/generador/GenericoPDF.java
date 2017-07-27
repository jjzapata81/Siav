package co.com.siav.pdf.generador;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import co.com.siav.exception.TechnicalException;

public class GenericoPDF extends GeneradorPDFBase{
	
	private String jrxmlFile;
	private List<?> data;
	private Map<String, Object> params;

	public GenericoPDF(List<?> data, String jrxmlFile) {
		this.data = data;
		this.jrxmlFile = jrxmlFile;
		params = new HashMap<String, Object>();
		params.put(SUBREPORT_DIR, RUTA_ARCHIVO);
	}
	
	public GenericoPDF(List<?> data, String jrxmlFile, Map<String, Object> params) {
		this.data = data;
		this.jrxmlFile = jrxmlFile;
		this.params = params;
		this.params.put(SUBREPORT_DIR, RUTA_ARCHIVO);
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
	
	@Override
	protected Map<String, Object> getParameters() throws JRException {
		return params;
	}

	

}
