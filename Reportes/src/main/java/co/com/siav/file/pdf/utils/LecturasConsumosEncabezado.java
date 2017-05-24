package co.com.siav.file.pdf.utils;


public class LecturasConsumosEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Lecturas y Consumos  -  Ciclo: "; 

	public LecturasConsumosEncabezado(String titulo) {
		super(titulo);
	}
	
	public LecturasConsumosEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
