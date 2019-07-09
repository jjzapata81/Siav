package co.com.siav.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Consumo;
import co.com.siav.entities.Desactivacion;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.entities.Instalacion;
import co.com.siav.repositories.IRepositoryDesactivacion;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.request.DesactivacionRequest;
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
	private CiclosBean ciclosBean;
	
	@Inject
	private ConsumosBean consumosBean;
	
	@Inject
	private AutorizacionBean autorizacion;
	
	@Inject
	private IRepositoryDesactivacion desactivacionRep;
	
	public InstalacionResponse consultarInstalacionPorNumero(Long numeroInstalacion) {
		Instalacion instalacion = consultaPorNumero(numeroInstalacion);
		if(instalacion == null){
			return new InstalacionResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, numeroInstalacion));
		}
		InstalacionResponse response = new InstalacionResponse(instalacion);
		List<Consumo> consumos = consumosBean.porInstalacion(instalacion.getNumeroInstalacion());
		response.setActivar(!instalacion.getActivo() && consumos.isEmpty());
		return response;
	}

	public Instalacion consultaPorNumero(Long numeroInstalacion) {
		return instalacionRep.findOne(numeroInstalacion);
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
		Long ciclo = ciclosBean.getPorEstado(Constantes.CERRADO).getCiclo();
		Factura factura = facturasRep.findByNumeroInstalacionAndCiclo(numeroInstalacion, ciclo);
		if(factura != null && factura.getDetalles().stream().filter(detalle -> !detalle.getCancelado()).findAny().isPresent()){
			Long vencido = factura.getDetalles().stream().mapToLong(this::getVencido).sum();
			if(vencido!=0){
				String mensaje = vencido > 0 ? Constantes.VALOR_VENCIDO : Constantes.SALDO_FAVOR;
				return new CuentasVencidasResponse(EstadoEnum.INFO, Constantes.getMensaje(mensaje, vencido), autorizacion.get(usuario, Constantes.ACCION_CUENTAS_VENCIDAS));
			}
		}
		return new CuentasVencidasResponse(Constantes.ACTUALIZACION_EXITO);
	}
	
	private Long getVencido(DetalleFactura detalle){
		return detalle.getValor() + detalle.getSaldo() - detalle.getCartera();
	}

	public MensajeResponse activar(Long numeroInstalacion) {
		try{
			Instalacion instalacion = consultaPorNumero(numeroInstalacion);
			ciclosBean.actualizar(instalacion);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
	
	public MensajeResponse desactivar(DesactivacionRequest request){
		try{
			Instalacion instalacion = instalacionRep.findOne(request.getInstalacion());
			instalacion.setActivo(false);
			instalacion.setFechaDesactivacion(new Date());
			instalacionRep.save(instalacion);
			Desactivacion desactivacion = new Desactivacion();
			desactivacion.setInstalacion(request.getInstalacion());
			desactivacion.setUsuario(request.getUsuario());
			desactivacion.setObservacion(request.getObservacion());
			desactivacionRep.save(desactivacion);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
}
