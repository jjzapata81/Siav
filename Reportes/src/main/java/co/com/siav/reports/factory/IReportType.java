package co.com.siav.reports.factory;

import co.com.siav.reports.filters.Filter;

public interface IReportType {
	
	 byte[] getPDF(Filter filter);
	 
	 byte[] download(Filter filter);
	 
	 void send(Filter filter);

}
