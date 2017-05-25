package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum InstalacionesRutaExcelDescriptor implements IExcelDescriptor{
	
	RUTA("RUTA","getRuta"),
	INSTALACION("INSTALACION","getInstalacion"),
	NOMBRE("NOMBRE","getNombre"),
	TIENE_MEDIDOR("TIENE MEDIDOR","getTieneMedidor"),
	SERIE_MEDIDOR("SERIE MEDIDOR","getSerieMedidor"),
	FACTURA("FACTURA","getFactura"),
	VEREDA("VEREDA","getVereda"),
	DIRECCION("DIRECCIÓN","getDireccion"),
	TELEFONO("TELÉFONO","getTelefono")
	;
	
	private String header;
	
	private String attribute;
	
	private InstalacionesRutaExcelDescriptor(String header, String attribute){
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
