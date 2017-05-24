package co.com.siav.rest.config.error;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;


@Provider
public class AdministradorExcepciones implements ExceptionMapper<Exception> {

	private static ByteArrayOutputStream stream;
	private static PrintWriter writer;
	private static final ReentrantLock LOCK = new ReentrantLock();
	private static final AtomicInteger CONTADOR = new AtomicInteger();
	private static final Integer TOPE_ERRORES = 100;
	private static final String PATH = "co.com.siav.exception.ExcepcionNegocio: ";
	private static final Logger LOGGER = Logger.getLogger(AdministradorExcepciones.class);
	
	@Override
	public Response toResponse(Exception e) {
		guardarTraza(e);
		LOGGER.trace("I leave no trace");
		LOGGER.warn("WARN");
		LOGGER.info("INFO");
		LOGGER.error(e.getMessage(), e);
		return construirRespuestaError(e, Response.status(Status.INTERNAL_SERVER_ERROR)).build();
	}
	
	public static ResponseBuilder construirRespuestaError(Exception exception, ResponseBuilder builder) {
		String error = exception.getMessage();
		if(exception.getMessage().contains(PATH)){
			error = exception.getMessage().replace(PATH, "");
		}
		builder.header("mensaje-interno", error);
		return builder;
	}
	
	public static String getErroLogs() {
		LOCK.lock();
		try{
			if (stream != null) {
				return stream.toString();
			}
			return "";
		}finally{
			LOCK.unlock();
		}
	}

	private void guardarTraza(Exception e) {
		LOCK.lock();
		try{
			if(stream == null || CONTADOR.incrementAndGet() == TOPE_ERRORES){
				stream = new ByteArrayOutputStream();
				writer = new PrintWriter(stream);
				CONTADOR.set(0);
			}
			e.printStackTrace(writer);
			writer.flush();
		}finally{
			LOCK.unlock();
		}
		
	}

}
