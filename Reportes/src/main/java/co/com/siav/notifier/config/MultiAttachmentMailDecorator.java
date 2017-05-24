package co.com.siav.notifier.config;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


public class MultiAttachmentMailDecorator extends MailDecorator {

	private Attachment[] attachments;

	public MultiAttachmentMailDecorator(IMailConstructor constructor, Attachment... attachments) {
		super(constructor);
		this.attachments = attachments;
	}

	@Override
	public MimeMessage buildMessage(MimeMessage message) throws MessagingException {
		constructor.buildMessage(message);
		for(Attachment att : attachments){
			message.setContent(buildMultipart(message, att));
		}
		message.saveChanges();
		return message;
	}
	
	public Multipart buildMultipart(MimeMessage message, Attachment att) throws MessagingException{
		Multipart multipart = new MimeMultipart();
		
		MimeBodyPart messageContentBodyPart = new MimeBodyPart();
		try {
			messageContentBodyPart.setContent(message.getContent(), message.getContentType());
			multipart.addBodyPart(messageContentBodyPart);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();		
		DataSource source = new ByteArrayDataSource(att.getFile(), "application/pdf");
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(att.getFilename());
		multipart.addBodyPart(messageBodyPart);
		return multipart;
	}

}
