package co.com.siav.file.exception;

public class ExcepcionEscrituraArchivo extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcepcionEscrituraArchivo(String message, Exception e) {
		super(message, e);
	}
}
