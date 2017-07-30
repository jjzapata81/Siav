package co.com.siav.response;

import java.util.List;
import java.util.stream.Collectors;

import co.com.siav.entities.Consumo;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.Factura;

public class InstalacionInfo {
	
	private Long numeroInstalacion;
	
	private List<CreditoMaestroInfo> creditos;
	
	private List<FacturaInfo> facturas;
	
	private List<Consumo> consumos;
	
	public InstalacionInfo() {
		super();
	}

	public InstalacionInfo(Long numeroInstalacion, List<CreditoMaestro> creditos, List<Factura> facturas, List<Consumo> consumos) {
		this.numeroInstalacion = numeroInstalacion;
		this.creditos = creditos.stream().map(CreditoMaestroInfo::new).collect(Collectors.toList());
		this.facturas = facturas.stream().map(FacturaInfo::new).collect(Collectors.toList());
		this.consumos = consumos;
	}
	
	public Long getNumeroInstalacion() {
		return numeroInstalacion;
	}
	
	public void setNumeroInstalacion(Long numeroInstalacion) {
		this.numeroInstalacion = numeroInstalacion;
	}


	public List<CreditoMaestroInfo> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<CreditoMaestroInfo> creditos) {
		this.creditos = creditos;
	}

	public List<FacturaInfo> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<FacturaInfo> facturas) {
		this.facturas = facturas;
	}

	public List<Consumo> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

}
