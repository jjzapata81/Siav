package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ta_consumos")
public class Consumo implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConsumoPK id;
	
	@Column(name="fedesde")
	private Date fechaDesde;
	
	@Column(name="fehasta")
	private Date fechaHasta;
	
	@Column(name="leanterior")
	private Long lecturaAnterior;
	
	@Column(name="leactual")
	private Long lecturaActual;
	
	@Column(name="consumo")
	private Long consumoMes;
	
	@Column(name="leanteriorcorregido")
	private Long lecturaAnteriorCorregido;
	
	@Column(name="leactualcorregido")
	private Long lecturaActualCorregido;
	
	@Column(name="consumocorregido")
	private Long consumoCorregido;
	
	@Column(name="consumopromedio")
	private Long consumoPromedio;
	
	@Column(name="consumodefinitivo")
	private Long consumoDefinitivo;
	
	@Column(name="cdcausanolectura")
	private Long codigoCausaNoLectura;
	
	@Column(name="snajuste")
	private String ajustado;
	
	@Column(name="usuario_lectura")
	private String usuarioLectura;
	
	@Column(name="snsincronizado")
	private String sincronizado;
	
	@Column(name="observacion")
	private String observacion;
	
	@JoinColumn(name="nminstalacion",referencedColumnName="nminstalacion", updatable=false, insertable=false)
	@ManyToOne
	private Instalacion instalacion;

	public ConsumoPK getId() {
		return id;
	}

	public void setId(ConsumoPK id) {
		this.id = id;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getLecturaAnterior() {
		return lecturaAnterior;
	}

	public void setLecturaAnterior(Long lecturaAnterior) {
		this.lecturaAnterior = lecturaAnterior;
	}

	public Long getLecturaActual() {
		return lecturaActual;
	}

	public void setLecturaActual(Long lecturaActual) {
		this.lecturaActual = lecturaActual;
	}

	public Long getConsumoMes() {
		return consumoMes;
	}

	public void setConsumoMes(Long consumoMes) {
		this.consumoMes = consumoMes;
	}

	public Long getLecturaAnteriorCorregido() {
		return lecturaAnteriorCorregido;
	}

	public void setLecturaAnteriorCorregido(Long lecturaAnteriorCorregido) {
		this.lecturaAnteriorCorregido = lecturaAnteriorCorregido;
	}

	public Long getLecturaActualCorregido() {
		return lecturaActualCorregido;
	}

	public void setLecturaActualCorregido(Long lecturaActualCorregido) {
		this.lecturaActualCorregido = lecturaActualCorregido;
	}

	public Long getConsumoCorregido() {
		return consumoCorregido == null ? 0L : consumoCorregido;
	}

	public void setConsumoCorregido(Long consumoCorregido) {
		this.consumoCorregido = consumoCorregido;
	}

	public Long getConsumoPromedio() {
		return consumoPromedio == null ? 0L : consumoPromedio;
	}

	public void setConsumoPromedio(Long consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}

	public Long getCodigoCausaNoLectura() {
		return codigoCausaNoLectura;
	}

	public void setCodigoCausaNoLectura(Long codigoCausaNoLectura) {
		this.codigoCausaNoLectura = codigoCausaNoLectura;
	}

	public String getUsuarioLectura() {
		return usuarioLectura;
	}

	public void setUsuarioLectura(String usuarioLectura) {
		this.usuarioLectura = usuarioLectura;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public Boolean getAjustado() {
		return "S".equals(ajustado);
	}

	public void setAjustado(Boolean ajustado) {
		this.ajustado = ajustado ? "S" : "N";
	}

	public Boolean getSincronizado() {
		return "S".equals(sincronizado);
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado ? "S" : "N";
	}
	
	public Long getConsumoDefinitivo() {
		return consumoDefinitivo == null ? 0L : consumoDefinitivo;
	}
	
	public void setConsumoDefinitivo(Long consumoDefinitivo) {
		this.consumoDefinitivo = consumoDefinitivo;
	}
	
}
