package co.com.siav.file.csv;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import co.com.siav.file.exception.ExcepcionEscrituraArchivo;


public abstract class CSVFileDescriptor implements ICSVDescriptor {

	private String header;
	private String attribute;
	private int order;

	protected CSVFileDescriptor(String header, String attribute, int order) {
		this.header = header;
		this.attribute = attribute;
		this.order = order;
	}

	@Override
	public String getColumnHeader() {
		return header;
	}

	@Override
	public String getColumnDataMapper() {
		return attribute;
	}

	@Override
	public int getColumnIndex() {
		return order;
	}
	
	public static List<ICSVDescriptor> values(Class<?> clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();
		List<ICSVDescriptor> descriptor = new ArrayList<ICSVDescriptor>();
		final int constantModifiers = Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL;
		for (Field f : declaredFields) {
			addDescriptor(descriptor, constantModifiers, f);
		}
		return descriptor;
	}

	private static void addDescriptor(List<ICSVDescriptor> descriptor, int constantModifiers, Field f) {
		try {
			if (f.getModifiers() == constantModifiers && f.get(null) instanceof ICSVDescriptor) {
				descriptor.add((ICSVDescriptor) f.get(null));
			}
		} catch (Exception e) {
			throw new ExcepcionEscrituraArchivo("Error de configuracion del reporte", e);
		}
	}
}
