package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum KardexExcelDescriptor implements IExcelDescriptor{
	
	CICLO("CICLO", "getCiclo"),
	FECHA("FECHA", "getFecha"),
	CODIGO("CÓDIGO", "getCodigo"),
	ARTICULO("ARTÍCULO", "getArticulo"),
	SALDO_INICIAL("SALDO INICIAL", "getSaldoInicial"),
	ENTRADA("ENTRADA", "getEntrada"),
	PRECIO_ENTRADA("PRECIO ENTRADA", "getPrecioEntrada"),
	SALIDA("SALIDA", "getSalida"),
	PRECIO_SALIDA("PRECIO SALIDA", "getPrecioSalida"),
	SALDO_FINAL("SALDO FINAL", "getSaldoFinal"),
	VALOR_SALDO("VALOR SALDO", "getValorSaldo")
	;
	
	private String header;
	
	private String attribute;
	
	private KardexExcelDescriptor(String header, String attribute){
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
