package co.com.siav.notificador;


public abstract class Decorator implements IMailConstructor{

	protected IMailConstructor constructor;
	
	public Decorator(IMailConstructor constructor) {
		this.constructor = constructor;
	}

}
