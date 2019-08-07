package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.SalidaMaterialesExcelDescriptor;
import co.com.siav.file.excel.dto.SalidaArticuloExcel;
import co.com.siav.file.excel.dto.SalidaMaterialesExcel;

public class SalidaMaterialesRepository {
	
	public byte[] download(List<SalidaArticuloExcel> excel) {
		ExcelReportGeneratorXLSX<SalidaMaterialesExcel> generator = new ExcelReportGeneratorXLSX<SalidaMaterialesExcel>();
		return generator.generate(getData(excel), Arrays.asList(SalidaMaterialesExcelDescriptor.values()));
	}

	private List<SalidaMaterialesExcel> getData(List<SalidaArticuloExcel> excel) {
		return excel.stream().map(this::transform).collect(Collectors.toList());
	}
	
	private SalidaMaterialesExcel transform(SalidaArticuloExcel item){
		SalidaMaterialesExcel excel = new SalidaMaterialesExcel();
		excel.setArticulo(item.getArticulo().getNombre());
		excel.setCantidad(item.getCantidad());
		excel.setIva(item.getValorIva());
		excel.setPrecioCompra(item.getPrecio());
		excel.setPrecioUnitario(item.getPrecioUnitario());
		excel.setTotal(item.getValorConIva());
		return excel;
	}

}
