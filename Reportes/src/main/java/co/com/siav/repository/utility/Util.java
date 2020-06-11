package co.com.siav.repository.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.com.siav.exception.TechnicalException;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.IConsultaParametro;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Ciclo;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.entities.Parametro;
import co.com.siav.repository.entities.Sistema;
import co.com.siav.utility.Constantes;

public class Util {
	
	private Util(){
		
	}

	public static Empresa getEmpresa() {
		String query = QueryHelper.getEmpresa();
		ReportBDFactory<Empresa> factory = new ReportBDFactory<>();
		List<Empresa> reportResult = factory.getReportResult(Empresa.class, query);
		if(!reportResult.isEmpty()){
			return reportResult.get(0);
		}else{
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
	}

	public static Ciclo getCiclo(Filter filter) {
		String query = QueryHelper.getCiclo(filter);
		ReportBDFactory<Ciclo> factory = new ReportBDFactory<>();
		List<Ciclo> result = factory.getReportResult(Ciclo.class, query);
		if(null == result || result.isEmpty()){
			throw new TechnicalException(Constantes.CICLO_NO_EXISTE);
		}
		return result.get(0);
	}
	
	public static Ciclo getCicloPorEstado(String estado) {
		String query = QueryHelper.getCicloPorEstado(estado);
		ReportBDFactory<Ciclo> factory = new ReportBDFactory<>();
		List<Ciclo> reportResult = factory.getReportResult(Ciclo.class, query);
		if(!reportResult.isEmpty()){
			return reportResult.get(0);
		}else{
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		
	}
	
	public static Ciclo getCicloPorFecha(Filter filter) {
		String query = QueryHelper.getCicloPorFecha(filter);
		ReportBDFactory<Ciclo> factory = new ReportBDFactory<>();
		List<Ciclo> reportResult = factory.getReportResult(Ciclo.class, query);
		if(!reportResult.isEmpty()){
			return reportResult.get(0);
		}else{
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
		
	}

	public static Sistema getSistema() {
		String query = QueryHelper.getSistema();
		ReportBDFactory<Sistema> factory = new ReportBDFactory<>();
		List<Sistema> reportResult = factory.getReportResult(Sistema.class, query);
		if(!reportResult.isEmpty()){
			return reportResult.get(0);
		}else{
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
	}
	
	public static String getParametro(IConsultaParametro parametro) {
		String query = QueryHelper.getParametro(parametro);
		ReportBDFactory<Parametro> factory = new ReportBDFactory<>();
		List<Parametro> reportResult = factory.getReportResult(Parametro.class, query);
		if(!reportResult.isEmpty()){
			return reportResult.get(0).getParametro();
		}else{
			throw new TechnicalException(Constantes.ERR_NO_DATA);
		}
	}
	
	public static String getCodigoBarras(String referencia, Long valorTotal, Date fecha) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		StringBuilder sb = new StringBuilder();
		sb.append(complet(referencia, 13));
		sb.append(complet(String.valueOf(valorTotal < 0 ? 0L : valorTotal), 8));
		sb.append(df.format(fecha));
		return sb.toString();
	}

	private static String complet(String value, int size) {
		StringBuilder sb = new StringBuilder();
		int rest = size - value.length();
		for(int i=0; i <rest; i++){
			sb.append("0");
		}
		sb.append(value);
		return sb.toString();
	}

}
