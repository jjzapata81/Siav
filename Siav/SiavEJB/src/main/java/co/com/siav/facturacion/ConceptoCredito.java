package co.com.siav.facturacion;

public class ConceptoCredito extends Concepto{
	
	private Long idCredito;
	
	public ConceptoCredito() {
	}
	
	public ConceptoCredito(String codigo, String nombre, String detalle, Long metros, Long valor, Long vencido, Long idCredito) {
		super(codigo, nombre, detalle, metros, valor, vencido);
		this.idCredito = idCredito;
	}
	
	public Long getIdCredito() {
		return idCredito;
	}
	
	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}
	
}
