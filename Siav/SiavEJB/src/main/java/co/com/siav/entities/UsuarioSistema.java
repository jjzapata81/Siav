package co.com.siav.entities;

import co.com.siav.utils.Constantes;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="ta_usuario_sistema")
public class UsuarioSistema implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="nombreusuario")
	private String nombreUsuario;
	
	@Column(name="pass")
	private String password;
	
	@Column(name="snactivo")
	private String activo;
	
	@Column(name="email")
	private String email;
	
	@JoinColumn(name="nmperfil",referencedColumnName="nmperfil", updatable=true, insertable=true)
	@OneToOne
	private Perfil perfil;

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres.toUpperCase();
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public Perfil getPerfil() {
		return perfil;
	}
	
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@PrePersist
	public void prePersist(){
		activo = Constantes.SI;
		preUpdate();
	}
	
	@PreUpdate
	public void preUpdate(){
		nombres = nombres.toUpperCase();
		apellidos = apellidos.toUpperCase();
	}
	
	public String getNombreCompleto(){
		return nombres + " " + apellidos;
	}
	
}
