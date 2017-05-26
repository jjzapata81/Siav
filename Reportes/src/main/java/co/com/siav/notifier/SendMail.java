package co.com.siav.notifier;

import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.config.NotificationBean;
import co.com.siav.notifier.generator.GeneradorHTML;
import co.com.siav.repository.ConsultaParametro;
import co.com.siav.repository.utility.Util;

public class SendMail {
	
	private static final String EMAIL_ENVIO_CORREO = "EMAIL_ENVIO_CORREO";
	private static final String PASS_ENVIO_CORREO = "PASS_ENVIO_CORREO";
	private static final String ALIAS_ENVIO_CORREO = "ALIAS_ENVIO_CORREO";
	
	public void send(String email, String asunto, String textoMail, Attachment file) {
		String from = Util.getParametro(new ConsultaParametro(EMAIL_ENVIO_CORREO));;
		String pass = Util.getParametro(new ConsultaParametro(PASS_ENVIO_CORREO));;
		String alias = Util.getParametro(new ConsultaParametro(ALIAS_ENVIO_CORREO));;
		NotificationBean notificator = new NotificationBean(from, alias, pass);
		notificator.sendHtmlAndAttachedMultipleFiles(new GeneradorHTML(textoMail), asunto, email, file);
	}

}
