package co.com.siav.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="ta_sistema")
public class Sistema implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="consumominimo")
	private Long consumoMinimo;
	
	@Column(name="idcargofijo")
	private String idCargoFijo;
	
	@Column(name="idbasico")
	private String idBasico;
	
	@Column(name="idcomplementario")
	private String idComplementario;
	
	@Column(name="idsuntuario")
	private String idSuntuario;
	
	@Column(name="idrecargo")
	private String idRecargo;
	
	@Column(name="idreconexion")
	private String idReconexion;
	
	@Column(name="idderecho")
	private String idDerecho;
	
	@Column(name="idinteres")
	private String idInteres;
	
	@Column(name="idsaldoafavor")
	private String idSaldoFavor;
	
	@Column(name="idmoroso")
	private String idMoroso;
	
	@Column(name="idmatricula")
	private String idMatricula;
	
	@Column(name="idmateriales")
	private String idMateriales;
	
	@Column(name="snrecargofijo")
	private String esRecargoFijo;
	
	@Column(name="precargo")
	private Double porcentajeRecargo;
	
	@Column(name="cuentasvencidas")
	private Long cuentasVencidas;
	
	@Column(name="cuentascorte")
	private Long cuentasCorte;
	
	@Column(name="snrango")
	private String esPorRango;
	
	@Column(name="snestrato")
	private String esPorEstrato;
	
	@Column(name="epsilon")
	private Long epsilon;
	
	@Column(name="tope")
	private Long tope;
	
	@Column(name="sntope")
	private String tieneTope;
	
	@Column(name="snenviofactura")
	private String envioFactura;

	@Column(name="iva")
	private Double iva;
	

	public String getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(String idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(Long consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getIdCargoFijo() {
		return idCargoFijo;
	}

	public void setIdCargoFijo(String idCargoFijo) {
		this.idCargoFijo = idCargoFijo;
	}

	public String getIdRecargo() {
		return idRecargo;
	}

	public void setIdRecargo(String idRecargo) {
		this.idRecargo = idRecargo;
	}

	public String getIdReconexion() {
		return idReconexion;
	}

	public void setIdReconexion(String idReconexion) {
		this.idReconexion = idReconexion;
	}

	public String getIdDerecho() {
		return idDerecho;
	}

	public void setIdDerecho(String idDerecho) {
		this.idDerecho = idDerecho;
	}

	public String getIdInteres() {
		return idInteres;
	}

	public void setIdInteres(String idInteres) {
		this.idInteres = idInteres;
	}
	
	public String getIdSaldoFavor() {
		return idSaldoFavor;
	}
	
	public void setIdSaldoFavor(String idSaldoFavor) {
		this.idSaldoFavor = idSaldoFavor;
	}

	public String getIdMoroso() {
		return idMoroso;
	}

	public void setIdMoroso(String idMoroso) {
		this.idMoroso = idMoroso;
	}
	
	public String getIdMateriales() {
		return idMateriales;
	}
	
	public void setIdMateriales(String idMateriales) {
		this.idMateriales = idMateriales;
	}

	public Boolean getEsRecargoFijo() {
		return "S".equals(esRecargoFijo);
	}
	
	public void setEsRecargoFijo(Boolean esRecargoFijo) {
		this.esRecargoFijo = esRecargoFijo ? "S" : "N";
	}

	public Double getPorcentajeRecargo() {
		return porcentajeRecargo;
	}

	public void setPorcentajeRecargo(Double porcentajeRecargo) {
		this.porcentajeRecargo = porcentajeRecargo;
	}

	public Long getCuentasVencidas() {
		return cuentasVencidas;
	}

	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}
	
	public Long getCuentasCorte() {
		return cuentasCorte != null ? cuentasCorte : 0L;
	}
	
	public void setCuentasCorte(Long cuentasCorte) {
		this.cuentasCorte = cuentasCorte;
	}
	
	public Long getEpsilon() {
		return epsilon;
	}
	
	public void setEpsilon(Long epsilon) {
		this.epsilon = epsilon;
	}
	
	public boolean getEsPorRango(){
		return "S".equals(esPorRango);
	}
	
	public void setEsPorRango(boolean esPorRango) {
		this.esPorRango = esPorRango ? "S" : "N";
	}
	
	public boolean getTieneTope(){
		return "S".equals(tieneTope);
	}
	
	public void setTieneTope(boolean tieneTope) {
		this.tieneTope = tieneTope ? "S" : "N";
	}
	
	public boolean getEsPorEstrato() {
		return "S".equals(esPorEstrato);
	}
	
	public void setEsPorEstrato(boolean esPorEstrato) {
		this.esPorEstrato = esPorEstrato ? "S" : "N";
	}
	
	public Boolean getEnvioFactura() {
		return "S".equals(envioFactura);
	}
	
	public void setEnvioFactura(Boolean envioFactura) {
		this.envioFactura = envioFactura ? "S" : "N";
	}

	public String getIdBasico() {
		return idBasico;
	}

	public void setIdBasico(String idBasico) {
		this.idBasico = idBasico;
	}

	public String getIdComplementario() {
		return idComplementario;
	}

	public void setIdComplementario(String idComplementario) {
		this.idComplementario = idComplementario;
	}

	public String getIdSuntuario() {
		return idSuntuario;
	}

	public void setIdSuntuario(String idSuntuario) {
		this.idSuntuario = idSuntuario;
	}
	
	public Long getTope() {
		return tope;
	}
	
	public void setTope(Long tope) {
		this.tope = tope;
	}
	
	public Double getIva() {
		return null == iva ? 0.0 : iva;
	}
	
	public void setIva(Double iva) {
		this.iva = iva;
	}
	
}
