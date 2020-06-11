package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.CarteraTotalExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.CarteraTotal;
import co.com.siav.repository.entities.Ciclo;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class CarteraTotalRepository implements IReportType{
	
	
	@Inject
	private SendMail notifier;
	
	@Override
	public byte[] getPDF(Filter filter) {
		validar(filter);
		return new GeneradorPDF(getData(filter), Constantes.CARTERA_FINAL_JRXML, getParams()).getStream();
	}

	private Map<String, Object> getParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constantes.TITULO, Util.getEmpresa().getNombreCorto());
		params.put(Constantes.SUBTITULO, Reporte.CARTERA_SUBTITULO);
		return params;
	}

	@Override
	public byte[] download(Filter filter) {
		validar(filter);
		ExcelReportGeneratorXLSX<CarteraTotal> generator = new ExcelReportGeneratorXLSX<CarteraTotal>();
		return generator.generate(getData(filter), Arrays.asList(CarteraTotalExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		validar(filter);
		notifier.send(filter.getEmail(),Reporte.CARTERA_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return Reporte.CARTERA_CUERPO;
	}

	private Attachment getFile(Filter filter){
		return new Attachment(Reporte.CARTERA, download(filter));
	}
	
	private List<CarteraTotal> getData(Filter filter) {
		Date fechaDesde = filter.getFechaDesde();
		Calendar c = Calendar.getInstance();
		c.setTime(fechaDesde);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date fechaBusqueda = c.getTime();
		filter.setFechaDesde(fechaBusqueda);
		Ciclo cicloPorFecha = Util.getCicloPorFecha(filter);
		String query = QueryHelper.getCarteraTotal(cicloPorFecha.getCiclo());
		ReportBDFactory<CarteraTotal> factory = new ReportBDFactory<>();
		List<CarteraTotal> data = factory.getReportResult(CarteraTotal.class, query);
		if(data.isEmpty()){
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		return data;
	}
	
	private void validar(Filter filter) {
		Calendar c = Calendar.getInstance();
		c.setTime(filter.getFechaDesde());
		int diaDesde = c.get(Calendar.DAY_OF_MONTH);
		int mesDesde = c.get(Calendar.MONTH);
		c.setTime(filter.getFechaHasta());
		int diaHasta = c.get(Calendar.DAY_OF_MONTH);
		int mesHasta = c.get(Calendar.MONTH);
		if (mesDesde!=mesHasta){
			throw new TechnicalException(Constantes.ERR_MONTH_DATE);
		}
		if (diaHasta<diaDesde){
			throw new TechnicalException(Constantes.ERR_FINAL_DATE);
		}
	}

}
