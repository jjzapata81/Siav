package co.com.siav.notifier.config;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class NotificationBean {

	private static final String ENABLE_STARTTLS = "true";
	private static final String ENABLE_AUTH = "true";
	private static final String SERVER = "smtp.gmail.com";
	private static final String PORT = "587";
	
	private String emailUser;
	private String userAlias;
	private String emailPass;


	public NotificationBean(String emailUser, String userAlias, String emailPass) {
		this.emailUser = emailUser;
		this.userAlias = userAlias;
		this.emailPass = emailPass;
	}

	public void sendPlainText(String mailText, String subject, String receivers) {
		send(emailUser, emailPass, new TextPlainMailDecorator(new MailBasicDecorator(emailUser, userAlias, receivers, subject), mailText));
	}
	
	public void sendHtml(IHtmlGenerator htmlGenerator, String subject, String receivers) {
		send(emailUser, emailPass, new HtmlMailDecorator(new MailBasicDecorator(emailUser, userAlias, receivers, subject), htmlGenerator));
	}
	
	private void send(String emailUser, String emailPass, IMailConstructor mailConstructor) {
		String eUser = emailUser;
		String ePass = emailPass;

		try {
			Session session = Session.getInstance(getMailProperties(emailUser));
			MimeMessage message = mailConstructor.buildMessage(new MimeMessage(session));
			
			Transport t = session.getTransport("smtp");
			t.connect(eUser, ePass);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (Exception e) {
			throw new NotifierException("Error enviando el correo de notificacion", e);
		}
	}
	
	private Properties getMailProperties(String emailUser) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", SERVER);
		props.setProperty("mail.smtp.starttls.enable", ENABLE_STARTTLS);
		props.setProperty("mail.smtp.port", PORT);
		props.setProperty("mail.smtp.user", emailUser);
		props.setProperty("mail.smtp.auth", ENABLE_AUTH);
		return props;
	}

	public void sendTextPlainAndAttachedFile(String mailText, String subject, String receivers, byte[] attachment, String fileName) {
		MailBasicDecorator mensajeConDatosBasicosEnvio = new MailBasicDecorator(emailUser, userAlias, receivers, subject);
		TextPlainMailDecorator mensajeConDatosBasicosEnvioYTextPlano = new TextPlainMailDecorator(mensajeConDatosBasicosEnvio, mailText);
		AttachmentMailDecorator mensajeConDatosBasicosEnvioYTextPlanoYAdjunto = new AttachmentMailDecorator(mensajeConDatosBasicosEnvioYTextPlano, attachment, fileName);
		send(emailUser, emailPass, mensajeConDatosBasicosEnvioYTextPlanoYAdjunto);
	}
	
	public void sendHtmlAndAttachedMultipleFiles(IHtmlGenerator htmlGenerator, String subject, String receivers, Attachment... attachments){
		MailBasicDecorator mensajeConDatosBasicosEnvio = new MailBasicDecorator(emailUser, userAlias, receivers, subject);
		HtmlMailDecorator mensajeConDatosBasicosEnvioYHtml = new HtmlMailDecorator(mensajeConDatosBasicosEnvio, htmlGenerator);
		MultiAttachmentMailDecorator mensajeConDatosBasicosEnvioYHtmlYAdjunto = new MultiAttachmentMailDecorator(mensajeConDatosBasicosEnvioYHtml, attachments);
		send(emailUser, emailPass, mensajeConDatosBasicosEnvioYHtmlYAdjunto);
	}
	
	public void sendHtmlAndAttachedFile(IHtmlGenerator htmlGenerator, String subject, String receivers, byte[] attachment, String fileName){
		MailBasicDecorator mensajeConDatosBasicosEnvio = new MailBasicDecorator(emailUser, userAlias, receivers, subject);
		HtmlMailDecorator mensajeConDatosBasicosEnvioYHtml = new HtmlMailDecorator(mensajeConDatosBasicosEnvio, htmlGenerator);
		AttachmentMailDecorator mensajeConDatosBasicosEnvioYHtmlYAdjunto = new AttachmentMailDecorator(mensajeConDatosBasicosEnvioYHtml, attachment, fileName);
		send(emailUser, emailPass, mensajeConDatosBasicosEnvioYHtmlYAdjunto);
	}
}