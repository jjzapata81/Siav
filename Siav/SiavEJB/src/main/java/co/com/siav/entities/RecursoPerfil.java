package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="ta_recurso_perfil")
public class RecursoPerfil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nmrecursoperfil")
	private Long id;
	
	@Column(name="nmperfil")
	private Long codigoPerfil;
	
	@JoinColumn(name="nmrecurso",referencedColumnName="nmrecurso", updatable=false, insertable=false)
	@OneToOne
	private Recurso recurso;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}
	
	public Recurso getRecurso() {
		return recurso;
	}
	
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

}
