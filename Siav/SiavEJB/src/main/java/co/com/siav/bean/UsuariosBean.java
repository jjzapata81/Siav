package co.com.siav.bean;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Usuario;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryUsuarios;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class UsuariosBean {
	
	@Inject
	private IRepositoryUsuarios usuariosRep;

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
			usuariosRep.save(usuario);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, e.getMessage());
		}
	}
	
}
