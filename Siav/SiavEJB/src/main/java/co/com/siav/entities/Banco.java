package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="ta_bancos")
public class Banco implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "Banco.codigo";
	
	@Id
	@SequenceGenerator(name = Banco.NOMBRE_SECUENCIA, sequenceName = "sq_ta_bancos", allocationSize=1)
	@GeneratedValue(generator = Banco.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="nmbanco")
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
		this.nombre = nombre;
	}
	
	@PrePersist
	@PreUpdate
	private void onPrePersist(){
		nombre = nombre.toUpperCase();
	}
	
}
