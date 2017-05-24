package co.com.siav.notifier.config;

public class NotifierException extends RuntimeException {

	private static final long serialVersionUID = 7957741281266941101L;

	public NotifierException(String message, Exception cause) {
		super(message, cause);
	}
}
