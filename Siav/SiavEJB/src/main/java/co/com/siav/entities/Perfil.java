package co.com.siav.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="ta_perfiles")
public class Perfil implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nmperfil")
	private Long codigoPerfil;
	
	@Column(name="dsperfil")
	private String nombrePerfil;
	
	@OneToMany(mappedBy = "codigoPerfil", fetch=FetchType.EAGER, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<RecursoPerfil> recursoPerfil;
	
	public Long getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
	
	public List<RecursoPerfil> getRecursoPerfil() {
		return recursoPerfil;
	}
	
	public void setRecursoPerfil(List<RecursoPerfil> recursoPerfil) {
		this.recursoPerfil = recursoPerfil;
	}

}
