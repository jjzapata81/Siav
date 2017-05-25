package co.com.siav.repository.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.com.siav.exception.TechnicalException;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.IConsultaParametro;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
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
		ReportFactory<Empresa> factory = new ReportFactory<>();
		return factory.getReportResult(Empresa.class, query).get(0);
	}

	public static Ciclo getCiclo(Filter filter) {
		String query = QueryHelper.getCiclo(filter);
		ReportFactory<Ciclo> factory = new ReportFactory<>();
		List<Ciclo> result = factory.getReportResult(Ciclo.class, query);
		if(null == result || result.isEmpty()){
			throw new TechnicalException(Constantes.CICLO_NO_EXISTE);
		}
		return result.get(0);
	}
	
	public static Ciclo getCicloPorEstado(String estado) {
		String query = QueryHelper.getCicloPorEstado(estado);
		ReportFactory<Ciclo> factory = new ReportFactory<>();
		return factory.getReportResult(Ciclo.class, query).get(0);
	}

	public static Sistema getSistema() {
		String query = QueryHelper.getSistema();
		ReportFactory<Sistema> factory = new ReportFactory<>();
		return factory.getReportResult(Sistema.class, query).get(0);
	}
	
	public static String getParametro(IConsultaParametro parametro) {
		String query = QueryHelper.getParametro(parametro);
		ReportFactory<Parametro> factory = new ReportFactory<>();
		return factory.getReportResult(Parametro.class, query).get(0).getParametro();
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
