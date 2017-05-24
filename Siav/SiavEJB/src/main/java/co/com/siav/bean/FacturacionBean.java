package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;

import co.com.siav.entities.Ciclo;
import co.com.siav.entities.Consumo;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Exceso;
import co.com.siav.entities.Factura;
import co.com.siav.entities.Sistema;
import co.com.siav.entities.Tarifa;
import co.com.siav.facturacion.EstratoManager;
import co.com.siav.facturacion.FacturadorManager;
import co.com.siav.facturacion.IFacturador;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryConsumos;
import co.com.siav.repositories.IRepositoryCreditoDetalle;
import co.com.siav.repositories.IRepositoryExcesos;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositorySistema;
import co.com.siav.repositories.IRepositoryTarifas;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class FacturacionBean {

	@Inject
	private IRepositoryInstalaciones instalacionRep;
	
	@Inject
	private IRepositoryExcesos excesosRep;
	
	@Inject
	private IRepositoryTarifas tarifasRep;
	
	@Inject
	private TarifasBean tarifas;
	
	@Inject
	private IRepositorySistema sistemaRep;
	
	@Inject
	private IRepositoryCiclos cicloRep;
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryCreditoDetalle detalleCreditoRep;
	
	@Inject
	private IRepositoryConsumos consumosRep;
	
	@Inject
	private Tarifador tarifador;
	
	@Inject
	private Validador validador;
	
	@Inject
	private Numerador numerador;
	
	private IFacturador facturador;
	
	private Sistema sistema;
	
	private Long cicloActual;
	
	private Long cicloAnterior;

	public MensajeResponse facturar() {
		sistema = sistemaRep.findAll().get(0);
		Ciclo ciclo = cicloRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
		if(null == ciclo){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_SIN_CICLO_ABIERTO);
		}
		cicloActual = ciclo.getCiclo();
		cicloAnterior = cicloRep.findMaximoCicloPorEstado(Constantes.CERRADO);
		List<Consumo> consumos = consumosRep.findConsumosPrefactura(ciclo.getCiclo(), Constantes.SI, Constantes.FACTURACION_NORMAL);
		Long instalacionesActivas = instalacionRep.countByActivo(Constantes.SI);
		try{
			validador.ejecutar(consumos, instalacionesActivas, excesosRep.findAll(), tarifasRep.findAll(), sistema.getEsPorEstrato());
			numerador.prepararNumeroFactura(ciclo.getCiclo(), consumos.size());
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, e.getMessage());
		}
		facturador = getFacturador();		
		borrarPrefacturacion(ciclo.getCiclo());
		borrarDetelleCredito(ciclo.getCiclo());
		List<Factura> collect = consumos.stream().map(this::crearFactura).collect(Collectors.toList());
		return new MensajeResponse(EstadoEnum.OK, Constantes.getMensaje(Constantes.PREFACTURACION_EXITO, String.valueOf(collect.size())));
	}

	
	private Factura crearFactura(Consumo consumo) {
		Factura factura = new Factura();
		Factura facturaAnterior = facturasRep.findByFacturaCuentasVencidas(consumo.getInstalacion().getNumeroInstalacion(), cicloAnterior);
		factura.setCedula(consumo.getInstalacion().getUsuario().getCedula());
		factura.setCiclo(cicloActual);
		factura.setCuentasVencidas(facturaAnterior == null ? 0L : facturaAnterior.getCancelado() ? 0L : facturaAnterior.getCuentasVencidas() + 1L);
		factura.setDireccion(consumo.getInstalacion().getDireccion());
		factura.setCancelado(false);
		factura.setNombres(consumo.getInstalacion().getUsuario().getNombreCompleto());
		factura.setEstrato(consumo.getInstalacion().getEstrato());
		factura.setSerieMedidor(consumo.getInstalacion().getSerieMedidor());
		factura.setNombreVereda(consumo.getInstalacion().getVereda().getNombre());
		Long numeroFactura = numerador.getNumeroFactura().next();
		factura.setNumeroFactura(numeroFactura);
		factura.setCausaNoLectura(consumo.getCodigoCausaNoLectura());
		factura.setNumeroInstalacion(consumo.getInstalacion().getNumeroInstalacion());
		factura.setFechaDesde(consumo.getFechaDesde());
		factura.setFechaHasta(consumo.getFechaHasta());
		factura.setAjustado(consumo.getAjustado());
		factura.setAbono(false);
		factura.setLecturaAnterior(consumo.getLecturaAnterior());
		factura.setLecturaActual(consumo.getLecturaActual());
		factura.setConsumo(consumo.getConsumoDefinitivo());
		factura.setConsumoPromedio(consumo.getConsumoPromedio());
		List<DetalleFactura> detalles = crearDetalle(consumo, numeroFactura, facturaAnterior);
		if(sistema.getCuentasVencidas().equals(factura.getCuentasVencidas())){
			detalles.add(crearRecargoCV(numeroFactura, cicloActual, factura.getNumeroInstalacion()));
		}
		factura.setDetalles(detalles);
		facturasRep.save(factura);
		return factura;
	}

	private DetalleFactura crearRecargoCV(Long numeroFactura, Long ciclo, Long numeroInstalacion) {
		DetalleFactura conceptoFactura = new DetalleFactura();
		conceptoFactura.setIdFactura(numeroFactura);
		conceptoFactura.setCiclo(ciclo);
		conceptoFactura.setCodigo(sistema.getIdRecargo());
		Tarifa tarifaRecargo = tarifasRep.findOne(sistema.getIdRecargo());
		conceptoFactura.setValor(tarifaRecargo.getEstrato0());
		conceptoFactura.setNombre(tarifaRecargo.getDescripcion());
		conceptoFactura.setInstalacion(numeroInstalacion);
		return conceptoFactura;
	}

	private List<DetalleFactura> crearDetalle(Consumo consumo, Long numeroFactura, Factura facturaAnterior) {
		return tarifador.generar(consumo, numeroFactura, facturaAnterior ,sistema, facturador);
	}

	private void borrarPrefacturacion(Long ciclo) {
		facturasRep.deleteByCiclo(ciclo);
	}
	
	private void borrarDetelleCredito(Long ciclo) {
		detalleCreditoRep.deleteByIdCiclo(ciclo);
	}
	
	private IFacturador getFacturador() {
		IFacturador instance = FacturadorManager.getInstance(sistema.getEsPorRango());
		Exceso consumoBasico = excesosRep.findByCodigo(sistema.getIdBasico());
		Exceso consumoComplementario = excesosRep.findByCodigo(sistema.getIdComplementario());
		Exceso consumoSuntuario = excesosRep.findByCodigo(sistema.getIdSuntuario());
		Tarifa cargoFijo = tarifasRep.findOne(sistema.getIdCargoFijo());
		Tarifa conceptoInteres= tarifasRep.findOne(sistema.getIdInteres());
		Tarifa conceptoCuentasVencidas= tarifasRep.findOne(sistema.getIdMoroso());
		instance.setEstrato(EstratoManager.getInstance(sistema.getEsPorEstrato()));
		instance.setTarifas(consumoBasico, consumoComplementario, consumoSuntuario);
		instance.setConceptosFacturacion(cargoFijo, conceptoInteres, conceptoCuentasVencidas, tarifas.consultar());
		return instance;
	}
	
}