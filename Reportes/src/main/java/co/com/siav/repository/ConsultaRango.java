package co.com.siav.repository;

public class ConsultaRango implements IConsultaParametro{
	
	public final static String RANGOS_FACTURACION = "ta_rangos_facturacion";
	public final static String ESTADO = "estado";
	
	private String columna;
	private String valor;
	
	public ConsultaRango(String columna, String valor) {
		this.columna = columna;
		this.valor = valor;
	}

	@Override
	public String getTabla() {
		return RANGOS_FACTURACION;
	}

	@Override
	public String getColumna() {
		return columna;
	}

	@Override
	public String getCondicion() {
		return ESTADO;
	}

	@Override
	public String getValor() {
		return valor;
	}

}
