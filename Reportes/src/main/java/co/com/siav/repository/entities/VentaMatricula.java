package co.com.siav.repository.entities;

import java.util.Date;

public class VentaMatricula {
	
		private Date fechaPago;
		private String cedula;
		private String nombre;
		private String concepto;
		private Double valor;
		private Double inicial;
		
		public Date getFechaPago() {
			return fechaPago;
		}
		public void setFechaPago(Date fechaPago) {
			this.fechaPago = fechaPago;
		}
		public String getCedula() {
			return cedula;
		}
		public void setCedula(String cedula) {
			this.cedula = cedula;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getConcepto() {
			return concepto;
		}
		public void setConcepto(String concepto) {
			this.concepto = concepto;
		}
		public Double getValor() {
			return valor;
		}
		public void setValor(Double valor) {
			this.valor = valor;
		}
		public Double getInicial() {
			return inicial;
		}
		public void setInicial(Double inicial) {
			this.inicial = inicial;
		}
		
}
