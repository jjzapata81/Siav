package co.com.siav.file.pdf.descriptor;

import co.com.siav.file.pdf.IPdfDescriptor;
import co.com.siav.utility.Alignment;


public enum ConsolidadoConceptoDescriptor implements IPdfDescriptor{
	
	CODIGO("CÓDIGO","getCodigo", 50, DataType.STRING, Alignment.LEFT),
	NOMBRE_CONCEPTO("CONCEPTO","getNombreConcepto", 170, DataType.STRING, Alignment.LEFT),
	VALOR("VALOR","getValor", 100, DataType.CURRENCY, Alignment.RIGHT),
	CARTERA("CARTERA","getCartera", 100, DataType.CURRENCY, Alignment.RIGHT),
	TOTAL("TOTAL","getTotal", 100, DataType.CURRENCY, Alignment.RIGHT),
	;
	
	private String header;
	private float columnWidth;
	private String attribute;
	private DataType type;
	private Alignment alignment;
	
	private ConsolidadoConceptoDescriptor(String header, String attribute, float columnWidth, DataType type, Alignment alignment){
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
