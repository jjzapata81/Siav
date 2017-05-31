package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import co.com.siav.utils.Constantes;


@Entity
@Table(name="ta_rangos_facturacion")
public class Rango implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "Rango.id";
	
	@Id
	@SequenceGenerator(name = Rango.NOMBRE_SECUENCIA, sequenceName = "sq_ta_rangos_facturacion", allocationSize=1)
	@GeneratedValue(generator = Rango.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="limite_inicial")
	private long limiteInicial;
	
	@Column(name="limite_final")
	private long limiteFinal;
	
	@Column(name="resolucion")
	private String resolucion;
	
	@Column(name="estado")
	private String estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getLimiteInicial() {
		return limiteInicial;
	}

	public void setLimiteInicial(long limiteInicial) {
		this.limiteInicial = limiteInicial;
	}

	public long getLimiteFinal() {
		return limiteFinal;
	}

	public void setLimiteFinal(long limiteFinal) {
		this.limiteFinal = limiteFinal;
	}

	public String getResolucion() {
		return null == resolucion ? "" : resolucion.toUpperCase();
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@PrePersist
	private void onPrePersist(){
		estado = Constantes.DISPONIBLE;
	}
		
}
