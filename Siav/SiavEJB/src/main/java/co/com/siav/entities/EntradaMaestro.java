package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ta_entrada_maestro")
public class EntradaMaestro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nmentrada")
	private Long codigo;

	private Long ciclo;
	
	@Column(name="nmproveedor")
	private Long codProveedor;
	
	@Column(name="nmfacturacompra")
	private Long codFacturaCompra;
	
	@Column(name="fefacturacompra")
	private Date fechaFacturaCompra;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCiclo() {
		return ciclo;
	}

	public void setCiclo(Long ciclo) {
		this.ciclo = ciclo;
	}

	public Long getCodProveedor() {
		return codProveedor == null ? 0L : codProveedor;
	}

	public void setCodProveedor(Long codProveedor) {
		this.codProveedor = codProveedor;
	}

	public Long getCodFacturaCompra() {
		return codFacturaCompra == null ? 0L : codFacturaCompra;
	}

	public void setCodFacturaCompra(Long codFacturaCompra) {
		this.codFacturaCompra = codFacturaCompra;
	}

	public Date getFechaFacturaCompra() {
		return fechaFacturaCompra;
	}

	public void setFechaFacturaCompra(Date fechaFacturaCompra) {
		this.fechaFacturaCompra = fechaFacturaCompra;
	}
	
}
