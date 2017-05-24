package co.com.siav.repository;

public class ConsultaParametro implements IConsultaParametro{
	
	public final static String CONFIGURACIONES_SISTEMA = "ta_configuraciones_sistema";
	public final static String LLAVE = "llave";
	
	private String columna;
	private String valor;
	
	public ConsultaParametro(String columna, String valor) {
		this.columna = columna;
		this.valor = valor;
	}

	@Override
	public String getTabla() {
		return CONFIGURACIONES_SISTEMA;
	}

	@Override
	public String getColumna() {
		return columna;
	}

	@Override
	public String getCondicion() {
		return LLAVE;
	}

	@Override
	public String getValor() {
		return valor;
	}

}
