package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum VentasExcelDescriptor implements IExcelDescriptor{
	
	FECHA("FECHA PAGO", "getFechaPago"),
	CEDULA("CÉDULA", "getCedula"),
	NOMBRE("NOMBRE", "getNombre"),
	CONCEPTO("CONCEPTO", "getConcepto"),
	VALOR("VALOR", "getValor"),
	INICIAL("INICIAL", "getInicial"),
	;
	
	private String header;
	
	private String attribute;
	
	private VentasExcelDescriptor(String header, String attribute){
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
