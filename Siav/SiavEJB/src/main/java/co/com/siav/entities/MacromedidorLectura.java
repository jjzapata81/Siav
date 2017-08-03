package co.com.siav.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="ta_macro_maestro")
public class MacromedidorLectura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String NOMBRE_SECUENCIA = "MacromedidorLectura.codigo";
	
	@SequenceGenerator(name = MacromedidorLectura.NOMBRE_SECUENCIA, sequenceName = "sq_ta_macro_lectura", allocationSize=1)
	@GeneratedValue(generator = MacromedidorLectura.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name="nmlectura")
	private Long id;
	
	@JoinColumn(name="nmmacro",referencedColumnName="nmmacro", updatable=false, insertable=false)
	@ManyToOne
	private Macromedidor macro;
	
	@Column(name="fedesde")
	private Date fechaDese;
	
	@Column(name="fehasta")
	private Date fechaHasta;

	@Column(name="leanterior")
	private Long lecturaAnterior;

	@Column(name="leactual")
	private Long lecturaActual;

	@Column(name="usuario_lectura")
	private String usuario;

	@Column(name="snsincronizado")
	private String sincronizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Macromedidor getMacro() {
		return macro;
	}

	public void setMacro(Macromedidor macro) {
		this.macro = macro;
	}

	public Date getFechaDese() {
		return fechaDese;
	}

	public void setFechaDese(Date fechaDese) {
		this.fechaDese = fechaDese;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getSincronizado() {
		return "S".equals(sincronizado);
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado ? "S" : "N";
	}
	
}
