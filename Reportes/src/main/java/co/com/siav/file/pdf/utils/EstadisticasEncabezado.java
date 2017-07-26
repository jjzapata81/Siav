package co.com.siav.file.pdf.utils;


public class EstadisticasEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Estadísticas  -  Ciclo: "; 

	public EstadisticasEncabezado(String titulo) {
		super(titulo);
	}
	
	public EstadisticasEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
