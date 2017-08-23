package co.com.siav.notificador;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDecorator implements IMailConstructor{

	private String from;
	private String alias;
	private String recipients;
	private String subject;
	
	public MailDecorator(String from, String alias, String recipients, String subject) {
		this.from = from;
		this.alias = alias;
		this.recipients = recipients;
		this.subject = subject;
	}


	@Override
	public MimeMessage buildMessage(MimeMessage message) throws MessagingException {
		buildMessageSender(message);
		buildMessageRecipients(message);
		buildMessageSubject(message);
		return message;
	}
	
	protected void buildMessageSender(MimeMessage message) throws MessagingException {
		try {
			message.setFrom(new InternetAddress(from, alias));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	protected void buildMessageSubject(MimeMessage message) throws MessagingException {
		message.setSubject(subject);
	}
	
	protected void buildMessageRecipients(MimeMessage message) throws AddressException, MessagingException {
		String[] recipientsArray = recipients.split(",");
		for(int r = 0; r < recipientsArray.length; r++) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientsArray[r].trim()));
		}
	}
	
}
