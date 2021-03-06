package co.com.siav.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.dto.ConfiguracionRuta;
import co.com.siav.entities.Instalacion;
import co.com.siav.entities.UsuarioRamal;
import co.com.siav.entities.UsuarioRamalPK;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryUsuarioRamal;
import co.com.siav.request.UsuarioRamalRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class RutaBean {
	
	private final static Long SIN_ORDEN = 99999L;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryUsuarioRamal usuarioRamalRep;

	public List<ConfiguracionRuta> consultar() {
		List<Instalacion> rutas = instalacionesRep.findByOrden(SIN_ORDEN);
		if(rutas.isEmpty()){
			return new ArrayList<ConfiguracionRuta>();
		}
		return rutas.stream().map(this::transform).collect(Collectors.toList());
	}
	
	public ConfiguracionRuta consultarPorNumero(Long numeroInstalacion) {
		if(!instalacionesRep.exists(numeroInstalacion)){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, numeroInstalacion));
		}
		return transform(instalacionesRep.findOne(numeroInstalacion));
	}

	private ConfiguracionRuta transform(Instalacion instalacion){
		ConfiguracionRuta ruta = new ConfiguracionRuta();
		ruta.setNombre(instalacion.getUsuario().getNombres());
		ruta.setApellido(instalacion.getUsuario().getApellidos());
		ruta.setInstalacion(instalacion.getNumeroInstalacion());
		ruta.setRamal(instalacion.getRamal());
		ruta.setSerieMedidor(instalacion.getSerieMedidor());
		return ruta;
	}

	public MensajeResponse guardar(ConfiguracionRuta request) {
		Instalacion instalacionBD = instalacionesRep.findOne(request.getInstalacion());
		if(null == instalacionBD){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, request.getInstalacion()));
		}
		if(request.isCambiarOrden()){
			instalacionBD.setOrden(getOrden(request));
		}
		instalacionBD.setSerieMedidor(request.getSerieMedidor());
		instalacionBD.setRamal(request.getRamal());
		instalacionesRep.save(instalacionBD);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		
		
	}

	private Long getOrden(ConfiguracionRuta request) {
		if(null == request.getInstalacionAnterior()){
			Instalacion primeraInstalacion = instalacionesRep.findFirstOrderByOrden().stream().findFirst().orElseThrow(()->new ExcepcionNegocio("No existen instalaciones para ese ramal."));
			return Long.valueOf(Math.round(primeraInstalacion.getOrden() / 2));
		}
		Instalacion instalacionAnterior = instalacionesRep.findOne(request.getInstalacionAnterior());
		if(null == instalacionAnterior){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE,request.getInstalacionAnterior()));
		}
		if(!request.getRamal().equals(instalacionAnterior.getRamal())){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_RAMAL_DIFERENTE,request.getInstalacion(), instalacionAnterior.getNumeroInstalacion()));
		}
		Instalacion instalacionSiguiente = instalacionesRep.findNextByOrden(instalacionAnterior.getOrden()).stream().findFirst().orElse(null);
		if(null == instalacionSiguiente || instalacionSiguiente.getOrden().equals(SIN_ORDEN)){
			if(instalacionAnterior.getOrden().equals(SIN_ORDEN)){
				Optional<Instalacion> ultimaInstalacion = instalacionesRep.findLastByOrden().stream().findFirst();
				return ultimaInstalacion.map(Instalacion::getOrden).orElse(SIN_ORDEN);
			}
			return instalacionAnterior.getOrden() + 100L;
		}
		return Long.valueOf(Math.round((instalacionSiguiente.getOrden() - instalacionAnterior.getOrden())/ 2) + instalacionAnterior.getOrden());
	}

	public List<UsuarioRamal> consultarUsuarioSistema() {
		return usuarioRamalRep.findAll().stream().filter(item-> null == item.getFechaFinal()).collect(Collectors.toList());
	}

	public MensajeResponse actualizarUsuarioSistema(UsuarioRamalRequest request) {
		UsuarioRamalPK usuarioPK = new UsuarioRamalPK(request.getUsuarioActual(), request.getCodigoRamal(), request.getFechaInicial());
		UsuarioRamal usuarioActual = usuarioRamalRep.findOne(usuarioPK);
		if(null == usuarioActual){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.USUARIO_OPERACION_NO_ENCONTRADO);
		}
		usuarioActual.setFechaFinal(new Date());
		usuarioRamalRep.save(usuarioActual);
		UsuarioRamal usuarioNuevo = new UsuarioRamal();
		usuarioNuevo.setUsuarioRamalPK(new UsuarioRamalPK(request.getUsuarioNuevo(), request.getCodigoRamal(), new Date()));
		usuarioRamalRep.save(usuarioNuevo);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}


}
