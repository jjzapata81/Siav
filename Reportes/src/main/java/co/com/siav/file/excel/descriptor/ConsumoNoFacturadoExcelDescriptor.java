package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum ConsumoNoFacturadoExcelDescriptor implements IExcelDescriptor{
	
	INSTALACION("INSTALACIÓN","getInstalacion"),
	NOMBRE("NOMBRE","getNombre"),
	LECTURA_ANTERIOR("LECTURA ANTERIOR","getLeAnterior"),
	LECTURA_ACTUAL("LECTURA ACTUAL","getLeActual"),
	CONSUMO_DEFINITIVO("CONSUMO DEFINITIVO","getConsumoDefinitivo"),
	CONSUMO_PROMEDIO("CONSUMO PROMEDIO","getConsumoPromedio"),
	PAGA("PAGA","getPaga")
	;
	
	private String header;
	
	private String attribute;
	
	private ConsumoNoFacturadoExcelDescriptor(String header, String attribute){
		this.header = header;
		this.attribute = attribute;
	}

	@Override
	public String getColumnHeader() {
		return header;
	}

	@Override
	public String getColumnDataMapper() {
		return attribute;
	}

	@Override
	public int getColumnIndex() {
		return ordinal();
	}

}
