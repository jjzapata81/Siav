package co.com.siav.file.pdf.utils;


public class EstadisticaConsumoEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Estadísticas de consumo  -  Ciclo: "; 

	public EstadisticaConsumoEncabezado(String titulo) {
		super(titulo);
	}
	
	public EstadisticaConsumoEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
