package co.com.siav.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Ciclo;
import co.com.siav.entities.Consumo;
import co.com.siav.entities.ConsumoPK;
import co.com.siav.entities.CreditoDetalle;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.entities.Instalacion;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryConsumos;
import co.com.siav.repositories.IRepositoryCreditoMaestro;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.Utilidades;

@Stateless
public class CiclosBean {
	
	private static final String SEPARADOR = ";";

	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private IRepositoryCreditoMaestro creditosRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryConsumos consumosRep;
	
	@Inject
	private KardexBean kardexBean;

	public Ciclo consultar() {
		return ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
	}
	
	public Ciclo getPorEstado(String estado) {
		return ciclosRep.findFirstByEstadoOrderByCicloDesc(estado);
	}

	public MensajeResponse editar(Ciclo ciclo) {
		try{
			Ciclo cicloBD = ciclosRep.findOne(ciclo.getCiclo());
			cicloBD.setFechaConRecargo(ciclo.getFechaConRecargo());
			cicloBD.setFechaSinRecargo(ciclo.getFechaSinRecargo());
			cicloBD.setMensaje(ciclo.getMensaje());
			cicloBD.setMensajePuntoPago(ciclo.getMensajePuntoPago());
			cicloBD.setMensajeReclamo(ciclo.getMensajeReclamo());
			ciclosRep.save(cicloBD);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch (Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, e.getMessage());
		}
	}

	public List<Ciclo> consultarTodos() {
		return ciclosRep.findAll();
	}

	public MensajeResponse cerrar() {
		try{
			Ciclo cicloActual = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
			completarHistorico(cicloActual.getCiclo());
			actualzarCreditos(cicloActual.getCiclo());
			cicloActual.setFechaFacturacion(new Date());
			cicloActual.setEstado(Constantes.CERRADO);
			ciclosRep.save(cicloActual);
			crearNuevoCiclo(cicloActual);
			marcarCuentasVencidas(cicloActual.getCiclo());
			actualizarInstalaciones(cicloActual);
			kardexBean.cerrar(cicloActual.getCiclo());
			return new MensajeResponse(Constantes.getMensaje(Constantes.CICLO_CERRADO, cicloActual.getCiclo()));
		}catch (Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, e.getMessage());
		}
	}

	private void actualizarInstalaciones(Ciclo cicloActual) {
		List<Instalacion> inactivas = instalacionesRep.findToActivate(cicloActual.getFecha());
		inactivas.stream().forEach(instalacion -> actualizar(instalacion));
	}

	public void actualizar(Instalacion instalacion) {
		instalacion.setActivo(true);
		instalacionesRep.save(instalacion);
		crearConsumo(instalacion);
	}

	private void actualzarCreditos(Long ciclo) {
		List<CreditoMaestro> creditos = creditosRep.findAllSinCancelar();
		creditos.stream().forEach(credito -> actualizar(credito, ciclo));
	}

	private void actualizar(CreditoMaestro credito, Long ciclo) {
		CreditoDetalle detalle = credito.getCuotas().stream().filter(item->ciclo.equals(item.getId().getCiclo())).findFirst().orElse(new CreditoDetalle());
		credito.setSaldo(credito.getValor() - credito.getInicial() - (detalle.getCuota() * detalle.getCapital()));
		credito.setActual(detalle.getCuota());
		creditosRep.save(credito);
	}

	private void crearNuevoCiclo(Ciclo ciclo) {
		Ciclo nuevoCiclo = new Ciclo();
		nuevoCiclo.setCiclo(ciclo.getCiclo() + 1L);
		nuevoCiclo.setEstado(Constantes.ABIERTO);
		nuevoCiclo.setFecha(Utilidades.fechaPrimerDia(new Date()));
		nuevoCiclo.setMensaje(ciclo.getMensaje());
		nuevoCiclo.setMensajePuntoPago(ciclo.getMensajePuntoPago());
		nuevoCiclo.setMensajeReclamo(ciclo.getMensajeReclamo());
		ciclosRep.save(nuevoCiclo);
	}

	private void marcarCuentasVencidas(Long numeroCiclo) {
		facturasRep.findByCiclo(numeroCiclo).stream().forEach(this::actualizarCuentasVencidas);
	}
	
	@Asynchronous
	private void actualizarCuentasVencidas(Factura factura){
		Instalacion instalacion = instalacionesRep.findOne(factura.getNumeroInstalacion());
		instalacion.setCuentasVencidas(factura.getCuentasVencidas());
		instalacionesRep.save(instalacion);
	}
	
	private void completarHistorico(Long cicloActual) {
		Ciclo cicloAnterior = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO);
		List<Factura> facturas = facturasRep.findByCiclo(cicloActual);
		facturas.stream().forEach(factura -> completar(factura, cicloAnterior));
	}
	
	private void completar(Factura factura, Ciclo cicloAnterior){
		Factura facturaAnterior = facturasRep.findByNumeroInstalacionAndCiclo(factura.getNumeroInstalacion(), cicloAnterior.getCiclo());
		if(null != facturaAnterior){
			factura.setFechaUltimoPago(facturasRep.findByUltimaFechaPago(factura.getNumeroInstalacion()));
			factura.setConsumoAnterior(facturaAnterior.getConsumo());
			factura.setValorUltimoPago(
					facturaAnterior.getDetalles().stream().mapToLong(DetalleFactura::getValor).sum() + 
					facturaAnterior.getDetalles().stream().mapToLong(DetalleFactura::getSaldo).sum());
			factura.setHistoricoConsumos(getHistoricos(facturaAnterior, cicloAnterior.getNombreMes().substring(0,3).toLowerCase()));
		}
		facturasRep.save(factura);
	}

	private String getHistoricos(Factura facturaAnterior, String mesAnterior) {
		try{
			String consumo = String.format(Constantes.FORMATO_HISTORICO, facturaAnterior.getConsumo(), mesAnterior);
			String historico = Constantes.VACIO;
			if(null != facturaAnterior.getHistoricoConsumos()){
				String[] meses = facturaAnterior.getHistoricoConsumos().split(SEPARADOR);
				int start = meses.length < 6 ? 0 : 1;
				historico = Arrays.stream(meses).skip(start).collect(Collectors.joining(SEPARADOR)) + SEPARADOR;
			}
			return historico + consumo;
		}catch (Exception e){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERROR_HISTORICO, facturaAnterior.getNumeroFactura()));
		}
	}
	
	private Consumo crearConsumo(Instalacion instalacion) {
		Ciclo ciclo = getPorEstado(Constantes.ABIERTO);
		Consumo consumo = new Consumo();
		consumo.setId(crearConsumoPK(instalacion.getNumeroInstalacion(), ciclo, instalacion.getSerieMedidor()));
		consumo.setLecturaActual(0L);
		consumo.setLecturaAnterior(0L);
		consumo.setConsumoPromedio(0L);
		consumo.setConsumoMes(0L);
		consumo.setConsumoDefinitivo(0L);
		consumo.setSincronizado(true);
		consumo.setCodigoCausaNoLectura(0L);
		consumo.setFechaDesde(new Date());
		consumo.setFechaHasta(new Date());
		consumosRep.save(consumo);
		return consumo;
	}

	private ConsumoPK crearConsumoPK(Long instalacion, Ciclo ciclo, String serieMedidor) {
		ConsumoPK pk = new ConsumoPK();
		pk.setCiclo(ciclo.getCiclo());
		pk.setInstalacion(instalacion);
		pk.setSerieMedidor(serieMedidor);
		return pk;
	}
}
