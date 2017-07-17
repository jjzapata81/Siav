package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ta_novedad")
public class Novedad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private NovedadPK id;
	
	@Column(name="valor")
	private Long valor;
	
	@Column(name="snborrable")
	private String borrable;
	
	public NovedadPK getId() {
		return id;
	}

	public void setId(NovedadPK id) {
		this.id = id;
	}

	public Long getValor() {
		return valor == null ? 0L : valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Boolean getBorrable() {
		return "S".equals(borrable);
	}

	public void setBorrable(Boolean borrable) {
		this.borrable = borrable ? "S" : "N";
	}
	
}
