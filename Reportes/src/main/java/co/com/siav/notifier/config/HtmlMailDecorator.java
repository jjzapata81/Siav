package co.com.siav.notifier.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class HtmlMailDecorator extends MailDecorator {

	private static final String CONTENT_TYPE_HTML = "text/html";
	private IHtmlGenerator htmlGenerator;

	public HtmlMailDecorator(IMailConstructor constructor, IHtmlGenerator htmlGenerator) {
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
