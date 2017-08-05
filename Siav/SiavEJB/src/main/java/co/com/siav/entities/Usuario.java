package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ta_usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cedula")
	private String cedula;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="telefono")
	private String telefono;

	@Column(name="direccion")
	private String direccion;
	
	@Column(name="celular")
	private String celular;
	
	@Column(name="email")
	private String email;
	
	@Column(name="ciudad")
	private String ciudad;
	
	@Column(name="snenvioemail")
	private String envioMail;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return null == nombres ? "" : nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres.toUpperCase();
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos.toUpperCase();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion == null ? direccion : direccion.toUpperCase();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getNombreCompleto(){
		return null == nombres ? apellidos : nombres + " " + apellidos;
	}
	
	public Boolean getEnvioMail() {
		return "S".equals(envioMail);
	}
	
	public void setEnvioMail(Boolean envioMail) {
		this.envioMail = envioMail ? "S" : "N";
	}
		
}
