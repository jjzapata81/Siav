package co.com.siav.file.pdf.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EncabezadoBase {
	
	
	protected String titulo;
	protected Long ciclo;

	public EncabezadoBase(String titulo) {
		this.titulo = titulo;
	}
	
	public EncabezadoBase(String titulo, Long ciclo) {
		this.titulo = titulo;
		this.ciclo = ciclo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getFecha() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return df.format(new Date());
	}

	public String getNombreReporte() {
		return "REPORTE";
	}

	public String getCiclo() {
		return String.valueOf(ciclo);
	}
}
