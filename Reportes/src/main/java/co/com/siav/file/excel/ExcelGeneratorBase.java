package co.com.siav.file.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import co.com.siav.exception.TechnicalException;


public abstract class ExcelGeneratorBase<T, S extends Workbook> {

	private CellStyle dateCellStyle;
	private CreationHelper createHelper;
	
	public byte[] generate(List<T> data, IExcelDescriptor[] columnDescriptor) {
		return generate(data, getDescriptor(columnDescriptor));
	}
	
	private List<IExcelDescriptor> getDescriptor(IExcelDescriptor[] columnDescriptor) {
		return Arrays.asList(columnDescriptor);
	}
	
	public byte[] generate(List<T> data, List<IExcelDescriptor> columnDescriptor) {
		try (S workbook = getNewWorkbook()){
			Sheet sheet = workbook.createSheet();
			dateCellStyle = workbook.createCellStyle();
			createHelper = workbook.getCreationHelper();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm"));
			createHeaders(sheet, columnDescriptor);
			fillData(sheet, data, columnDescriptor);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			return bos.toByteArray();
		} catch (IOException e) {
			throw new TechnicalException("Error generando el excel", e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public byte[] generate(List<IExcelDescriptor> columnDescriptor, String atributo, List<T>... data) {
		try (S workbook = getNewWorkbook()){
			dateCellStyle = workbook.createCellStyle();
			createHelper = workbook.getCreationHelper();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm"));
			for (int i = 0; i < data.length; i++){
				Field declaredField;
				String nombreHoja;
				Sheet sheet;
				try{
					declaredField = data[i].get(0).getClass().getDeclaredField(atributo);
					declaredField.setAccessible(true);
					nombreHoja = declaredField.get(data[i].get(0)).toString();
				}
				catch(NoSuchFieldException | IndexOutOfBoundsException e){
					nombreHoja = "Sheet" + i;
				}
				try{
					sheet = workbook.createSheet(nombreHoja);
				}
				catch(IllegalArgumentException e){
					sheet = workbook.createSheet(nombreHoja+i);
				}
				createHeaders(sheet, columnDescriptor);
				fillData(sheet, data[i], columnDescriptor);
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			return bos.toByteArray();
		} catch (IOException | SecurityException | IllegalAccessException e) {
			throw new TechnicalException("Error generando el excel", e);
		}
	}
	
	private void fillData(Sheet sheet, List<T> data, List<IExcelDescriptor> columnsDescriptor) {
		for (int i = 0; i < data.size(); i++) {
			Row row = sheet.createRow(i+1);
			for (IExcelDescriptor column : columnsDescriptor) {
				Cell cell = row.createCell(column.getColumnIndex());
				createCell(getData(data.get(i), column), cell);
			}
		}
		
	}
	
	private void createCell(Object data, Cell cell) {
		if (data == null) {
			cell.setCellValue("");
			return;
		}
		
		if (Date.class.isAssignableFrom(data.getClass())) {
			Date date = (Date) data;
			cell.setCellStyle(dateCellStyle);
			cell.setCellValue(date);
		} else if (Number.class.isAssignableFrom(data.getClass())) {
			Number number = (Number) data;
			cell.setCellValue(number.doubleValue());
		} else {
			cell.setCellValue(data.toString());
		}
		
	}
	
	private void createHeaders(Sheet sheet, List<IExcelDescriptor> columnsDescriptor) {
		Row fila = sheet.createRow(0);
		for (IExcelDescriptor column : columnsDescriptor) {
			Cell cell = fila.createCell(column.getColumnIndex());
			cell.setCellValue(column.getColumnHeader());
		}
	}

	private Object getData(T data, IExcelDescriptor columnDescriptor) {
		try {
			Method method = data.getClass().getDeclaredMethod(columnDescriptor.getColumnDataMapper(), new Class[0]);
			return method.invoke(data);
		} catch (Exception e) {
			throw new TechnicalException("Error de configuracion del reporte", e);
		}
	}
	
	public abstract S getNewWorkbook(); 
}
