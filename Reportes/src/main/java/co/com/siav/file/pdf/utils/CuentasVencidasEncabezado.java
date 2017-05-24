package co.com.siav.file.pdf.utils;


public class CuentasVencidasEncabezado extends EncabezadoBase implements IEncabezado{
	
	private static final String NOMBRE_REPORTE = "Cuentas Vencidas   -  Ciclo: "; 

	public CuentasVencidasEncabezado(String titulo) {
		super(titulo);
	}
	
	public CuentasVencidasEncabezado(String titulo, Long ciclo) {
		super(titulo, ciclo);
	}

	@Override
	public String getNombreReporte() {
		return NOMBRE_REPORTE;
	}

}
