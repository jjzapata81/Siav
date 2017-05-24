package co.com.siav.response;


public class UsuarioSistemaResponse {

	private String id;

	private String nombres;
	
	private String apellidos;
	
	private String nombreUsuario;
	
	private String password;
	
	private String activo;
	
	private PerfilResponse perfil;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActivo() {
		return "S".equals(activo);
	}

	public void setActivo(Boolean activo) {
		this.activo = activo ? "S" : "N";
	}
	
	public String getNombreCompleto(){
		return nombres + " " + apellidos;
	}
	
	public PerfilResponse getPerfil() {
		return perfil;
	}
	
	public void setPerfil(PerfilResponse perfil) {
		this.perfil = perfil;
	}
	
}
