package co.com.siav.response;

import java.util.ArrayList;
import java.util.List;

public class MenuResponse {
	
	private Long orden;
	private String grupo;
	private List<MenuItem> recursos;
	
	
	public Long getOrden() {
		return orden;
	}
	
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public List<MenuItem> getRecursos() {
		return recursos == null ? new ArrayList<MenuItem>() : recursos;
	}
	public void setRecursos(List<MenuItem> recursos) {
		this.recursos = recursos;
	}
	
	

}
