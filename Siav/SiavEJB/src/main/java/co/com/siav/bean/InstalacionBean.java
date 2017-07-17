package co.com.siav.bean;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.entities.Instalacion;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.response.CuentasVencidasResponse;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.InstalacionResponse;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;


@Stateless
public class InstalacionBean {
	
	@Inject
	private IRepositoryInstalaciones instalacionRep;
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private AutorizacionBean autorizacion;
	
	public InstalacionResponse consultarInstalacionPorNumero(String numeroInstalacion) {
		Instalacion instalacion = instalacionRep.findOne(Long.valueOf(numeroInstalacion));
		if(instalacion == null){
			return new InstalacionResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, numeroInstalacion));
		}
		InstalacionResponse response = new InstalacionResponse(instalacion);
		return response;
	}
	
	public MensajeResponse crear(Instalacion instalacion){
		instalacion.setNumeroInstalacion(crearNumeroInstalacion(instalacion.getVereda().getCodigo()));
		instalacion.setCuentasVencidas(0L);
		instalacion.setActivo(false);
		instalacion.setConMedidor(false);
		instalacion.setFechaInstalacion(new Date());
		instalacion.setDigitosMedidor(null == instalacion.getDigitosMedidor() ? 0L : instalacion.getDigitosMedidor());
		instalacion.setSerieMedidor(null == instalacion.getSerieMedidor() ? "0" : instalacion.getSerieMedidor());
		instalacion.setOrden(Constantes.SIN_ORDEN);
		instalacionRep.save(instalacion);
		return new MensajeResponse(Constantes.getMensaje(Constantes.INSTALACION_CREADA, instalacion.getNumeroInstalacion()));
	}

	public MensajeResponse guardar(Instalacion request) {
		if(!instalacionRep.exists(request.getNumeroInstalacion())){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, request.getNumeroInstalacion()));
		}
		instalacionRep.save(request);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		
	}
	
	private Long crearNumeroInstalacion(Long codigoVereda) {
		Long limiteInicial = codigoVereda * 1000L;
		Long limiteFinal = (codigoVereda + 1L) * 1000L;
		long consecutivo = instalacionRep.getMaxNumeroInstalacion(limiteInicial, limiteFinal);
		if(consecutivo == 0){
			return limiteInicial;
		}
		return consecutivo  + 1L;
	}

	public CuentasVencidasResponse consultarVencido(Long numeroInstalacion, String usuario) {
		Long ciclo = ciclosRep.findMaximoCicloPorEstado(Constantes.CERRADO);
		Factura factura = facturasRep.findByNumeroInstalacionAndCiclo(numeroInstalacion, ciclo);
		if(factura.getDetalles().stream().filter(detalle -> !detalle.getCancelado()).findAny().isPresent()){
			Long vencido = factura.getDetalles().stream().mapToLong(this::getVencido).sum();
			return new CuentasVencidasResponse(EstadoEnum.INFO, Constantes.getMensaje(Constantes.VALOR_VENCIDO, vencido), autorizacion.get(usuario, Constantes.ACCION_CUENTAS_VENCIDAS));
		}
		return new CuentasVencidasResponse(Constantes.ACTUALIZACION_EXITO);
	}
	
	private Long getVencido(DetalleFactura detalle){
		return detalle.getValor() + detalle.getSaldo() - detalle.getCartera();
	}
}
