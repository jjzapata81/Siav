package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum DetalleRecaudoExcelDescriptor implements IExcelDescriptor{
	
	FECHA_FACTURA("FECHA FACTURA","getFechaFactura"),
	BANCO("BANCO","getBanco"),
	NUMERO_CUENTA("NÚMERO CUENTA","getNumeroCuenta"),
	FECHA_HASTA("FECHA HASTA","getFeHasta"),
	INSTALACION("INSTALACIÓN","getInstalacion"),
	NOMBRES("NOMBRES","getNombres"),
	VALOR("VALOR","getValor"),
	;
	
	private String header;
	
	private String attribute;
	
	private DetalleRecaudoExcelDescriptor(String header, String attribute){
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
