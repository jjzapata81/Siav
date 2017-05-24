package co.com.siav.file.pdf.descriptor;

import co.com.siav.file.pdf.IPdfDescriptor;
import co.com.siav.utility.Alignment;


public enum VariacionConsumoDescriptor implements IPdfDescriptor{
	
	INSTALACION("INSTALACIÓN","getInstalacion",50, DataType.NUMBER, Alignment.CENTER),
	NOMBRES("NOMBRES","getNombre", 300, DataType.STRING, Alignment.LEFT),
	CONSUMO_ACTUAL("CONSUMO ACTUAL","getConsumoActual",50, DataType.NUMBER, Alignment.CENTER),
	CONSUMO_ANTERIOR("CONSUMO ANTERIOR","getConsumoAnterior", 50, DataType.NUMBER, Alignment.CENTER),
	DIFERENCIA_PORCENTAJE("DIF. PORCENTAJE","getDifPorcentaje", 50, DataType.NUMBER, Alignment.CENTER),
	DIFERENCIA_METROS("DIF. METROS","getDifMetros", 50, DataType.NUMBER, Alignment.CENTER)
	;
	
	private String header;
	private float columnWidth;
	private String attribute;
	private DataType type;
	private Alignment alignment;
	
	private VariacionConsumoDescriptor(String header, String attribute, float columnWidth, DataType type, Alignment alignment){
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
