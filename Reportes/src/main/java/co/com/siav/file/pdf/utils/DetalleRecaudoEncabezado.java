package co.com.siav.file.pdf.utils;


public class DetalleRecaudoEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Recaudo detallado por pagos  -  Ciclo: "; 

	public DetalleRecaudoEncabezado(String titulo) {
		super(titulo);
	}
	
	public DetalleRecaudoEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
