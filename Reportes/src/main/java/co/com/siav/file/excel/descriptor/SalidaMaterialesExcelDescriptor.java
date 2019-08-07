package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum SalidaMaterialesExcelDescriptor implements IExcelDescriptor{
	
	ARTICULO("ARTÍCULO", "getArticulo"),
	CANTIDAD("CANTIDAD", "getCantidad"),
	PRECIO_UNITARIO("PRECIO UNITARIO", "getPrecioUnitario"),
	PRECIO_COMPRA("PRECIO COMPRA", "getPrecioCompra"),
	IVA("IVA", "getIva"),
	TOTAL("TOTAL", "getTotal")
	;
	
	private String header;
	
	private String attribute;
	
	private SalidaMaterialesExcelDescriptor(String header, String attribute){
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
