package co.com.siav.file.pdf.descriptor;

import co.com.siav.file.pdf.IPdfDescriptor;
import co.com.siav.utility.Alignment;


public enum ConsumoNoFacturadoDescriptor implements IPdfDescriptor{
	
	INSTALACION("INSTALACIÓN","getInstalacion",50, DataType.NUMBER, Alignment.CENTER),
	NOMBRE("NOMBRE","getNombre", 300, DataType.STRING, Alignment.LEFT),
	LECTURA_ANTERIOR("LECTURA ANTERIOR","getLeAnterior",50, DataType.NUMBER, Alignment.CENTER),
	LECTURA_ACTUAL("LECTURA ACTUAL","getLeActual", 50, DataType.NUMBER, Alignment.CENTER),
	CONSUMO_DEFINITIVO("CONSUMO DEFINITIVO","getConsumoDefinitivo", 50, DataType.NUMBER, Alignment.CENTER),
	CONSUMO_PROMEDIO("CONSUMO PROMEDIO","getConsumoPromedio", 50, DataType.NUMBER, Alignment.CENTER),
	PAGA("PAGA","getPaga", 20, DataType.STRING, Alignment.CENTER)
	;
	
	private String header;
	private float columnWidth;
	private String attribute;
	private DataType type;
	private Alignment alignment;
	
	private ConsumoNoFacturadoDescriptor(String header, String attribute, float columnWidth, DataType type, Alignment alignment){
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
