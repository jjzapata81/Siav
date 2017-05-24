package co.com.siav.file.pdf.utils;


public class VariacionConsumoEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Variación Consumo   -  Ciclo: "; 

	public VariacionConsumoEncabezado(String titulo) {
		super(titulo);
	}
	
	public VariacionConsumoEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
