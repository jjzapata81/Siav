package co.com.siav.file.pdf.utils;


public class ConsumoNoFacturadoEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Consumos no facturados  -  Ciclo: "; 

	public ConsumoNoFacturadoEncabezado(String titulo) {
		super(titulo);
	}
	
	public ConsumoNoFacturadoEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
