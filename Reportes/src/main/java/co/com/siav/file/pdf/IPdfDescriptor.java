package co.com.siav.file.pdf;

import co.com.siav.utility.Alignment;


public interface IPdfDescriptor {
	
	String getColumnHeader();
	String getColumnDataMapper();
	String getWithFormat(Object data);
	float getColumnWidth();
	int getColumnIndex();
	Alignment getAlignment();
	
}
