package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SalidaDetallePK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public SalidaDetallePK() {
	}
	
	public SalidaDetallePK(Long codSalida, Long codArticulo) {
		super();
		this.codSalida = codSalida;
		this.codArticulo = codArticulo;
	}

	@Column(name="nmsalida", nullable=false)
	private Long codSalida;
	
	@Column(name="nmarticulo", nullable=false)
	private Long codArticulo;
	
	public Long getCodSalida() {
		return codSalida;
	}
	
	public void setCodSalida(Long codSalida) {
		this.codSalida = codSalida;
	}
	
	public Long getCodArticulo() {
		return codArticulo;
	}
	
	public void setCodArticulo(Long codArticulo) {
		this.codArticulo = codArticulo;
	}


}
