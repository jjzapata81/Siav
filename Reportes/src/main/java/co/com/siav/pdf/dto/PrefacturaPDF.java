package co.com.siav.pdf.dto;

import java.util.List;

public class PrefacturaPDF {
	
	private String nombreAcueducto;
	private String nombreReporte;
	private List<InstalacionPDF> instalaciones;

	public String getNombreAcueducto() {
		return nombreAcueducto;
	}

	public void setNombreAcueducto(String nombreAcueducto) {
		this.nombreAcueducto = nombreAcueducto;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public List<InstalacionPDF> getInstalaciones() {
		return instalaciones;
	}

	public void setInstalaciones(List<InstalacionPDF> instalaciones) {
		this.instalaciones = instalaciones;
	}

}
