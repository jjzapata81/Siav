package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum PrefacturaExcelDescriptor implements IExcelDescriptor{
	
	FACTURA_NUMERO("FACTURA NÚMERO","getNumerofactura"),
	INSTALACION("INSTALACIÓN","getInstalacion"),
	NOMBRE("NOMBRE","getNombre"),
	CEDULA("CÉDULA","getCedula"),
	CONSUMO("CONSUMO","getConsumo"),
	CUENTAS_VENCIDAS("CUENTAS VENCIDAS","getCuentasVencidas"),
	CODIGO_CONCEPTO("CÓDIGO CONCEPTO","getCodigoConcepto"),
	NOMBRE_CONCEPTO("NOMBRE CONCEPTO","getNombreConcepto"),
	METROS("METROS","getMetros"),
	VALOR("VALOR","getValor"),
	SALDO("SALDO","getSaldo"),
	DESCRIPCION("DESCRIPCION","getDescripcion"),
	CODIGO_CREDITO("CÓDIGO CRÉDITO","getCodigoCredito")
	;
	
	private String header;
	
	private String attribute;
	
	private PrefacturaExcelDescriptor(String header, String attribute){
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
