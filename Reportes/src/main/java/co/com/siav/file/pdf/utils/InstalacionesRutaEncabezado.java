package co.com.siav.file.pdf.utils;


public class InstalacionesRutaEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Instalaciones por ruta"; 

	public InstalacionesRutaEncabezado(String titulo) {
		super(titulo);
	}
	
	public InstalacionesRutaEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
