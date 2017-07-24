package co.com.siav.file.pdf.descriptor;

import co.com.siav.file.pdf.IPdfDescriptor;
import co.com.siav.utility.Alignment;


public enum EstadisticaConsumoDescriptor implements IPdfDescriptor{
	
	CONCEPTO("CONCEPTO","getVereda", 180, DataType.STRING, Alignment.LEFT),
	INSTALACIONES("INSTALACIONES","getInstalaciones", 50, DataType.NUMBER, Alignment.RIGHT),
	CONSUMO_TOTAL("CONSUMO TOTAL","getConsumoTotal", 110, DataType.NUMBER, Alignment.RIGHT),
	PORCENTAJE("PORCENTAJE","getPorcentaje", 50, DataType.PERCENT, Alignment.RIGHT),
	CONSUMO_PROMEDIO("CONSUMO PROMEDIO","getConsumoPromedio", 110, DataType.NUMBER, Alignment.RIGHT),
	;
	
	private String header;
	private float columnWidth;
	private String attribute;
	private DataType type;
	private Alignment alignment;
	
	private EstadisticaConsumoDescriptor(String header, String attribute, float columnWidth, DataType type, Alignment alignment){
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
