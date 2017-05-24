package co.com.siav.file.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReportGeneratorXLSX<T> extends ExcelGeneratorBase<T, XSSFWorkbook>{

	@Override
	public XSSFWorkbook getNewWorkbook() {
		return new XSSFWorkbook();
	}

}
