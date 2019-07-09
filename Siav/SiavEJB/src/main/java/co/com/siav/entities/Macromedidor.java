package co.com.siav.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="ta_macro_maestro")
public class Macromedidor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String NOMBRE_SECUENCIA = "Macromedidor.codigo";
	
	@SequenceGenerator(name = Macromedidor.NOMBRE_SECUENCIA, sequenceName = "sq_ta_macro_maestro", allocationSize=1)
	@GeneratedValue(generator = Macromedidor.NOMBRE_SECUENCIA, strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name="nmmacro")
	private Long macro;

	@Column(name="nombre")
	private String nombre;

	@Column(name="marca")
	private String marca;

	@Column(name="direccion")
	private String direccion;

	public Long getMacro() {
		return macro;
	}

	public void setMacro(Long macro) {
		this.macro = macro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca != null ? marca.toUpperCase() : null;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion != null ? direccion.toUpperCase() : null;
	}

}
