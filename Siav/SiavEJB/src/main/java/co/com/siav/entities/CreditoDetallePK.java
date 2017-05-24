package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CreditoDetallePK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="nmcredito", nullable=false)
	private Long codCredito;
	
	@Column(name="ciclo", nullable=false)
	private Long ciclo;
	
	public Long getCodCredito() {
		return codCredito;
	}
	
	public void setCodCredito(Long codCredito) {
		this.codCredito = codCredito;
	}
	
	public Long getCiclo() {
		return ciclo;
	}
	
	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

}
