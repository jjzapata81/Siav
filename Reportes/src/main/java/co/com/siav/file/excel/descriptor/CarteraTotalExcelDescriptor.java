package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum CarteraTotalExcelDescriptor implements IExcelDescriptor{
	
	CEDULA("CÉDULA","getCedula"),
	INSTALACION("INSTALACION","getInstalacion"),
	NOMBRE("NOMBRE","getNombre"),
	FECHA("FECHA","getFecha"),
	NUMERO_FACTURA("COMPROBANTE","getComprobante"),
	TOTAL("VALOR","getValor"),
	CANCELADO("CANCELADO","getCancelado"),
	CUENTAS_VENCIDAS("CUENTAS VENCIDAS","getCuentasVencidas"),
	;
	
	private String header;
	
	private String attribute;
	
	private CarteraTotalExcelDescriptor(String header, String attribute){
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
