package co.com.siav.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



@Entity
@Table(name="ta_nota_credito_auditoria")
public class NotaCreditoAuditoria implements Serializable{

	private static final long serialVersionUID = 1L;


	private static final String NOMBRE_SECUENCIA = "NotaCreditoAuditoria.id";
	
	
	@Id
	@SequenceGenerator(name = NotaCreditoAuditoria.NOMBRE_SECUENCIA, sequenceName = "sq_ta_nota_credito_auditoria", allocationSize=1)
	@GeneratedValue(generator = NotaCreditoAuditoria.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="nmciclo")
	private Long ciclo;
	
	@Column(name="nminstalacion")
	private Long instalacion;
	
	@Column(name="nmvalor")
	private Long valor;

	@Column(name="usuario")
	private String usuario;

	@Column(name="observacion")
	private String observacion;

	@Column(name="fecha")
	private Date fecha;
	
	public NotaCreditoAuditoria(){
		
	}

	public NotaCreditoAuditoria(Long ciclo, Long instalacion, Long valor, String usuario, String observacion) {
		super();
		this.ciclo = ciclo;
		this.instalacion = instalacion;
		this.valor = valor;
		this.usuario = usuario;
		this.observacion = observacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public Long getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@PrePersist
	private void onPrepersist(){
		fecha = new Date();
	}
	
}
