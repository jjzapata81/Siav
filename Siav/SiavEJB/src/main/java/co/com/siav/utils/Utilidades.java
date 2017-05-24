package co.com.siav.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public final class Utilidades {
	
	private Utilidades(){}

	public static String dateToString(Date fecha) {
		if (null !=fecha){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
			return df.format(fecha);
		}
		return "";
	}

	public static Date stringToDate(String fecha) {
		if(null != fecha){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return df.parse(fecha);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;

	}
	
	public static String formatoMoneda(Long valor){
		NumberFormat format = NumberFormat.getCurrencyInstance();
		return format.format(valor);
		
	}

	public static Long extract(Long lecturaActual, Long lecturaAnterior) {
		Long anterior = null == lecturaAnterior ? 0L : lecturaAnterior;
		Long actual = null == lecturaActual ? lecturaAnterior : lecturaActual;
		return actual - anterior;
	}

	public static Date fechaMasDias(Date fecha, int dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.add(Calendar.DAY_OF_MONTH, dias);
		return c.getTime();
	}
}

