package co.com.siav.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ta_formato_recaudo")
public class FormatoRecaudo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idformato")
	private String id;
	
	private Long fecha;
	
	private Long valor;
	
	private Long referencia;
	
	@Column(name="formato_fecha")
	private String formatoFecha;
	
	private String separador;
	
	@Column(name="separador_aux")
	private String separadorAux;
	
	@Column(name="posicion_aux")
	private Long posicionAux;
	
	@Column(name="posicion_inicial_fra")
	private Long posicionInicialFactura;
	
	@Column(name="posicion_final_fra")
	private Long posicionFinalFactura;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getFecha() {
		return null == fecha ? 0 : fecha.intValue();
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public int getValor() {
		return null == valor ? 0 : valor.intValue();
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public int getReferencia() {
		return null == referencia ? 0 : referencia.intValue();
	}

	public void setReferencia(Long referencia) {
		this.referencia = referencia;
	}

	public String getFormatoFecha() {
		return formatoFecha;
	}

	public void setFormatoFecha(String formatoFecha) {
		this.formatoFecha = formatoFecha;
	}

	public String getSeparador() {
		return separador;
	}

	public void setSeparador(String separador) {
		this.separador = separador;
	}

	public String getSeparadorAux() {
		return separadorAux;
	}

	public void setSeparadorAux(String separadorAux) {
		this.separadorAux = separadorAux;
	}

	public int getPosicionAux() {
		return null == posicionAux ? 0 : posicionAux.intValue();
	}

	public void setPosicionAux(Long posicionAux) {
		this.posicionAux = posicionAux;
	}

	public int getPosicionInicialFactura() {
		return null == posicionInicialFactura ? 0 : posicionInicialFactura.intValue();
	}

	public void setPosicionInicialFactura(Long posicionInicialFactura) {
		this.posicionInicialFactura = posicionInicialFactura;
	}

	public int getPosicionFinalFactura() {
		return null == posicionFinalFactura ? 0 : posicionFinalFactura.intValue();
	}

	public void setPosicionFinalFactura(Long posicionFinalFactura) {
		this.posicionFinalFactura = posicionFinalFactura;
	}
	
}
