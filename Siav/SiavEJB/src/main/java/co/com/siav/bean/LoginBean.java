package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.dto.LoginDTO;
import co.com.siav.entities.Grupo;
import co.com.siav.entities.Perfil;
import co.com.siav.entities.Recurso;
import co.com.siav.entities.RecursoPerfil;
import co.com.siav.entities.UsuarioSistema;
import co.com.siav.repositories.IRepositoryPerfiles;
import co.com.siav.repositories.IRepositoryUsuarioSistema;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.LoginResponse;
import co.com.siav.response.MenuItem;
import co.com.siav.response.MenuResponse;
import co.com.siav.response.SubMenuItem;
import co.com.siav.response.UsuarioMovilResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class LoginBean {
	
	@Inject
	private IRepositoryPerfiles perfilesRep;
	
	@Inject
	private IRepositoryUsuarioSistema userRep;

	public LoginResponse ingresar(LoginDTO request) {
		UsuarioSistema usuarioSistema = userRep.findByNombreUsuario(request.getUser());
		if(null == usuarioSistema || !usuarioSistema.getPassword().equals(request.getPass().trim())){
			return new LoginResponse(EstadoEnum.ERROR, Constantes.USUARIO_INCORRECTO);
		}
		if(!usuarioSistema.getActivo()){
			return new LoginResponse(EstadoEnum.ERROR, Constantes.USUARIO_INACTIVO);
		}
		LoginResponse response =  new LoginResponse(Constantes.getMensaje(Constantes.BIENVENIDO, usuarioSistema.getNombreCompleto()));
		response.setMenu(crearMenu(usuarioSistema.getPerfil().getRecursoPerfil()));
		response.setNombreUsuario(usuarioSistema.getNombreCompleto());
		return response;
	}

	private List<MenuResponse> crearMenu(List<RecursoPerfil> recursosperfil) {
		List<Recurso> recursos = recursosperfil.stream().map(item -> {return item.getRecurso();}).collect(Collectors.toList());
		List<Grupo> grupos = recursos.stream().map(Recurso::getGrupo).distinct().collect(Collectors.toList());
		return grupos.stream().map(grupo -> {
				List<Recurso> recursoPorGrupo = recursos.stream().filter(recurso -> grupo.getNumeroGrupo().equals(recurso.getGrupo().getNumeroGrupo())).collect(Collectors.toList());
				return crearRecurso(recursoPorGrupo, grupo);
			}).collect(Collectors.toList());
	}
	
	private MenuResponse crearRecurso(List<Recurso> recursos, Grupo grupo){
		MenuResponse menu = new MenuResponse();
		menu.setOrden(grupo.getNumeroGrupo());
		menu.setGrupo(grupo.getNombre());
		menu.setRecursos(crearSubMenu(recursos));
		return menu;
	}

	private List<MenuItem> crearSubMenu(List<Recurso> recursos) {
		List<Long> relaciones = recursos.stream().mapToLong(Recurso::getRelacion).boxed().distinct().collect(Collectors.toList());
		return relaciones.stream().map(relacion -> {
				List<Recurso> recursoPorRelacion = recursos.stream().filter(recurso -> relacion.equals(recurso.getRelacion())).collect(Collectors.toList());
				return crearSubMenuItem(recursoPorRelacion, relacion);
			}).collect(Collectors.toList());
	}
	
	private MenuItem crearSubMenuItem(List<Recurso> recursos, Long relacion){
		MenuItem item = new MenuItem();
		if(recursos.size()==1){
			item.setAccion(recursos.get(0).getAccion());
			item.setTitulo(recursos.get(0).getTitulo());
			item.setTieneSubMenu(false);
			item.setOrden(recursos.get(0).getNumeroRecurso());
			return item;
		}
		Recurso subGrupo = recursos.stream().filter(recurso-> recurso.getTieneSubmenu()).findAny().orElse(null);
		List<Recurso> items = recursos.stream().filter(recurso->!recurso.getTieneSubmenu()).collect(Collectors.toList());
		item.setAccion(subGrupo.getAccion());
		item.setTitulo(subGrupo.getTitulo());
		item.setTieneSubMenu(true);
		item.setOrden(subGrupo.getNumeroRecurso());
		item.setSubmenus(items.stream().map(this::transform).collect(Collectors.toList()));
		return item;
	}
	
	private SubMenuItem transform(Recurso recurso){
		SubMenuItem item = new SubMenuItem();
		item.setAccion(recurso.getAccion());
		item.setTitulo(recurso.getTitulo());
		item.setOrden(recurso.getNumeroRecurso());
		return item;
	}

	public UsuarioMovilResponse validarUsuarioMovil(LoginDTO request) {
		UsuarioSistema usuarioSistema = userRep.findByNombreUsuario(request.getUser());
		UsuarioMovilResponse response = new UsuarioMovilResponse();
		response.setUsuarioValido(null != usuarioSistema && usuarioSistema.getPassword().equals(request.getPass().trim()));
		response.setUsuario(request.getUser());
		response.setPassword(request.getPass());
		return response;
	}
	
	public List<Perfil> getRoles(){
		return perfilesRep.findAll();
	}

}
