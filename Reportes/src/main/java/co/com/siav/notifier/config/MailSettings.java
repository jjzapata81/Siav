package co.com.siav.notifier.config;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSettings {
	
	private Properties props;
	private Session session;
	private MimeMessage message;
	private Transport transport;

	public void configureProps(String adress, String alias) {
		try {
			// Propiedades de la conexion
			props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "correo");
			props.setProperty("mail.smtp.auth", "true");

			// Preparamos la sesion
			session = Session.getDefaultInstance(props);
			session.setDebug(true);
			
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(adress, alias));

			// Construimos el mensaje
			
			
			//Se agrega adjunto
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			DataSource source = new FileDataSource("C:/julian/file.txt");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("file.txt");
			multipart.addBodyPart(messageBodyPart);
			message.setText("Hola mundo, desde java con adjunto....");
			message.saveChanges();
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			DataSource source2 = new FileDataSource("C:/julian/otro.csv");
			messageBodyPart2.setDataHandler(new DataHandler(source2));
			messageBodyPart2.setFileName("otro.csv");
			multipart.addBodyPart(messageBodyPart2);
			
			message.setContent(multipart);
			message.setFileName("cualquiercos.txt");
			message.saveChanges();
			message.setContent("<b>Hola mundo</b><br><br><h3>Bieeennnnn!!!</h3>", "text/html");
			message.saveChanges();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void configureTransport(){
		try {
			transport = session.getTransport("smtp");
			transport.connect("jjzapata2004@gmail.com", "StAr&WaRs");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void setMessage(String mailTo, String subject, String textMessage){
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
			message.setSubject(subject);
			message.setText(textMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
		
}


