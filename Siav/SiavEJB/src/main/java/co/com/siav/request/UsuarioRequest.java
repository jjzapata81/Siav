package co.com.siav.request;

public class UsuarioRequest {
	
	private static final String COMODIN = "%";
	private String nombres;
	private String apellidos;
	
	public String getNombres() {
		return nombres == null ? COMODIN+COMODIN : COMODIN + nombres.trim().toUpperCase() + COMODIN;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos == null ? COMODIN+COMODIN : COMODIN + apellidos.trim().toUpperCase() + COMODIN;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

}
