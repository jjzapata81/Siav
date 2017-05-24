package co.com.siav.file.pdf.descriptor;

import co.com.siav.file.pdf.IPdfDescriptor;
import co.com.siav.utility.Alignment;


public enum CuentasVencidasDescriptor implements IPdfDescriptor{
	
	FACTURA("FACTURA", "getFactura", 50, DataType.NUMBER, Alignment.CENTER),
	NOMBRES("NOMBRES", "getNombres", 300, DataType.STRING, Alignment.LEFT),
	VALOR("VALOR", "getValor", 70, DataType.CURRENCY, Alignment.RIGHT),
	CUENTAS_VENCIDAS("VENCIDAS", "getCuentasvencidas", 70, DataType.NUMBER, Alignment.CENTER)
	;
	
	private String header;
	private float columnWidth;
	private String attribute;
	private DataType type;
	private Alignment alignment;
	
	private CuentasVencidasDescriptor(String header, String attribute, float columnWidth, DataType type, Alignment alignment){
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
