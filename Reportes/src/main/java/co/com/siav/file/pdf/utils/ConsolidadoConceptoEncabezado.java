package co.com.siav.file.pdf.utils;


public class ConsolidadoConceptoEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Facturación consolidada por conceptos  -  Ciclo: "; 

	public ConsolidadoConceptoEncabezado(String titulo) {
		super(titulo);
	}
	
	public ConsolidadoConceptoEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
