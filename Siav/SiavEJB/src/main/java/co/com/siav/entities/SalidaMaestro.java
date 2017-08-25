package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ta_salida_maestro")
public class SalidaMaestro implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "SalidaMaestro.codigo";
	
	@Id
	@SequenceGenerator(name = SalidaMaestro.NOMBRE_SECUENCIA, sequenceName = "sq_ta_salida_maestro", allocationSize=1)
	@GeneratedValue(generator = SalidaMaestro.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="nmsalida")
	private Long codigo;

	private Long ciclo;
	
	@Column(name="nmdestino")
	private Long codDestino;
	
	@Column(name="nmordensalida")
	private Long codOrdenSalida;
	
	@Column(name="feordensalida")
	private Date fechaOrdenSalida;
	
	@OneToMany(mappedBy = "codigo.codSalida", fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<SalidaDetalle> detalles;
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public List<SalidaDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<SalidaDetalle> detalles) {
		this.detalles = detalles;
	}

	public Long getCodDestino() {
		return codDestino == null ? 0L : codDestino;
	}

	public void setCodDestino(Long codDestino) {
		this.codDestino = codDestino;
	}

	public Long getCodOrdenSalida() {
		return codOrdenSalida == null ? 0L : codOrdenSalida;
	}

	public void setCodOrdenSalida(Long codOrdenSalida) {
		this.codOrdenSalida = codOrdenSalida;
	}

	public Date getFechaOrdenSalida() {
		return fechaOrdenSalida;
	}

	public void setFechaOrdenSalida(Date fechaOrdenSalida) {
		this.fechaOrdenSalida = fechaOrdenSalida;
	}
	
}
