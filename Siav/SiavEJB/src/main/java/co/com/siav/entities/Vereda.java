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
@Table(name="ta_veredas")
public class Vereda implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "Vereda.codigo";
	
	@Id
	@SequenceGenerator(name = Vereda.NOMBRE_SECUENCIA, sequenceName = "sq_ta_veredas", allocationSize=1)
	@GeneratedValue(generator = Vereda.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="cdvereda")
	private Long codigo;
	
	@Column(name="nombre")
	private String nombre;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}
	
}
