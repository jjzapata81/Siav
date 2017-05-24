package co.com.siav.file.pdf.event;

import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;

public class HeaderEvent implements IEventHandler{
	
	protected String header;
	
	private Document document;

    public void setHeader(String header, Document document) {
        this.header = header;
        this.document = document;
    }

	@Override
	public void handleEvent(Event event) {
		PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        try {
            new PdfCanvas(documentEvent.getPage())
                    .beginText()
                    .setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 12)
                    .moveText(450, 806)
                    .showText(header)
                    .endText()
                    .stroke();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
