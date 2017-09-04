package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ta_desactivacion")
public class Desactivacion implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "Desactivacion.codigo";

	@Id
	@SequenceGenerator(name = Desactivacion.NOMBRE_SECUENCIA, sequenceName = "sq_ta_desactivacion", allocationSize=1)
	@GeneratedValue(generator = Desactivacion.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	private Long codigo;
	
	@Column(name="nminstalacion")
	private Long instalacion;
	
	private String observacion;
	
	private String usuario;
	
	private Date fecha;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	
	@PrePersist
	private void onPrepersist(){
		fecha = new Date();
	}

}
