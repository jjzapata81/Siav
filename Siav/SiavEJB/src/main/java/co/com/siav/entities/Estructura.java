package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ta_estructura")
public class Estructura implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstructuraPK id;
	
	@Column(name="fechabaja")
	private Date fechaBaja;
	
	@Column(name="acta")
	private String acta;
	
	@Column(name="snactivo")
	private String activo;

	public EstructuraPK getId() {
		return id;
	}

	public void setId(EstructuraPK id) {
		this.id = id;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getActa() {
		return acta;
	}

	public void setActa(String acta) {
		this.acta = acta;
	}

	public Boolean getActivo() {
		return "S".equals(activo);
	}

	public void setActivo(Boolean activo) {
		this.activo = activo ? "S" : "N";
	}
	
}
