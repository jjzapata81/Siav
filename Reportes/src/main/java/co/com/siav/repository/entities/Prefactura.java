package co.com.siav.repository.entities;

import java.util.List;

public class Prefactura {
	
	private static final String  TOTAL = "Total: $";
	private static final String ESPACIO = "    ";
	private PrefacturaTitulo tituloGrupo;
	private List<PrefacturaDetalle> grupo;
	private String resumenGrupo;
	
	public String getTituloGrupo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Instalacion:");
		sb.append(ESPACIO);
		sb.append(tituloGrupo.getInstalacion());
		sb.append(ESPACIO);
		sb.append(tituloGrupo.getNombres());
		sb.append(ESPACIO);
		sb.append(tituloGrupo.getCedula());
		sb.append(ESPACIO);
		sb.append("Factura:");
		sb.append(ESPACIO);
		sb.append(tituloGrupo.getFactura());
		sb.append(ESPACIO);
		sb.append("L. anterior:");
		sb.append(ESPACIO);
		sb.append(tituloGrupo.getLecturaAnterior());
		sb.append(ESPACIO);
		sb.append("L. actual:");
		sb.append(ESPACIO);
		sb.append(tituloGrupo.getLecturaActual());
		sb.append(ESPACIO);
		sb.append("Consumo:");
		sb.append(ESPACIO);
		sb.append(tituloGrupo.getConsumo());
		return sb.toString();
	}
	public void setTituloGrupo(PrefacturaTitulo tituloGrupo) {
		this.tituloGrupo = tituloGrupo;
	}
	public List<PrefacturaDetalle> getGrupo() {
		return grupo;
	}
	public void setGrupo(List<PrefacturaDetalle> grupo) {
		this.grupo = grupo;
	}
	public String getResumenGrupo() {
		return resumenGrupo;
	}
	public void setResumenGrupo() {
		resumenGrupo = TOTAL + grupo.stream().mapToLong(PrefacturaDetalle :: getValor).sum();
	}
	
}
