package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ta_pqr_detalle")
public class PqrDetalle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String NOMBRE_SECUENCIA = "PqrDetalle.id";
	
	@Id
	@SequenceGenerator(name = PqrDetalle.NOMBRE_SECUENCIA, sequenceName = "sq_ta_pqr_detalle", allocationSize=1)
	@GeneratedValue(generator = PqrDetalle.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="nmpqr")
	private Long idPqr;
	
	@JoinColumn(name="nmpqr",referencedColumnName="nmpqr", updatable=false, insertable=false)
	@ManyToOne
	private Pqr pqr;

	private Date fechaAccion;
	
	@OneToOne
	@JoinColumn(name="usuario",referencedColumnName="nombreusuario", updatable=false, insertable=false)
	private UsuarioSistema usuario;
	
	private String accion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdPqr() {
		return idPqr;
	}
	public void setIdPqr(Long idPqr) {
		this.idPqr = idPqr;
	}
	public Pqr getPqr() {
		return pqr;
	}
	public void setPqr(Pqr pqr) {
		this.pqr = pqr;
	}
	public Date getFechaAccion() {
		return fechaAccion;
	}
	public void setFechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}
	public UsuarioSistema getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioSistema usuario) {
		this.usuario = usuario;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
}
