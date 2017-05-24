package co.com.siav.file.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class PDFHelper {
	
	private Document document;
	
	public void create(String rutaArchivo){
		try{
			
			OutputStream out = new FileOutputStream(rutaArchivo);
			PdfWriter writer = new PdfWriter(out);
			PdfDocument pdf = new PdfDocument(writer);
			document = new Document(pdf);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void close(){
		document.close();
	}

	public void setTitle(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setSection() {
		// TODO Auto-generated method stub
		
	}

	public void setFooter(String string) {
		// TODO Auto-generated method stub
		
	}

}
