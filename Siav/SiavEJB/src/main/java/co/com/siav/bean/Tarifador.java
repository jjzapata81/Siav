package co.com.siav.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Consumo;
import co.com.siav.entities.CreditoDetalle;
import co.com.siav.entities.CreditoDetallePK;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.Sistema;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.facturacion.Concepto;
import co.com.siav.facturacion.ConceptoCredito;
import co.com.siav.facturacion.IFacturador;
import co.com.siav.repositories.IRepositoryCreditoMaestro;
import co.com.siav.repositories.IRepositoryNovedades;
import co.com.siav.utils.Constantes;

@Stateless
public class Tarifador {
	
	@Inject
	private IRepositoryNovedades novedadesRep;
	
	@Inject
	private IRepositoryCreditoMaestro creditoMaestroRep;
	
	private List<DetalleFactura> conceptos;
	
	private List<DetalleFactura> conceptosAux;
	
	private Long numeroFactura;
	
	private Long ciclo;
	
	private Long instalacion;
	
	private Sistema sistema;
	
	private IFacturador facturador;
	
	public List<DetalleFactura> generar(Consumo consumo, Long numeroFactura, Factura facturaAnterior, Sistema sistemaBD, IFacturador facturador) {
		this.numeroFactura = numeroFactura;
		this.ciclo = consumo.getId().getCiclo();
		this.instalacion = consumo.getInstalacion().getNumeroInstalacion();
		this.facturador = facturador;
		this.facturador.setCuentasVencidas(facturaAnterior);
		sistema = sistemaBD;
		conceptos = new ArrayList<DetalleFactura>();
		conceptosAux = new ArrayList<DetalleFactura>();
		generarCargoFijo(consumo.getInstalacion().getEstrato());
		generarExceso(consumo.getConsumoDefinitivo(), consumo.getInstalacion().getEstrato());
		if(sistema.getTieneTope()){
			generarConTope();
		}else{
			conceptos.addAll(conceptosAux);
		}
		generarCreditos(consumo.getInstalacion().getNumeroInstalacion());
		generarNovedades(consumo.getInstalacion().getNumeroInstalacion(), consumo.getId().getCiclo(), consumo.getInstalacion().getEstrato());
		if(facturaAnterior!=null){
			generarOtrosVencidos();
			//TODO: Calcular recagro
//			generarConceptoRegargo(facturaAnterior);
		}
		return conceptos;
	}
	

	private void generarCargoFijo(String estrato) {
		agregarConcepto(facturador.getCargoFijo(estrato), true);
	}

	private void generarExceso(Long consumoPeriodo, String estrato) {
		Long consumoExceso = consumoPeriodo - sistema.getConsumoMinimo();
		if(consumoExceso >= 0){
			List<Concepto> conceptos = facturador.generar(consumoExceso, estrato);
			conceptos.stream().forEach(cto -> agregarConcepto(cto, true));
		}
	}
	
	private void generarCreditos(Long instalacion) {
		try{
			List<CreditoMaestro> creditos = buscarCreditos(instalacion);
			if(!creditos.isEmpty()){
				creditos.stream().forEach(credito -> generarDetalleCredito(credito));
			}
		}catch(Exception e){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_GENERACION_CREDITO, instalacion) + " " + e.getMessage());
		}
	}

	private void generarDetalleCredito(CreditoMaestro credito) {
		Long capital = (credito.getValor() - credito.getInicial()) / credito.getNumeroCuotas();
		Double interes = credito.getSaldo() * (credito.getInteres()/100);
		Long numeroCuota = 1L;
		if(null != credito.getCuotas()){
			numeroCuota = credito.getCuotas().stream().max((t1, t2) -> t1.getCuota().intValue() - t2.getCuota().intValue()).orElse(new CreditoDetalle()).getCuota() + 1L;
		}
		CreditoDetalle creditoDetalle = new CreditoDetalle();
		creditoDetalle.setId(createId(credito.getId()));
		creditoDetalle.setCapital(capital);
		creditoDetalle.setCuota(numeroCuota);
		creditoDetalle.setFactura(numeroFactura);
		creditoDetalle.setInstalacion(instalacion);
		creditoDetalle.setInteres(interes.longValue());
		ConceptoCredito conceptoCredito = facturador.getCredito(creditoDetalle, credito.getCodigoConcepto());
		if(null!=conceptoCredito){
			agregarConceptoCredito(conceptoCredito);
		}
		ConceptoCredito conceptoInteres = facturador.getInteres(credito);
		if(null!=conceptoInteres){
			agregarConceptoCredito(conceptoInteres);
		}
		credito.getCuotas().add(creditoDetalle);
		creditoMaestroRep.save(credito);
	}
	
	private CreditoDetallePK createId(Long codCredito) {
		CreditoDetallePK pk = new CreditoDetallePK();
		pk.setCiclo(ciclo);
		pk.setCodCredito(codCredito);
		return pk;
	}


	private void generarNovedades(Long instalacion, Long ciclo, String estrato) {
		List<Novedad> novedades = novedadesRep.findByIdInstalacionAndIdCiclo(instalacion, ciclo);
		if(!novedades.isEmpty()){
			novedades.stream().forEach(novedad -> generarDetalleNovedad(novedad, estrato));
		}
	}
	
	private void generarDetalleNovedad(Novedad novedad, String estrato) {
		Concepto conceptoNovedad = facturador.getNovedad(novedad, estrato);
		agregarConcepto(conceptoNovedad);
	}
	
