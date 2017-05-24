package co.com.siav.response;

import java.util.ArrayList;
import java.util.List;



public class LoginResponse extends MensajeResponse{

	private List<MenuResponse> menu;
	
	public LoginResponse() {
	}
	
	public LoginResponse(String mensaje) {
		super(mensaje);
	}
	
	public LoginResponse(EstadoEnum estado, String mensaje){
		super(estado, mensaje);
	}
	
	public List<MenuResponse> getMenu() {
		return menu == null ? new ArrayList<MenuResponse>() : menu;
	}
	
	public void setMenu(List<MenuResponse> menu) {
		this.menu = menu;
	}

}
