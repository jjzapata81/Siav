package co.com.siav.bean;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Ciclo;
import co.com.siav.entities.Consumo;
import co.com.siav.entities.ConsumoPK;
import co.com.siav.entities.Instalacion;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryConsumos;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.InstalacionResponse;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;


@Stateless
public class InstalacionBean {
	
	@Inject
	private IRepositoryInstalaciones instalacionRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private IRepositoryConsumos consumosRep;
	
//	@Inject
//	private IRepositoryUsuarios usuarioRep;
//	
//	@Inject
//	private IRepositoryMaestros maestroRep;
//	
//	@Inject
//	private IRepositoryVeredas veredasRep;

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
		instalacionRep.save(instalacion);
		crearConsumo(instalacion);
		return new MensajeResponse(Constantes.getMensaje(Constantes.INSTALACION_CREADA, instalacion.getNumeroInstalacion()));
	}

	public MensajeResponse guardar(Instalacion request) {
		if(!instalacionRep.exists(request.getNumeroInstalacion())){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, request.getNumeroInstalacion()));
		}
		instalacionRep.save(request);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		
	}

	private Consumo crearConsumo(Instalacion instalacion) {
		Consumo consumo = new Consumo();
		consumo.setId(crearConsumoPK(instalacion.getNumeroInstalacion(), ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.ABIERTO), instalacion.getSerieMedidor()));
		consumo.setLecturaActual(0L);
		consumo.setLecturaAnterior(0L);
		consumo.setConsumoPromedio(0L);
		consumo.setSincronizado(false);
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

//	private Usuario crearUsuario(UsuarioRequest usuarioRequest) {
//		
//		Usuario usuario = usuarioRep.findOne(usuarioRequest.getCedula());
//		if(null == usuario){
//			usuario = new Usuario();
//			BeanUtils.copyProperties(usuarioRequest, usuario);
//			usuarioRep.save(usuario);
//		}
//		
//		return usuario;
//	}
	
	private Long crearNumeroInstalacion(Long codigoVereda) {
		long consecutivo = instalacionRep.getMaxId(codigoVereda);
		if(consecutivo == 0){
			consecutivo = codigoVereda * 1000L;
		}
		return consecutivo  + 1L;
	}
}
