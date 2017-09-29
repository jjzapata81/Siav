package co.com.siav.repository.entities;

public class UsuarioMail {
	
	private String cedula;
	
	private String email;
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return cedula;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UsuarioMail){
			UsuarioMail compare = (UsuarioMail) obj;
			return cedula.equals(compare.getCedula());
		}
		
		return cedula.equals(obj.toString());
	}

}
