package co.com.siav.repository;

import java.util.List;

import co.com.siav.reports.manager.ReportManager;

public class ReportFactory<T> {
	
	private String DATA_SOURCE = "java:jboss/jdbc/SiavWEBDS";
	
	private ReportManager<T> getManager(){
		return new ReportManager<>(DATA_SOURCE);
	}

	public List<T> getReportResult(Class<T> clazz, String query) {
		return getManager().getReportResult(clazz, query);
	}

}
