package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum ConsolidadoConceptoExcelDescriptor implements IExcelDescriptor{
	
	CODIGO("CÓDIGO","getCodigo"),
	NOMBRE_CONCEPTO("CONCEPTO","getNombreConcepto"),
	VALOR("VALOR","getValor"),
	CARTERA("CARTERA","getCartera"),
	TOTAL("TOTAL","getTotal"),
	;
	
	private String header;
	
	private String attribute;
	
	private ConsolidadoConceptoExcelDescriptor(String header, String attribute){
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
