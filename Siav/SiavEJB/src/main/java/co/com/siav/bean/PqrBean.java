package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Instalacion;
import co.com.siav.entities.Pqr;
import co.com.siav.entities.UsuarioSistema;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryPqr;
import co.com.siav.repositories.IRepositoryUsuarioSistema;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class PqrBean {
	
	@Inject
	private IRepositoryPqr pqrRep;
	

	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryUsuarioSistema usuarioSistemaRep;

	public List<Pqr> consultarTodo(){
		return pqrRep.findAll();
	}

	public MensajeResponse crear(Pqr request) {
		try{
			UsuarioSistema usuario = usuarioSistemaRep.findByNombreUsuario(request.getUsuario().getNombreUsuario());
			Instalacion instalacion = instalacionesRep.findOne(request.getInstalacion().getNumeroInstalacion());
			request.setInstalacion(instalacion);
			request.setUsuario(usuario);
			pqrRep.save(request);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_CREAR_PQR);
		}
		
	}

}
