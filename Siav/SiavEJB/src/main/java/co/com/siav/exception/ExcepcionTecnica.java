package co.com.siav.exception;

public class ExcepcionTecnica extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionTecnica(String msg, Exception e) {
		super(msg, e);
	}
	
	public ExcepcionTecnica(String msg) {
		super(msg);
	}
}
