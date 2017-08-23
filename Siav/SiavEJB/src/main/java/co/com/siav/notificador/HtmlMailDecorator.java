package co.com.siav.notificador;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class HtmlMailDecorator extends Decorator{

	private static final String CONTENT_TYPE_HTML = "text/html";
	private GeneradorHTML htmlGenerator;

	public HtmlMailDecorator(IMailConstructor constructor, GeneradorHTML htmlGenerator) {
		super(constructor);
		this.htmlGenerator = htmlGenerator;
	}

	@Override
	public MimeMessage buildMessage(MimeMessage message) throws MessagingException {
		constructor.buildMessage(message);
		message.setContent(htmlGenerator.generate(), CONTENT_TYPE_HTML);
		message.saveChanges();
		return message;
	}

}
