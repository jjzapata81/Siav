package co.com.siav.exception;


public class TechnicalException extends RuntimeException {
	
	private static final long serialVersionUID = 5563617251114775027L;

	public TechnicalException(Throwable exception) {
		this(null, exception);
	}
	
	public TechnicalException(String message){
		this(message, null);
	}
	
	public TechnicalException(String message, Throwable exception){
		super(message, exception);
	}
	
	public TechnicalException(String code, String message, Throwable exception){
		super(message, exception);
	}
}
