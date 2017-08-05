package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Instalacion;
import co.com.siav.entities.Usuario;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryConsumos;
import co.com.siav.repositories.IRepositoryCreditoMaestro;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryUsuarios;
import co.com.siav.request.UsuarioRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.InstalacionInfo;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.UsuarioInfo;
import co.com.siav.utils.Constantes;

@Stateless
public class UsuariosBean {
	
	@Inject
	private IRepositoryUsuarios usuariosRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryConsumos consumosRep;
	
	@Inject
	private IRepositoryCreditoMaestro creditosRep;

	public Usuario consultarPorCedula(String cedula) {
		 Usuario usuarioBD = usuariosRep.findOne(cedula);
		 if(usuarioBD == null){
			 throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.USUARIO_NO_EXISTE, cedula));	
			}
		 return usuarioBD;
	}

	public MensajeResponse guardar(Usuario request) {
		try{
			if(null != usuariosRep.findOne(request.getCedula())){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.USUARIO_EXISTE, request.getCedula()));
			}
			usuariosRep.save(request);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, e.getMessage());
		}
	}

	public MensajeResponse actualizar(Usuario request) {
		try{
			Usuario usuario = usuariosRep.findOne(request.getCedula());
			usuario.setCedula(request.getCedula());
			usuario.setNombres(request.getNombres());
			usuario.setApellidos(request.getApellidos());
			usuario.setDireccion(request.getDireccion());
			usuario.setTelefono(request.getTelefono());
			usuario.setEmail(request.getEmail());
			usuario.setCelular(request.getCelular());
			usuario.setCiudad(request.getCiudad());
			usuario.setEnvioMail(request.getEnvioMail());
			usuariosRep.save(usuario);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, e.getMessage());
		}
	}

	public List<Usuario> consultarPorNombre(UsuarioRequest request) {
		return usuariosRep.findByNombresLikeAndApellidosLike(request.getNombres(), request.getApellidos());
	}

	public UsuarioInfo consultarInfo(String cedula) {
		return new UsuarioInfo(instalacionesRep.findByUsuarioCedula(cedula).stream().map(this::transform).collect(Collectors.toList()));
	}
	
	private InstalacionInfo transform(Instalacion instalacion){
		return new InstalacionInfo(instalacion.getNumeroInstalacion(),
				creditosRep.findByInstalacion(instalacion.getNumeroInstalacion()), 
				facturasRep.findTop6ByNumeroInstalacionOrderByCicloDesc(instalacion.getNumeroInstalacion()),
				consumosRep.findTop6ByInstalacionNumeroInstalacionOrderByIdCicloDesc(instalacion.getNumeroInstalacion()));
	}
	
}