//Este metodo
	private void generarConTope() {
		long sumaItems = conceptosAux.stream().mapToLong(DetalleFactura::getValor).sum();
		if(sumaItems < sistema.getTope()){
			conceptosAux.stream().forEach(concepto -> {
				Long valor = null;
				if(concepto.getCodigo().equalsIgnoreCase(sistema.getIdCargoFijo())){
					valor = sistema.getTope();
				}else{
					valor = 0L;
				}
				
				if(valor > 0 || concepto.getCartera() > 0){
					agregarConcepto(new Concepto(concepto.getCodigo(), concepto.getNombre(), concepto.getDetalle(), 0L, valor, concepto.getCartera()));
				}
				
			});
		}else{
			conceptos.addAll(conceptosAux);
		}
	}
	
	private List<CreditoMaestro> buscarCreditos(Long instalacion) {
		 return creditoMaestroRep.findByInstalacionSinCancelar(instalacion);
	}
	
	private void generarOtrosVencidos() {
		facturador.getOtrosVencidos().stream().forEach(item -> agregarConcepto(item));
	}
	

	private void generarConceptoRegargo(Factura facturaAnterior) {
		Long totalVencido = facturaAnterior.getDetalles().stream().mapToLong(this::getValorTotalVencido).sum();
		if(sistema.getEsRecargoFijo()){
			//TODO: Ojo, aca va el valor del cargo fijo
			totalVencido++;
		}else{
			Double valor = totalVencido.doubleValue() * sistema.getPorcentajeRecargo();
			totalVencido = valor.longValue();
		}
	}
	
	private Long getValorTotalVencido(DetalleFactura detalleFactura){
		return detalleFactura.getValor() + detalleFactura.getSaldo() - detalleFactura.getCartera();
	}

	private void agregarConcepto(Concepto concepto){
		agregarConcepto(concepto, false);
	}
	
	private void agregarConcepto(Concepto concepto, boolean isAux){
		if(concepto != null && (concepto.getValor() != 0L || concepto.getVencido() != 0L)){
			validarCodigoDuplicado(concepto);
			DetalleFactura conceptoFactura = new DetalleFactura();
			conceptoFactura.setIdFactura(numeroFactura);
			conceptoFactura.setCiclo(ciclo);
			conceptoFactura.setCodigo(concepto.getCodigo());
			conceptoFactura.setDetalle(concepto.getDetalle());
			conceptoFactura.setValor(concepto.getValor());
			conceptoFactura.setNombre(concepto.getNombre());
			conceptoFactura.setSaldo(concepto.getVencido());
			conceptoFactura.setMetros(concepto.getMetros());
			conceptoFactura.setInstalacion(instalacion);
			if(isAux){
				conceptosAux.add(conceptoFactura);
			}else{
				conceptos.add(conceptoFactura);
			}
		}
	}


	private void validarCodigoDuplicado(Concepto concepto) {
		if(conceptos.stream().filter(item -> item.getCodigo().equals(concepto.getCodigo())).findAny().isPresent()){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_CONCEPTO_DUPLICADO, concepto.getCodigo(), instalacion) );
		}
	}
	
	private void agregarConceptoCredito(ConceptoCredito concepto){
		if(concepto != null && (concepto.getValor() != 0L || concepto.getVencido() != 0L)){
			validarCodigoDuplicado(concepto);
			DetalleFactura conceptoFactura = new DetalleFactura();
			conceptoFactura.setIdFactura(numeroFactura);
			conceptoFactura.setCiclo(ciclo);
			conceptoFactura.setCodigo(concepto.getCodigo());
			conceptoFactura.setDetalle(concepto.getDetalle());
			conceptoFactura.setValor(concepto.getValor());
			conceptoFactura.setNombre(concepto.getNombre());
			conceptoFactura.setSaldo(concepto.getVencido());
			conceptoFactura.setInstalacion(instalacion);
			conceptoFactura.setIdCredito(concepto.getIdCredito());
			conceptos.add(conceptoFactura);
		}
	}
}
