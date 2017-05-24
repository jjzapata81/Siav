package co.com.siav.response;

import java.util.List;

public class MenuItem {
	
	private String titulo;
	private String accion;
	private boolean tieneSubMenu;
	private Long orden;
	private List<SubMenuItem> submenus;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public boolean isTieneSubMenu() {
		return tieneSubMenu;
	}
	public void setTieneSubMenu(boolean tieneSubMenu) {
		this.tieneSubMenu = tieneSubMenu;
	}
	
	public Long getOrden() {
		return orden;
	}
	
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	
	public List<SubMenuItem> getSubmenus() {
		return submenus;
	}
	public void setSubmenus(List<SubMenuItem> submenus) {
		this.submenus = submenus;
	}
	
}
