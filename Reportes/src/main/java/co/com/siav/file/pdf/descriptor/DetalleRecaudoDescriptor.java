package co.com.siav.file.pdf.descriptor;

import co.com.siav.file.pdf.IPdfDescriptor;
import co.com.siav.utility.Alignment;


public enum DetalleRecaudoDescriptor implements IPdfDescriptor{
	
	FECHA_FACTURA("FECHA FACTURA","getFechaFactura", 40, DataType.DATE, Alignment.CENTER),
	BANCO("BANCO","getBanco", 40, DataType.STRING, Alignment.LEFT),
	NUMERO_CUENTA("NÚMERO CUENTA","getNumeroCuenta", 80, DataType.STRING, Alignment.CENTER),
	FECHA_HASTA("FECHA HASTA","getFeHasta", 45, DataType.DATE, Alignment.CENTER),
	INSTALACION("INSTALACIÓN","getInstalacion", 40, DataType.STRING, Alignment.CENTER),
	NOMBRES("NOMBRES","getNombres", 230, DataType.STRING, Alignment.LEFT),
	VALOR("VALOR","getValor", 50, DataType.CURRENCY, Alignment.RIGHT),
	;
	
	private String header;
	private float columnWidth;
	private String attribute;
	private DataType type;
	private Alignment alignment;
	
	private DetalleRecaudoDescriptor(String header, String attribute, float columnWidth, DataType type, Alignment alignment){
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
