package co.com.siav.utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {

	public static String createStringFromDate(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	public static String getCurrency(Number number, String symbol) {
		DecimalFormat df = new DecimalFormat("#,###,###");
		return String.format(symbol, df.format(number));
	}
	
	public static String getNombreMes(Date fecha){
		SimpleDateFormat formateador = new SimpleDateFormat("MMMMM 'de' yyyy");
		return formateador.format(fecha);
	}
	
	public static String getMes(Date fecha){
		SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
		return formateador.format(fecha);
	}

}
