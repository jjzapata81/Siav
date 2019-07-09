package co.com.siav.pdf.dto;

public class DetalleInstalacionPDF extends DetalleInstalacion{
	
	private Long iva;

	public Long getIva() {
		return null == iva ? 0L : iva;
	}

	public void setIva(Long iva) {
		this.iva = iva;
	}
	
}
