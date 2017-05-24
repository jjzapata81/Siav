package co.com.siav.file.pdf;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import co.com.siav.file.exception.ExcepcionEscrituraArchivo;
import co.com.siav.file.pdf.utils.IEncabezado;


public class PdfGenerator<T> {
	
	private PdfManager manager;
	
	public byte[] generate(List<T> data, IPdfDescriptor[] columnDescriptor, IEncabezado encabezado) {
		List<IPdfDescriptor> descriptor = getDescriptor(columnDescriptor);
		manager = new PdfManager();
		manager.create();
		manager.createTable(descriptor);
		manager.createHeader(encabezado);
		return generate(data, descriptor);
	}
	
	private List<IPdfDescriptor> getDescriptor(IPdfDescriptor[] columnDescriptor) {
		return Arrays.asList(columnDescriptor);
	}
	
	private byte[] generate(List<T> data, List<IPdfDescriptor> columnDescriptor) {
		createHeaders(columnDescriptor);
		fillData(data, columnDescriptor);
		manager.addTable();
		manager.createResume("Total registros: " + data.size());
		return manager.toByteArray();
	}
	
	private void createHeaders(List<IPdfDescriptor> columnsDescriptor) {
		for (IPdfDescriptor column : columnsDescriptor) {
			manager.addHeaderCell(column);
		}
	}
	
	private void fillData(List<T> data, List<IPdfDescriptor> columnsDescriptor) {
		for (int i = 0; i < data.size(); i++) {
			for (IPdfDescriptor column : columnsDescriptor) {
				manager.createCell(getData(data.get(i), column), column);
			}
		}
	}
	
	private Object getData(T data, IPdfDescriptor columnDescriptor) {
		try {
			Method method = data.getClass().getDeclaredMethod(columnDescriptor.getColumnDataMapper(), new Class[0]);
			return method.invoke(data);
		} catch (Exception e) {
			throw new ExcepcionEscrituraArchivo("Error de configuracion del reporte", e);
		}
	}

}
