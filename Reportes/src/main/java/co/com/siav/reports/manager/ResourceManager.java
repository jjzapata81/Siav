package co.com.siav.reports.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceManager.class);
	
	private String datasource;
	
	public ResourceManager() {
		this("java:jboss/datasources/SACREPDS");
	}
	
	public ResourceManager(String datasource) {
		this.setDatasource(datasource);
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
	}
	
	public String getDatasource() {
		return datasource;
	}
	
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	
	protected Connection getConnection() throws SQLException {
		return ServiceLocator.INSTANCE.getService(DataSource.class, datasource).getConnection();
	}
	
	protected void closeResources(AutoCloseable ... resources) {
		Arrays.stream(resources).forEach((resource) -> closeResource(resource));
	}
	
	private void closeResource(AutoCloseable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (Exception ex) {
			LOGGER.error("Error cerrando recursos jdbc", ex);
		}
	}
	
}