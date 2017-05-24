package co.com.siav.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.dto.ExcesoDTO;
import co.com.siav.dto.RangoConsumosDTO;
import co.com.siav.entities.Exceso;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryExcesos;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class ExcesosBean {
	
	@Inject
	private IRepositoryExcesos excesosRep;

	public MensajeResponse guardar(ExcesoDTO request) {
//		rangoExcesoEsValido(request);
		Exceso excesoBD = consultarExceso(request.getCodigo());
		BeanUtils.copyProperties(request, excesoBD);
		excesoBD.setFechaCreacion(new Date());
		excesosRep.save(excesoBD);
		return new MensajeResponse("Se ha actualizado el exceso de manera correcta.");
	}

	public List<Exceso> consultar() {
		return excesosRep.findAll();
	}
	
	private Exceso consultarExceso(String codigo) {
		return excesosRep.findByCodigo(codigo);
	}
	
	private void rangoExcesoEsValido(ExcesoDTO exceso) {
		List<Exceso> excesos = consultar();
		excesos.stream().forEach(excesoBD -> estaEnRango(excesoBD, exceso.getLimInicial(), exceso.getLimFinal()));
	}

	private void estaEnRango(Exceso excesoBD, Long limInicial, Long limFinal) {
		if(excesoBD.getLimInicial() <= limInicial  && limInicial <= excesoBD.getLimFinal()){
			throw new ExcepcionNegocio("El límite inicial está dentro de un rango existente.");
		}
		
		if(excesoBD.getLimInicial() <= limFinal  && limFinal <= excesoBD.getLimFinal()){
			throw new ExcepcionNegocio("El límite final está dentro de un rango existente.");
		}
	}

	public MensajeResponse editarRango(RangoConsumosDTO request) {
		request.getExcesos().stream().forEach(exceso -> {
			actualizarRango(exceso);
		});
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}

	private void actualizarRango(ExcesoDTO exceso) {
		Exceso excesoBD = excesosRep.findByCodigo(exceso.getCodigo());
		excesoBD.setLimInicial(exceso.getLimInicial());
		excesoBD.setLimFinal(exceso.getLimFinal());
		excesosRep.save(excesoBD);
	}

}
