package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EntradaDetallePK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public EntradaDetallePK() {
	}
	
	public EntradaDetallePK(Long codEntrada, Long codArticulo) {
		super();
		this.codEntrada = codEntrada;
		this.codArticulo = codArticulo;
	}

	@Column(name="nmentrada", nullable=false)
	private Long codEntrada;
	
	@Column(name="nmarticulo", nullable=false)
	private Long codArticulo;
	
	public Long getCodEntrada() {
		return codEntrada;
	}
	
	public void setCodEntrada(Long codEntrada) {
		this.codEntrada = codEntrada;
	}
	
	public Long getCodArticulo() {
		return codArticulo;
	}
	
	public void setCodArticulo(Long codArticulo) {
		this.codArticulo = codArticulo;
	}


}
