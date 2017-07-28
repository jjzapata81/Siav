package co.com.siav.file.csv;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.siav.exception.TechnicalException;


public class CsvReportGenerator<T> {

	private static final String COLUMN_SEPARATOR = ";";
	private static final Object LINE_SEPARTOR = "\n";
	
	private List<? extends ICSVDescriptor> descriptors;

	public CsvReportGenerator(ICSVDescriptor[] columnDescriptor) {
		this(Arrays.asList(columnDescriptor));
	}
	
	public CsvReportGenerator(List<? extends ICSVDescriptor> columnDescriptor) {
		this.descriptors = columnDescriptor;
	}

	public byte[] generate(List<T> data) {
		StringBuffer report = new StringBuffer();
		try {
			createHeaders(report);
			fillData(report, data);
			return report.toString().getBytes(Charset.forName("UTF-8"));
		} finally {
			report = null;
		}
	}

	private void fillData(StringBuffer report, List<T> data) {
		for (int i = 0; i < data.size(); i++) {
			for (ICSVDescriptor column : descriptors) {
				putValue(report, getData(data.get(i), column));
			}
			report.append(LINE_SEPARTOR);
		}
	}

	private void putValue(StringBuffer report, Object data) {
		if (data == null) {
			report.append(COLUMN_SEPARATOR);
			return;
		}
		
		if (Date.class.isAssignableFrom(data.getClass())) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			report.append(formatter.format(data));
		} else if (Number.class.isAssignableFrom(data.getClass())) {
			DecimalFormat formatter = new DecimalFormat("####.##");
			Number number = (Number) data;
			report.append(formatter.format(number));
		} else {
			report.append(sanitizeString(data.toString()));
		}
		report.append(COLUMN_SEPARATOR);
	}

	private void createHeaders(StringBuffer report) {
		for (ICSVDescriptor column : descriptors) {
			putValue(report, column.getColumnHeader());
		}
		report.append(LINE_SEPARTOR);
	}

	private Object getData(T data, ICSVDescriptor columnDescriptor) {
		try {
			Method method = data.getClass().getDeclaredMethod(columnDescriptor.getColumnDataMapper(), new Class[0]);
			return method.invoke(data);
		} catch (Exception e) {
			throw new TechnicalException("Error de configuracion del archivo", e);
		}
	}

	private String sanitizeString(String str) {
		return StringUtils.stripAccents(str);
	}
}
