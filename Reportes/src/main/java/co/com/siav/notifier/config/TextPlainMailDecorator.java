package co.com.siav.notifier.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class TextPlainMailDecorator extends MailDecorator {

	private static final String CONTENT_TYPE = "text/plain";	
	private String text;

	public TextPlainMailDecorator(IMailConstructor constructor, String text) {
		super(constructor);
		this.text = text;
	}

	@Override
	public MimeMessage buildMessage(MimeMessage message) throws MessagingException {
		constructor.buildMessage(message);
		message.setContent(text, CONTENT_TYPE);
		return message;
	}

}
