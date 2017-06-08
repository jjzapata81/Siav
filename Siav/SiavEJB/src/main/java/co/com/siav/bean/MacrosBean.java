package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Macromedidor;
import co.com.siav.repositories.IRepositoryMacros;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class MacrosBean {
	
	@Inject
	private IRepositoryMacros macrosRep;

	public List<Macromedidor> consultar() {
		return macrosRep.findAll();
	}

	public MensajeResponse guardar(Macromedidor macro) {
		try{
			macrosRep.save(macro);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	public MensajeResponse actualizar(Macromedidor macro) {
		try{
			macrosRep.save(macro);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}
	
	
	
}
