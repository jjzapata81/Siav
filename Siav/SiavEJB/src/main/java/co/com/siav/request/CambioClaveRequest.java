package co.com.siav.request;

public class CambioClaveRequest {
	
	private String usuario;
	private String password;
	private String nuevoPassword;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNuevoPassword() {
		return nuevoPassword;
	}
	public void setNuevoPassword(String nuevoPassword) {
		this.nuevoPassword = nuevoPassword;
	}
	
}
