package co.com.siav.bean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;

import co.com.siav.entities.Ciclo;
import co.com.siav.entities.CreditoDetalle;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryCreditoMaestro;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class CiclosBean {
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private IRepositoryCreditoMaestro creditosRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryFacturas facturasRep;
	

	public Ciclo consultar() {
		return ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO);
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
			
			return new MensajeResponse(Constantes.getMensaje(Constantes.CICLO_CERRADO, cicloActual.getCiclo()));
		}catch (Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, e.getMessage());
		}
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
		nuevoCiclo.setFecha(new Date());
		nuevoCiclo.setMensaje(ciclo.getMensaje());
		nuevoCiclo.setMensajePuntoPago(ciclo.getMensajePuntoPago());
		nuevoCiclo.setMensajeReclamo(ciclo.getMensajeReclamo());
		ciclosRep.save(nuevoCiclo);
	}

	private void marcarCuentasVencidas(Long numeroCiclo) {
		instalacionesRep.updateCuentasVencidas(numeroCiclo);
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
			facturasRep.save(factura);
		}
	}

	private String getHistoricos(Factura facturaAnterior, String mesAnterior) {
		StringBuilder sb = new StringBuilder();
		if(null != facturaAnterior.getHistoricoConsumos()){
			String[] meses = facturaAnterior.getHistoricoConsumos().split(";");
			int longitud = meses.length >= 6 ? 5 : meses.length;
			for(int i = 1; i <= longitud; i++){
				sb.append(meses[i]);
				sb.append(";");
			}
		}
		sb.append(String.format("%d-%s;", facturaAnterior.getConsumo(), mesAnterior));
		return sb.toString();
	}


}
