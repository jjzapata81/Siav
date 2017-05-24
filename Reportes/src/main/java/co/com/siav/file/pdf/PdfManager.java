package co.com.siav.file.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import co.com.siav.file.pdf.utils.IEncabezado;
import co.com.siav.utility.Alignment;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class PdfManager {
	
	private PdfDocument pdf;
	
	private Document document;
	
	private PdfFont bold;
	
	private Table table;
	
	private ByteArrayOutputStream bos;
	
	public void create(){
		try {
			bos = new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(bos);
			pdf = new PdfDocument(writer);
			document = new Document(pdf, PageSize.LETTER);
			document.setMargins(30, 30, 30, 30);
			bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createTable(List<IPdfDescriptor> descriptor) {
		float[] dimen = new float[descriptor.size()];
		descriptor.stream().skip(1).forEach(item -> ArrayUtils.add(dimen, item.getColumnWidth()));
		table = new Table(dimen);
		table.setBorder(Border.NO_BORDER);
		table.setWidthPercent(95);
	}

	public void createHeader(IEncabezado content){
		document.add(new Paragraph(content.getFecha())
		.setFontSize(10)
		.setTextAlignment(TextAlignment.RIGHT));
		document.add(new Paragraph(content.getTitulo())
		.setFont(bold)
		.setFontSize(12));
		document.add(new Paragraph(content.getNombreReporte() + content.getCiclo())
		.setMarginBottom(20)
		.setFontSize(10));
	}
	
	public void createGroupTitle(String content){
		document.add(new Paragraph(content)
		.setFontSize(8));
	}
	
	public void createResume(String content){
		document.add(new Paragraph(content)
		.setFont(bold)
		.setMarginLeft(30)
		.setMarginTop(30)
		.setTextAlignment(TextAlignment.LEFT)
		.setFontSize(8));
	}
		
	public void addHeaderCell(IPdfDescriptor column) {
		Cell cell = new Cell(1, 1);
		cell.setBorder(Border.NO_BORDER);
		cell.setTextAlignment(TextAlignment.CENTER);
		cell.setFont(bold);
		cell.setPaddingLeft(10);
		cell.setFontSize(8);
		cell.setWidth(column.getColumnWidth());
		cell.add(column.getColumnHeader());
		table.addHeaderCell(cell);
	}
	
	public void createCell(Object data, IPdfDescriptor column) {
		Cell cell = new Cell(1,1);
		cell.add(column.getWithFormat(data));
		cell.setBorder(Border.NO_BORDER);
		cell.setFontSize(7);
		cell.setPaddingLeft(10);
		cell.setTextAlignment(getAlignment(column.getAlignment()));
		table.addCell(cell);
	}
	
	private TextAlignment getAlignment(Alignment alignment) {
		switch (alignment) {
		case LEFT:
			return TextAlignment.LEFT;
		case CENTER:
			return TextAlignment.CENTER;
		case RIGHT:
			return TextAlignment.RIGHT;
		}
		return TextAlignment.LEFT;
	}
	
	public void addTable(){
		document.add(table);
	}

	public void close(){
		document.close();
	}

	public byte[] toByteArray() {
		close();
		if(null == bos){
			bos = new ByteArrayOutputStream();;
		}
		return bos.toByteArray();
	}
}
