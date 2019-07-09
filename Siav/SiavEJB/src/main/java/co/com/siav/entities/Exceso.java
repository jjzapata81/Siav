package co.com.siav.entities;

import co.com.siav.utils.Constantes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="ta_excesos")
public class Exceso implements Serializable, IEstrato{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="liminicial")
	private Long limInicial;
	
	@Column(name="limfinal")
	private Long limFinal;

	@Column(name="estrato0")
	private Long estrato0;
	
	@Column(name="estrato1")
	private Long estrato1;
	
	@Column(name="estrato2")
	private Long estrato2;
	
	@Column(name="estrato3")
	private Long estrato3;
	
	@Column(name="estrato4")
	private Long estrato4;
	
	@Column(name="estrato5")
	private Long estrato5;
	
	@Column(name="estrato6")
	private Long estrato6;
	
	@Column(name="fecreacion")
	private Date fechaCreacion;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="nmorden")
	private Long orden;
	
	@Column(name="snventas")
	private String esVenta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getLimInicial() {
		return limInicial;
	}

	public void setLimInicial(Long limInicial) {
		this.limInicial = limInicial;
	}

	public Long getLimFinal() {
		return limFinal;
	}

	public void setLimFinal(Long limFinal) {
		this.limFinal = limFinal;
	}

	@Override
	public Long getEstrato0() {
		return estrato0;
	}

	public void setEstrato0(Long estrato0) {
		this.estrato0 = estrato0;
	}
	
	@Override
	public Long getEstrato1() {
		return estrato1;
	}

	public void setEstrato1(Long estrato1) {
		this.estrato1 = estrato1;
	}
	
	@Override
	public Long getEstrato2() {
		return estrato2;
	}

	public void setEstrato2(Long estrato2) {
		this.estrato2 = estrato2;
	}
	
	@Override
	public Long getEstrato3() {
		return estrato3;
	}

	public void setEstrato3(Long estrato3) {
		this.estrato3 = estrato3;
	}
	@Override
	public Long getEstrato4() {
		return estrato4;
	}

	public void setEstrato4(Long estrato4) {
		this.estrato4 = estrato4;
	}
	
	@Override
	public Long getEstrato5() {
		return estrato5;
	}

	public void setEstrato5(Long estrato5) {
		this.estrato5 = estrato5;
	}
	
	@Override
	public Long getEstrato6() {
		return estrato6;
	}

	public void setEstrato6(Long estrato6) {
		this.estrato6 = estrato6;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
	}
	
	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public Boolean getEsVenta() {
		return "S".equals(esVenta);
	}

	public void setEsVenta(Boolean esVenta) {
		this.esVenta = esVenta ? "S" : "N";
	}

	@Override
	public String getTipo() {
		return Constantes.FIJO;
	}
	
}
