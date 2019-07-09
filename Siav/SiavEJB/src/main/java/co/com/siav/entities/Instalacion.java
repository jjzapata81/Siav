package co.com.siav.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="ta_instalacion")
public class Instalacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nminstalacion")
	private Long numeroInstalacion;
	
	@ManyToOne
    @JoinColumn(name="cedula", updatable=true, insertable=true)
	private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name="cdvereda", updatable=true, insertable=true)
	private Vereda vereda;
	
	@Column(name="cdramal")
	private String ramal;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="estrato")
	private String estrato;
	
	@Column(name="marcamedidor")
	private String marcaMedidor;
	
	@Column(name="seriemedidor")
	private String serieMedidor;
	
	@Column(name="feinstalacion")
	private Date fechaInstalacion;
	
	@Column(name="snmedidor")
	private String conMedidor;
	
	@Column(name="snactivo")
	private String activo;
	
	@Column(name="cvencidas")
	private Long cuentasVencidas;
	
	@Column(name="cdtipofacturacion")
	private String facturacion;
	
	@Column(name="digitosmedidor")
	private Long digitosMedidor;

	@Column(name="numero_catastro")
	private String numeroCatastro;
	
	@Column(name="nmorden")
	private Long orden;
	
	@Column(name="fedesactivacion")
	private Date fechaDesactivacion;
	
	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}

	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}
	

	public String getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(String facturacion) {
		this.facturacion = facturacion;
	}

	public Vereda getVereda() {
		return vereda;
	}

	public void setVereda(Vereda vereda) {
		this.vereda = vereda;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion.toUpperCase();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstrato() {
		return estrato;
	}

	public void setEstrato(String estrato) {
		this.estrato = estrato;
	}

	public String getMarcaMedidor() {
		return marcaMedidor;
	}

	public void setMarcaMedidor(String marcaMedidor) {
		this.marcaMedidor = marcaMedidor;
	}

	public String getSerieMedidor() {
		return serieMedidor;
	}

	public void setSerieMedidor(String serieMedidor) {
		this.serieMedidor = serieMedidor;
	}

	public Date getFechaInstalacion() {
		return fechaInstalacion;
	}

	public void setFechaInstalacion(Date fechaInstalacion) {
		this.fechaInstalacion = fechaInstalacion;
	}

	public Long getCuentasVencidas() {
		return cuentasVencidas;
	}

	public void setCuentasVencidas(Long cuentasVencidas) {
		this.cuentasVencidas = cuentasVencidas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Long getDigitosMedidor() {
		return digitosMedidor;
	}
	
	public void setDigitosMedidor(Long digitosMedidor) {
		this.digitosMedidor = digitosMedidor;
	}
	
	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getNumeroCatastro() {
		return numeroCatastro;
	}

	public void setNumeroCatastro(String numeroCatastro) {
		this.numeroCatastro = numeroCatastro;
	}
	
	public Boolean getConMedidor() {
		return "S".equals(conMedidor);
	}

	public void setConMedidor(Boolean conMedidor) {
		this.conMedidor = conMedidor ? "S" : "N";
	}
	
	public Boolean getActivo() {
		return "S".equals(activo);
	}

	public void setActivo(Boolean activo) {
		this.activo = activo ? "S" : "N";
	}

	public Long getOrden() {
		return orden;
	}
	
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	
	public Date getFechaDesactivacion() {
		return fechaDesactivacion;
	}
	
	public void setFechaDesactivacion(Date fechaDesactivacion) {
		this.fechaDesactivacion = fechaDesactivacion;
	}
	
}
