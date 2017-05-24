package co.com.siav.pdf.generador;


import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import co.com.siav.exception.TechnicalException;
import co.com.siav.pdf.dto.FacturacionPDF;


public class GeneradorFactura extends GeneradorPDFBase{
	
	private static final String ARCHIVO_JRXML="siav.jrxml";
	
	protected FacturacionPDF data;
	
	public GeneradorFactura(FacturacionPDF data){
		this.data = data;
	}
	
	public JasperReport getReporte(){		
		try {
			InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(RUTA_ARCHIVO + ARCHIVO_JRXML);
			return JasperCompileManager.compileReport(resourceAsStream);
		} catch (JRException e) {
			throw new TechnicalException("No se encontro el jasper");
		}
	}

	@Override
	public List<?> getData() throws JRException {
		return Arrays.asList(this.data);
	}
	
}
