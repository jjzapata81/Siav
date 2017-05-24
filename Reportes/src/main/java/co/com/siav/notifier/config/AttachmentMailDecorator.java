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


public class AttachmentMailDecorator extends MailDecorator {

	private String fileName;	
	private byte[] attachment;

	public AttachmentMailDecorator(IMailConstructor constructor, byte[] attachment, String fileName) {
		super(constructor);
		this.attachment = attachment;
		this.fileName = fileName;
	}

	@Override
	public MimeMessage buildMessage(MimeMessage message) throws MessagingException {
		constructor.buildMessage(message);
		message.setContent(buildMultipart(message));
		message.saveChanges();
		return message;
	}
	
	public Multipart buildMultipart(MimeMessage message) throws MessagingException{
		Multipart multipart = new MimeMultipart();
		
		MimeBodyPart messageContentBodyPart = new MimeBodyPart();
		try {
			messageContentBodyPart.setContent(message.getContent(), message.getContentType());
			multipart.addBodyPart(messageContentBodyPart);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();		
		DataSource source = new ByteArrayDataSource(attachment, "application/pdf");
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(fileName);
		multipart.addBodyPart(messageBodyPart);
		return multipart;
	}

}
