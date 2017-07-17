package co.com.siav.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.UsuarioSistema;
import co.com.siav.repositories.IRepositoryUsuarioSistema;
import co.com.siav.utils.Constantes;

@Stateless
public class AutorizacionBean {
	
	@Inject
	private IRepositoryUsuarioSistema usuarioRep;

	public boolean get(String usuario, String accion) {
		UsuarioSistema usuarioSistema = usuarioRep.findByNombreUsuario(usuario);
		if(usuarioSistema == null){
			return false;
		}
		return getAutorizados(accion).contains(usuarioSistema.getPerfil().getCodigoPerfil());
	}
	
	private List<Long> getAutorizados(String accion){
		if (Constantes.ACCION_CUENTAS_VENCIDAS.equals(accion)){
			return Arrays.asList(1L);
		}
		return new ArrayList<Long>();
	}

}
