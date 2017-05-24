package co.com.siav.exception;

public abstract class ApplicationException extends RuntimeException{
	
	private static final long serialVersionUID = -887924735188546958L;

	private String code;
	
	public ApplicationException(Throwable exception) {
		this(null, exception);
	}
	
	public ApplicationException(String message){
		this(message, null);
	}
	
	public ApplicationException(String message, Throwable exception){
		this(null, message, exception);
	}
	
	public ApplicationException(String code, String message, Throwable exception){
		super(message, exception);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
