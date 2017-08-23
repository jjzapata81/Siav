package co.com.siav.notificador;


public class GeneradorHTML{

	private String contenido;
	
	public GeneradorHTML(String contenido){
		this.contenido = contenido;
	}
	
	public String generate() {
		return contenido;
	}

}
