package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.entities.Perfil;
import co.com.siav.entities.UsuarioSistema;
import co.com.siav.repositories.IRepositoryPerfiles;
import co.com.siav.repositories.IRepositoryUsuarioSistema;
import co.com.siav.request.CambioClaveRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.PerfilResponse;
import co.com.siav.response.UsuarioSistemaResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class UsuarioSistemaBean {
	
	@Inject
	private IRepositoryUsuarioSistema usuarioRep;
	
	@Inject
	private IRepositoryPerfiles perfilesRep;

	public MensajeResponse crear(UsuarioSistema usuario) {
		UsuarioSistema usuarioBD = usuarioRep.findOne(usuario.getId());
		if(null == usuarioBD){
			usuarioBD = new UsuarioSistema();
		}
		BeanUtils.copyProperties(usuario, usuarioBD);
		usuarioBD.setNombreUsuario(crearNombreUsuario(usuarioBD));
		usuarioBD.setPerfil(usuario.getPerfil());
		usuarioRep.save(usuarioBD);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	private String crearNombreUsuario(UsuarioSistema usuarioBD) {
		String[] nombres = usuarioBD.getNombres().split(Constantes.ESPACIO);
		String[] apellidos = usuarioBD.getApellidos().split(Constantes.ESPACIO);
		boolean noExiste = true;
		int posicion = 0;
		while(noExiste){
			posicion++;
			noExiste = null != usuarioRep.findByNombreUsuario(nombres[0].substring(0, posicion).toLowerCase() + apellidos[0].toLowerCase());
			
		}
		return nombres[0].substring(0, posicion).toLowerCase() + apellidos[0].toLowerCase();
	}

	public List<UsuarioSistemaResponse> consultarUsuarios() {
		return usuarioRep.findAll().stream().map(this::transform).collect(Collectors.toList());
	}

	public void cambioEstado(UsuarioSistema usuario) {
		UsuarioSistema usuarioBD = usuarioRep.findOne(usuario.getId());
		usuarioBD.setActivo(usuario.getActivo());
		usuarioRep.save(usuarioBD);
		
	}

	public MensajeResponse cambiarClave(CambioClaveRequest request) {
		UsuarioSistema usuarioBD = usuarioRep.findByNombreUsuario(request.getUsuario());
		if(null == usuarioBD || !usuarioBD.getPassword().equals(request.getPassword())){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.CAMBIO_CLAVE_ERR);
		}
		usuarioBD.setPassword(request.getNuevoPassword());
		usuarioRep.save(usuarioBD);
		return new MensajeResponse(Constantes.CAMBIO_CLAVE_OK);
	}
	
	private UsuarioSistemaResponse transform(UsuarioSistema usuarioBD){
		UsuarioSistemaResponse response = new UsuarioSistemaResponse();
		response.setId(usuarioBD.getId());
		response.setActivo(usuarioBD.getActivo());
		response.setApellidos(usuarioBD.getApellidos());
		response.setNombres(usuarioBD.getNombres());
		response.setNombreUsuario(usuarioBD.getNombreUsuario());
		response.setPerfil(transformPerfil(usuarioBD.getPerfil()));
		return response;
	}

	public List<PerfilResponse> consultarPerfiles() {
		return perfilesRep.findAll().stream().map(this::transformPerfil).collect(Collectors.toList());
	}
	
	private PerfilResponse transformPerfil(Perfil perfilBD){
		PerfilResponse response = new PerfilResponse();
		response.setCodigoPerfil(perfilBD.getCodigoPerfil());
		response.setNombrePerfil(perfilBD.getNombrePerfil());
		return response;
		
	}

	public MensajeResponse actualizar(UsuarioSistema usuario) {
		UsuarioSistema usuarioBD = usuarioRep.findOne(usuario.getId());
		if(null == usuarioBD){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.USUARIO_SISTEMA_NO_EXISTE, usuario.getId()));
		}
		usuarioBD.setNombres(usuario.getNombres());
		usuarioBD.setApellidos(usuario.getApellidos());
		usuarioBD.setPerfil(usuario.getPerfil());
		usuarioRep.save(usuarioBD);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

}
