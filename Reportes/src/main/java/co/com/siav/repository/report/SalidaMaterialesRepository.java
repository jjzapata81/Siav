package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.SalidaMaterialesExcelDescriptor;
import co.com.siav.file.excel.dto.SalidaArticuloExcel;
import co.com.siav.file.excel.dto.SalidaMaterialesExcel;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class SalidaMaterialesRepository {
	
	public byte[] getExcel(List<SalidaArticuloExcel> excel) {
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
	
	private Map<String, Object> getParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.NIT, Util.getEmpresa().getNit());
		params.put(Constantes.SUBTITULO, Reporte.INGRESO_COMPRA);
		return params;
	}
	
	public byte[] download(List<SalidaArticuloExcel> excel) {
		return new GeneradorPDF(getData(excel), Constantes.COMPROBANTE_MATERIALES, getParams()).getStream();
	}

}
