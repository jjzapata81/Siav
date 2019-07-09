package co.com.siav.facturacion;

import java.util.List;
import java.util.stream.Collectors;

import co.com.siav.entities.CreditoDetalle;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Exceso;
import co.com.siav.entities.Factura;
import co.com.siav.entities.IEstrato;
import co.com.siav.entities.Material;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.Tarifa;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.Utilidades;

public class FacturadorBase {
	
	protected IValorEstrato valorEstrato;
	
	protected Exceso basico;
	
	protected Exceso complementario;
	
	protected Exceso suntuario;
	
	protected Tarifa cargoFijo;
	
	protected Tarifa cuentaVencida;
	
	protected Tarifa conceptoInteres;
		
	protected List<Concepto> conceptos;
	
	protected List<Tarifa> tarifas;
	
	private Double interes;
	
	private Factura facturaAnterior;
	
	public void setTarifas(Exceso basico, Exceso complementario, Exceso suntuario){
		this.basico = basico;
		this.complementario = complementario;
		this.suntuario = suntuario;
	}
	
	public void setConceptosFacturacion(Tarifa cargoFijo, Tarifa conceptoInteres, Tarifa cuentaVencida, List<Tarifa> tarifas){
		this.cargoFijo = cargoFijo;
		this.conceptoInteres = conceptoInteres;
		this.cuentaVencida = cuentaVencida;
		this.tarifas = tarifas;
	}

	public void setEstrato(IValorEstrato valorEstrato){
		this.valorEstrato = valorEstrato;
	}
	
	public void setCuentasVencidas(Factura facturaAnterior){
		this.facturaAnterior = facturaAnterior;
	}
	
	protected Concepto crearConcepto(String codigo, String nombre, Long consumo, Long valor, Long vencido){
		Concepto concepto = new Concepto();
		concepto.setCodigo(codigo);
		concepto.setNombre(nombre);
		concepto.setValor(consumo.equals(0L) ? 0L : consumo * valor);
		concepto.setDetalle(consumo.equals(0L) ? "" : "" + consumo + " ms x " + Utilidades.formatoMoneda(valor));
		concepto.setVencido(vencido);
		concepto.setMetros(consumo);
		return concepto;
	}
	
	public Long getValor(IEstrato tarifa, String estrato){
		return valorEstrato.getValor(tarifa, estrato);
	}
	
	public Concepto getCargoFijo(String estrato) {
		return new Concepto(cargoFijo.getCodigo(),cargoFijo.getDescripcion(),"", 0L, getValor(cargoFijo, estrato), getVencido(cargoFijo.getCodigo()));
	}
	
	public ConceptoCredito getCredito(CreditoDetalle creditoDetalle, String codigoConcepto){
		Tarifa conceptoCredito = buscarTarifa(codigoConcepto);
		Long vencido = getVencido(codigoConcepto);
		if(null!=conceptoCredito || 0 != vencido.longValue()){
			interes = (double) creditoDetalle.getInteres();
			return new ConceptoCredito(conceptoCredito.getCodigo(), conceptoCredito.getDescripcion(), conceptoCredito.getDescripcion() + " CUOTA NRO " + (creditoDetalle.getCuota()), 0L, creditoDetalle.getCapital(), vencido, creditoDetalle.getId().getCodCredito());
		}
		interes = new Double(0);
		return null;
	}
	
	public ConceptoCredito getInteres(CreditoMaestro credito){
		Long vencido = getVencido(conceptoInteres.getCodigo());
		if(0 != interes.longValue() || 0 != vencido.longValue()){
			return new ConceptoCredito(conceptoInteres.getCodigo(), conceptoInteres.getDescripcion(), ""+ credito.getSaldo() + " x " + credito.getInteres() + "%", 0L, interes.longValue(), vencido, credito.getId());	
		}
		return null;
	}
	
	public Concepto getNovedad(Novedad novedad, String estrato){
		Tarifa tarifaNovedad = buscarTarifa(novedad.getId().getCodigoConcepto());
		Long vencido = null == tarifaNovedad ? 0L : getVencido(tarifaNovedad.getCodigo());
		if(null!=tarifaNovedad || 0 != vencido.longValue()){
			Long valor = tarifaNovedad.getTipo().equalsIgnoreCase(Constantes.FIJO) ? getValor(tarifaNovedad, estrato) : novedad.getValor();
			return new Concepto(tarifaNovedad.getCodigo(), tarifaNovedad.getDescripcion(), "", 0L, valor, vencido);
		}
		return null;
	}
	
	public Concepto getMateriales(Material material){
		Long vencido = getVencido(material.getCodigo());
		return new Concepto(material.getCodigo(), material.getDescripcion(), "", 0L, material.getValorSinIva().longValue(), vencido);
	}
	
	private Tarifa buscarTarifa(String concepto){
		return tarifas.stream().filter(tf -> concepto.equals(tf.getCodigo())).findFirst().orElse(null);
	}
	
	public Long getVencido(String concepto){
		if(facturaAnterior == null){
			return 0L;
		}
		DetalleFactura conceptoAnterior = facturaAnterior.getDetalles().stream().filter(factura -> factura.getCodigo().equals(concepto)).findFirst().orElse(null);
		if(conceptoAnterior!=null){
			conceptoAnterior.setUsed(true);
		}
		return null == conceptoAnterior ? 0L : conceptoAnterior.getValor() + conceptoAnterior.getSaldo() - conceptoAnterior.getCartera();
	}
	
	public List<Concepto> getOtrosVencidos(){
		return facturaAnterior.getDetalles().stream().filter(item -> !item.isUsed()).map(this::getConcepto).collect(Collectors.toList());
	}
	
	private Concepto getConcepto(DetalleFactura detalleFactura){
		return new Concepto(detalleFactura.getCodigo(), detalleFactura.getNombre(), Constantes.VENCIDO, 0L, 0L, detalleFactura.getValor() + detalleFactura.getSaldo() - detalleFactura.getCartera());
	}
	
}
