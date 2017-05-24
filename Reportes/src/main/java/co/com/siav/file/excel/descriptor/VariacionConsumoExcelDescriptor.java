package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum VariacionConsumoExcelDescriptor implements IExcelDescriptor{
	
	INSTALACION("INSTALACIÓN","getInstalacion"),
	NOMBRES("NOMBRES","getNombre"),
	CONSUMO_ACTUAL("CONSUMO ACTUAL","getConsumoActual"),
	CONSUMO_ANTERIOR("CONSUMO ANTERIOR","getConsumoAnterior"),
	DIFERENCIA_PORCENTAJE("DIF. PORCENTAJE","getDifPorcentaje"),
	DIFERENCIA_METROS("DIF. METROS","getDifMetros")
	;
	
	private String header;
	
	private String attribute;
	
	private VariacionConsumoExcelDescriptor(String header, String attribute){
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
