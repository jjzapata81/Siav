package co.com.siav.file.pdf.utils;


public class PrefacturaEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Prefactura   -  Ciclo: "; 

	public PrefacturaEncabezado(String titulo) {
		super(titulo);
	}
	
	public PrefacturaEncabezado(String titulo, Long ciclo) {
		super(titulo,ciclo);
	}
	
	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
