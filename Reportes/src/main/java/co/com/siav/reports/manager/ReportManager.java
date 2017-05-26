package co.com.siav.reports.manager;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import co.com.siav.exception.TechnicalException;

public class ReportManager<T> extends ResourceManager {
	
	public ReportManager() {
		super();
	}
	
	public ReportManager(String datasource) {
		super(datasource);
	}
	
	public void update(String sentence) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sentence);
			statement.executeUpdate();
		} catch (Exception ex) {
			throw new TechnicalException("Error ejecutando la consulta del reporte", ex);
		} finally {
			closeResources(statement, connection);
		}
	}
	
	public void save(String sentence) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sentence);
			statement.execute();
		} catch (Exception ex) {
			throw new TechnicalException("Error ejecutando la consulta del reporte", ex);
		} finally {
			closeResources(statement, connection);
		}
	}
	
	public List<T> getReportResult(Class<T> clazz, String sentence, Object... parameters) {
		List<T> list = null;
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sentence);
			int i = 1;
			for (Object parameter : parameters) {
				if (parameter instanceof Date) {
					statement.setDate(i, new java.sql.Date(((Date) parameter).getTime()));
				} else {
					statement.setObject(i, parameter);
				}
				i++;
			}
			resultset = statement.executeQuery();
			list = getResultList(clazz, resultset);
		} catch (Exception ex) {
			throw new TechnicalException("Error ejecutando la consulta del reporte", ex);
		} finally {
			closeResources(resultset, statement, connection);
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private List<T> getResultList(Class<T> clazz, final ResultSet resultset) {
		List<T> list = new ArrayList<>();
		List<Field> lista = new ArrayList<>();
		getFields(clazz, lista);
		
		try {
			while (resultset.next()) {
				T object = (T) Class.forName(clazz.getName()).newInstance();
				
				lista.stream().filter(field -> !Modifier.isStatic(field.getModifiers())).forEach((field) -> {
					setObjectValue(resultset, object, field);
				});
				
				list.add(object);
			}
		} catch (Exception ex) {
			throw new TechnicalException("Error recuperando los resultados de la consulta", ex);
		}
		
		return list;
	}
	
	private void setObjectValue(final ResultSet resultset, T object, Field field) {
		try {
			if (resultset.getObject(field.getName()) != null) {
				BeanUtils.setProperty(object, field.getName(), resultset.getObject(field.getName()));
			}
		} catch (Exception ex) {
			throw new TechnicalException("Error asignando los resultados al objeto", ex);
		}
	}
	
	private void getFields(Class<?> clazz, List<Field> lista) {
		if (clazz.getSuperclass() != null) {
			getFields(clazz.getSuperclass(), lista);
		}
		
		lista.addAll(Arrays.asList(clazz.getDeclaredFields()));
	}
	
}