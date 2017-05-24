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
@Table(name="ta_cuentas")
public class CuentaBanco implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "CuentaBanco.codigo";
	
	@Id
	@SequenceGenerator(name = CuentaBanco.NOMBRE_SECUENCIA, sequenceName = "sq_ta_cuentas", allocationSize=1)
	@GeneratedValue(generator = CuentaBanco.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="codigo")
	private Long codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="numerocuenta")
	private String numeroCuenta;

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

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	@PrePersist
	@PreUpdate
	private void onPrePersist(){
		nombre = nombre.toUpperCase();
	}
	
}
