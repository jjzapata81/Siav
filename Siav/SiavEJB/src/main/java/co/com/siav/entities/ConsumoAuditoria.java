package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="ta_consumo_auditoria")
public class ConsumoAuditoria implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "ConsumoAuditoria.id";
	
	@Id
	@SequenceGenerator(name = ConsumoAuditoria.NOMBRE_SECUENCIA, sequenceName = "sq_ta_consumo_auditoria", allocationSize=1)
	@GeneratedValue(generator = ConsumoAuditoria.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="lectura")
	private Long lectura;
	
	@Column(name="lectura_corregida")
	private Long lecturaCorregida;
	
	@Column(name="consumo")
	private Long consumo;
	
	@Column(name="consumo_corregido")
	private Long consumoCorregido;
	
	@Column(name="ciclo")
	private Long ciclo;
	
	@Column(name="nminstalacion")
	private Long instalacion;
	
	@Column(name="observacion")
	private String observacion;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="fecha")
	private Date fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLectura() {
		return lectura;
	}

	public void setLectura(Long lectura) {
		this.lectura = lectura;
	}

	public Long getLecturaCorregida() {
		return lecturaCorregida;
	}

	public void setLecturaCorregida(Long lecturaCorregida) {
		this.lecturaCorregida = lecturaCorregida;
	}

	public Long getConsumo() {
		return consumo;
	}

	public void setConsumo(Long consumo) {
		this.consumo = consumo;
	}

	public Long getConsumoCorregido() {
		return consumoCorregido;
	}

	public void setConsumoCorregido(Long consumoCorregido) {
		this.consumoCorregido = consumoCorregido;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
