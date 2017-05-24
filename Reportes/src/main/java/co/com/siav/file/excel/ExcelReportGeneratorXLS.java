package co.com.siav.file.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReportGeneratorXLS<T> extends ExcelGeneratorBase<T, HSSFWorkbook>{

	@Override
	public HSSFWorkbook getNewWorkbook() {
		return  new HSSFWorkbook();
	}

}
