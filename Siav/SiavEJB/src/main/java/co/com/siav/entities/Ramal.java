package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ta_ramal")
public class Ramal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cdramal")
	private String codigoRamal;
	
	@Column(name="nombre")
	private String nombre;
	
	public String getCodigoRamal() {
		return codigoRamal;
	}
	
	public void setCodigoRamal(String codigoRamal) {
		this.codigoRamal = codigoRamal;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
