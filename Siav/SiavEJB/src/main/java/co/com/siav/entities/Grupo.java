package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ta_grupos")
public class Grupo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nmgrupo")
	private Long numeroGrupo;
	
	@Column(name="nombre")
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getNumeroGrupo() {
		return numeroGrupo;
	}
	
	public void setNumeroGrupo(Long numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}
	
}
