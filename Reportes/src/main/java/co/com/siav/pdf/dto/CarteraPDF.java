package co.com.siav.pdf.dto;

import co.com.siav.reports.response.Cartera;

public class CarteraPDF extends Cartera{
	
	private String titulo;
	private String subtitulo;
	
	public CarteraPDF(){
		super();
	}
	public CarteraPDF(Cartera cartera){
		setInstalacion(cartera.getInstalacion());
		setNombre(cartera.getNombre());
		setCodigo(cartera.getCodigo());
		setConcepto(cartera.getConcepto());
		setSaldo(cartera.getSaldo());
		setTitulo("Cartera detallada créditos");
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
}
