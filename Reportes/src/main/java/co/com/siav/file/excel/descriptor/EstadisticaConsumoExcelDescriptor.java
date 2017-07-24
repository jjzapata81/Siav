package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum EstadisticaConsumoExcelDescriptor implements IExcelDescriptor{
	
	CONCEPTO("CONCEPTO","getVereda"),
	INSTALACIONES("INSTALACIONES","getInstalaciones"),
	CONSUMO_TOTAL("CONSUMO TOTAL (metros despachados)","getConsumoTotal"),
	PORCENTAJE("PORCENTAJE","getPorcentaje"),
	CONSUMO_PROMEDIO("CONSUMO PROMEDIO","getConsumoPromedio")
	;
	
	private String header;
	
	private String attribute;
	
	private EstadisticaConsumoExcelDescriptor(String header, String attribute){
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
