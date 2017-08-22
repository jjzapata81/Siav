package co.com.siav.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="ta_pqr_maestro")
public class Pqr implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "Pqr.id";
	
	@Id
	@SequenceGenerator(name = Pqr.NOMBRE_SECUENCIA, sequenceName = "sq_ta_pqr_maestro", allocationSize=1)
	@GeneratedValue(generator = Pqr.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="nmpqr")
	private Long id;
	
	@Column(name="nminstalacion")
	private Long numeroInstalacion;
	
	@OneToOne
	@JoinColumn(name="nminstalacion", updatable=false, insertable=false)
	private Instalacion instalacion;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private Long estado;
	
	private String descripcion;
	
	@Column(name="usuario")
	private String nombreUsuario;
	
	@OneToOne
	@JoinColumn(name="usuario",referencedColumnName="nombreusuario", updatable=false, insertable=false)
	private UsuarioSistema usuario;
	
	@OneToMany(mappedBy = "idPqr", fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<PqrDetalle> detalles;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}
	
	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
	
	public Instalacion getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public UsuarioSistema getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioSistema usuario) {
		this.usuario = usuario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public List<PqrDetalle> getDetalles() {
		if(null == detalles){
			detalles = new ArrayList<PqrDetalle>();
		}
		return detalles;
	}
	
	public void setDetalles(List<PqrDetalle> detalles) {
		this.detalles = detalles;
	}
	
	@PrePersist
	private void onPrepersist(){
		fechaInicio = new Date();
		estado = 1L;
	}
	
}
