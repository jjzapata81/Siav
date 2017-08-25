package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ta_entrada_maestro")
public class EntradaMaestro implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "EntradaMaestro.codigo";
	
	@Id
	@SequenceGenerator(name = EntradaMaestro.NOMBRE_SECUENCIA, sequenceName = "sq_ta_entrada_maestro", allocationSize=1)
	@GeneratedValue(generator = EntradaMaestro.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="nmentrada")
	private Long codigo;

	private Long ciclo;
	
	@Column(name="nmproveedor")
	private Long codProveedor;
	
	@OneToOne
	@JoinColumn(name="nmproveedor", updatable=false, insertable=false)
	private Proveedor proveedor;
	
	@OneToMany(mappedBy = "codigo.codEntrada", fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<EntradaDetalle> detalles;
	
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<EntradaDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<EntradaDetalle> detalles) {
		this.detalles = detalles;
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
