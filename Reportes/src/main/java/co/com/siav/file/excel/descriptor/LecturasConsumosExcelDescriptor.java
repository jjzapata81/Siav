package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum LecturasConsumosExcelDescriptor implements IExcelDescriptor{
	
	INSTALACION("INSTALACIÓN","getInstalacion"),
	NOMBRES("NOMBRES","getNombres"),
	LECTURA_ANTERIOR("LECTURA ANTERIOR","getLeAnterior"),
	LECTURA_ACTUAL("LECTURA ACTUAL","getLeActual"),
	CONSUMO_DEFINITIVO("CONSUMO DEFINITIVO","getConsumoDefinitivo"),
	CONSUMO_PROMEDIO("CONSUMO PROMEDIO","getConsumoPromedio")
	;
	
	private String header;
	
	private String attribute;
	
	private LecturasConsumosExcelDescriptor(String header, String attribute){
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
