package co.com.siav.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ta_salida_maestro")
public class SalidaMaestro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nmsalida")
	private Long codigo;

	@Column(name="ciclo")
	private Long ciclo;
	
	@Column(name="nmdestino")
	private Long codDestino;
	
	@Column(name="feordensalida")
	private Date fechaOrdenSalida;
	
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

	public Long getCodDestino() {
		return codDestino == null ? 0L : codDestino;
	}

	public void setCodDestino(Long codDestino) {
		this.codDestino = codDestino;
	}

	public Date getFechaOrdenSalida() {
		return fechaOrdenSalida;
	}

	public void setFechaOrdenSalida(Date fechaOrdenSalida) {
		this.fechaOrdenSalida = fechaOrdenSalida;
	}
	
}
