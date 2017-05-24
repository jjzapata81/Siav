package co.com.siav.entities;



public class NumeroFactura {
	
	private Long consecutivoFactura;
	private Long limiteRango;
	private Long consecutivoSiguienteRango;
	private int numerosDisponibles;
	private boolean rangoCompleto;
	
	public NumeroFactura(Long consecutivoFactura, Long limiteRango){
		this.consecutivoFactura = consecutivoFactura;
		this.limiteRango = limiteRango;
		this.numerosDisponibles = limiteRango.intValue() - consecutivoFactura.intValue() + 1;
		rangoCompleto = false;
	}
	
	public Long next(){
		consecutivoFactura++;
		if(!rangoCompleto && consecutivoFactura > limiteRango){
			consecutivoFactura = consecutivoSiguienteRango;
			rangoCompleto = true;
		}
		return consecutivoFactura;
	}

	public void setConsecutivoSiguienteRango(Long consecutivoSiguienteRango) {
		this.consecutivoSiguienteRango = consecutivoSiguienteRango;
	}

	public int getNumerosDisponibles() {
		return numerosDisponibles;
	}
}
