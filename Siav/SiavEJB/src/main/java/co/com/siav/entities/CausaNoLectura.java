package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="ta_causas_no_lectura")
public class CausaNoLectura implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "CausaNoLectura.codigo";
	
	@Id
	@SequenceGenerator(name = CausaNoLectura.NOMBRE_SECUENCIA, sequenceName = "sq_ta_causas_no_lectura", allocationSize=1)
	@GeneratedValue(generator = CausaNoLectura.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="codigo")
	private Long codigo;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="snaplicaprom")
	private String aplicaPromedio;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
	}
	
	public Boolean getAplicaPromedio() {
		return "S".equals(aplicaPromedio);
	}
	
	public void setAplicaPromedio(Boolean aplicaPromedio) {
		this.aplicaPromedio = aplicaPromedio ? "S" : "N";
	}
	
}
