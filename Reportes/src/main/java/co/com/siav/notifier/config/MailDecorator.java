package co.com.siav.notifier.config;


public abstract class MailDecorator implements IMailConstructor {

	protected IMailConstructor constructor;
	
	public MailDecorator(IMailConstructor constructor) {
		this.constructor = constructor;
	}
	
}
