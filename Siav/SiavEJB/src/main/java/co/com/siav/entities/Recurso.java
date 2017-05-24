package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="ta_recursos")
public class Recurso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nmrecurso")
	private Long numeroRecurso;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="accion")
	private String accion;
	
	@Column(name="snsubmenu")
	private String tieneSubmenu;
	
	@Column(name="nmrelacion")
	private Long relacion;
	
	@JoinColumn(name="nmgrupo",referencedColumnName="nmgrupo", updatable=false, insertable=false)
	@OneToOne
	private Grupo grupo;

	public Long getNumeroRecurso() {
		return numeroRecurso;
	}

	public void setNumeroRecurso(Long numeroRecurso) {
		this.numeroRecurso = numeroRecurso;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Boolean getTieneSubmenu() {
		return "S".equals(tieneSubmenu);
	}

	public void setTieneSubmenu(Boolean tieneSubmenu) {
		this.tieneSubmenu = tieneSubmenu ? "S" : "N";
	}

	public Long getRelacion() {
		return relacion;
	}

	public void setRelacion(Long relacion) {
		this.relacion = relacion;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}
