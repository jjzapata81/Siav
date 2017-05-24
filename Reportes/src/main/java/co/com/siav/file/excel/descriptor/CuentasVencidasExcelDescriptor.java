package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum CuentasVencidasExcelDescriptor implements IExcelDescriptor{
	
	FACTURA("FACTURA", "getFactura"),
	NOMBRES("NOMBRES", "getNombres"),
	VALOR("VALOR", "getValor"),
	CUENTAS_VENCIDAS("CUENTAS VENCIDAS", "getCuentasvencidas")
	;
	
	private String header;
	
	private String attribute;
	
	private CuentasVencidasExcelDescriptor(String header, String attribute){
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
