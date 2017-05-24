package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ta_roles")
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idrol")
	private int id;
	
	@Column(name="dsrol")
	private String nombreRol;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombreRol() {
		return nombreRol;
	}
	
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
}
