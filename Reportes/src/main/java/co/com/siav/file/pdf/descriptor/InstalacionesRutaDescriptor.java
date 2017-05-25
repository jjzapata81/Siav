package co.com.siav.file.pdf.descriptor;

import co.com.siav.file.pdf.IPdfDescriptor;
import co.com.siav.utility.Alignment;


public enum InstalacionesRutaDescriptor implements IPdfDescriptor{
	
	RUTA("RUTA","getRuta",30, DataType.STRING, Alignment.CENTER),
	INSTALACION("INSTALACION","getInstalacion", 30, DataType.NUMBER, Alignment.CENTER),
	NOMBRE("NOMBRE","getNombre", 250, DataType.STRING, Alignment.LEFT),
	TIENE_MEDIDOR("TIENE MEDIDOR","getTieneMedidor",10, DataType.STRING, Alignment.CENTER),
	SERIE_MEDIDOR("SERIE MEDIDOR","getSerieMedidor", 40, DataType.STRING, Alignment.CENTER),
	FACTURA("FACTURA","getFactura", 10, DataType.STRING, Alignment.CENTER),
	VEREDA("VEREDA","getVereda", 100, DataType.STRING, Alignment.LEFT),
	DIRECCION("DIRECCIÓN","getDireccion", 80, DataType.STRING, Alignment.LEFT),
	TELEFONO("TELÉFONO","getTelefono", 40, DataType.STRING, Alignment.LEFT)
	;
	
	private String header;
	private float columnWidth;
	private String attribute;
	private DataType type;
	private Alignment alignment;
	
	private InstalacionesRutaDescriptor(String header, String attribute, float columnWidth, DataType type, Alignment alignment){
		this.header = header;
		this.attribute = attribute;
		this.columnWidth = columnWidth;
		this.type = type;
		this.alignment = alignment;
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

	@Override
	public float getColumnWidth() {
		return columnWidth;
	}

	@Override
	public String getWithFormat(Object data) {
		return type.getFormat(data);
	}

	@Override
	public Alignment getAlignment() {
		return alignment;
	}

}
