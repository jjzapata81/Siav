package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum CarteraExcelDescriptor implements IExcelDescriptor{
	
	CONCEPTO("CONCEPTO","getConcepto"),
	INSTALACION("INSTALACION","getInstalacion"),
	NOMBRE("NOMBRE","getNombre"),
	SALDO("SALDO","getSaldo"),
	CODIGO("CÓDIGO","getCodigo")
	;
	
	private String header;
	
	private String attribute;
	
	private CarteraExcelDescriptor(String header, String attribute){
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
