package co.com.siav.file.pdf.descriptor;

import java.util.Date;

import co.com.siav.utility.Formatter;


public enum DataType {
	
	STRING(""),
	DATE("yyyy-MM-hh"),
	CURRENCY("$ %s"),
	NUMBER(""),
	PERCENT("% %d")
	;
	
	private String symbol;
	
	private DataType(String symbol){
		this.symbol = symbol;
	}
	
	public String getFormat(Object data){
		if (data == null) {
			return "";
		}
		if (Date.class.isAssignableFrom(data.getClass())) {
			Date date = (Date) data;
			return Formatter.createStringFromDate(date, symbol);
		} else if (Number.class.isAssignableFrom(data.getClass())) {
			Number number = (Number) data;
			return symbol.contains("$") ? Formatter.getCurrency(number, symbol) :  String.valueOf(number);
		} else {
			return data.toString();
		}
	}
	
}
