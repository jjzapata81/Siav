package co.com.siav.notifier.generator;

import co.com.siav.notifier.config.IHtmlGenerator;

public class GeneradorHTML implements IHtmlGenerator {

	private String contenido;
	
	public GeneradorHTML(String contenido){
		this.contenido = contenido;
	}
	
	@Override
	public String generate() {
		return contenido;
	}

}
