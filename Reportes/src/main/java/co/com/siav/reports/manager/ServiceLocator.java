package co.com.siav.reports.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.siav.exception.TechnicalException;

public enum ServiceLocator {
	
	INSTANCE;
	
	private final transient InitialContext context;
	
	private final Map<String, Object> services = new ConcurrentHashMap<String, Object>();
	
	private ServiceLocator() {
		try {
			context = new InitialContext();
		} catch (NamingException nex) {
			throw new TechnicalException("El contexto del servidor no pudo ser inicializado", nex);
		}
	}
	
	public <S> S getCachedService(Class<S> clazz, String name) {
		Object service = services.get(name);
		
		if (service == null) {
			service = getService(clazz, name);
			services.put(name, service);
		}
		
		return clazz.cast(service);
	}
	
	public <S> S getService(Class<S> clazz, String name) {
		try {
			return clazz.cast(context.lookup(name));
		} catch (NamingException nex) {
			throw new TechnicalException("El servicio no pudo ser inicializado", nex);
		}
	}
	
}
