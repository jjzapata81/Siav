package co.com.siav.enums;

public enum Estrato {
	CERO("0"),
	UNO("1"),
	DOS("2"),
	TRES("3"),
	CUATRO("4"),
	CINCO("5"),
	SEIS("6");
	
	private String valor;
	
	Estrato(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
}
