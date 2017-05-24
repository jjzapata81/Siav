package co.com.siav.notifier.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface IMailConstructor {

	MimeMessage buildMessage(MimeMessage message) throws MessagingException;
}
