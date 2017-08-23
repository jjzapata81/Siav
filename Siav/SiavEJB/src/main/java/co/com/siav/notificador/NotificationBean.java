package co.com.siav.notificador;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.utils.Constantes;

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
	
	public void sendHtml(GeneradorHTML htmlGenerator, String subject, String receivers) {
		send(emailUser, emailPass, new HtmlMailDecorator(new MailDecorator(emailUser, userAlias, receivers, subject), htmlGenerator));
	}
	
	private void send(String emailUser, String emailPass, HtmlMailDecorator mailConstructor) {
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
			throw new ExcepcionNegocio(Constantes.ERR_ENVIO_NOTIFICACION);
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
}