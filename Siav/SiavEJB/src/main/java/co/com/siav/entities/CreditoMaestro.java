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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="ta_credito_maestro")
public class CreditoMaestro implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String NOMBRE_SECUENCIA = "CreditoMaestro.id";
	
	@Id
	@SequenceGenerator(name = CreditoMaestro.NOMBRE_SECUENCIA, sequenceName = "sq_ta_credito_maestro", allocationSize=1)
	@GeneratedValue(generator = CreditoMaestro.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name="nmcredito")
	private Long id;
	
	@Column(name="nminstalacion")
	private Long instalacion;
	
	@Column(name="cdconcepto")
	private String codigoConcepto;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="valor")
	private Long valor;
	
	@Column(name="saldo")
	private Long saldo;
	
	@Column(name="inicial")
	private Long inicial;
	
	@Column(name="actual")
	private Long actual;
	
	@Column(name="interes")
	private Double interes;
	
	@Column(name="cuotas")
	private Long numeroCuotas;
	
	@Column(name="fechafinal")
	private Date fechaFinal;
	
	@Column(name="snfinanciado")
	private String esFinanciado;
	
	@JoinColumn(name = "cdconcepto", referencedColumnName="cdconcepto", insertable=false, updatable=false)
	@ManyToOne
	private Tarifa concepto;
	
	@OneToMany(mappedBy = "credito", fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	private List<CreditoDetalle> cuotas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Long instalacion) {
		this.instalacion = instalacion;
	}

	public String getCodigoConcepto() {
		return codigoConcepto;
	}

	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}

	public Long getValor() {
		return null == valor ? 0L : valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Long getSaldo() {
		return null == saldo ? 0L : saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public Long getInicial() {
		return inicial;
	}

	public void setInicial(Long inicial) {
		this.inicial = inicial;
	}

	public Long getActual() {
		return null == actual ? 0L : actual;
	}

	public void setActual(Long actual) {
		this.actual = actual;
	}

	public Double getInteres() {
		return null == interes ? 0D : interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public Long getNumeroCuotas() {
		return null == numeroCuotas ? 0L : numeroCuotas;
	}

	public void setNumeroCuotas(Long numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public List<CreditoDetalle> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<CreditoDetalle> cuotas) {
		this.cuotas = cuotas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Boolean getEsFinanciado() {
		return "S".equals(esFinanciado);
	}

	public void setEsFinanciado(Boolean esFinanciado) {
		this.esFinanciado = esFinanciado ? "S" : "N";
	}
	
	public Tarifa getConcepto() {
		return concepto;
	}
	
	public void setConcepto(Tarifa concepto) {
		this.concepto = concepto;
	}
		
}
