package co.com.siav.bean;

import javax.inject.Inject;

import co.com.siav.entities.NumeroFactura;
import co.com.siav.entities.Rango;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryRangos;
import co.com.siav.utils.Constantes;



public class Numerador {
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryRangos rangosRep;

	private NumeroFactura numeroFactura;
	
	public void prepararNumeroFactura(Long ciclo, int cantidadInstalaciones) {
		Rango rangoFacturacion = getRangoFacturacion();
		Long ultimaFactura = facturasRep.findNumeroFacturaByCiclo(ciclo-1L);
		Long numeroInicial = ultimaFactura > rangoFacturacion.getLimiteInicial() ? ultimaFactura : rangoFacturacion.getLimiteInicial();
		numeroFactura = new NumeroFactura(numeroInicial , rangoFacturacion.getLimiteFinal());
		if(cantidadInstalaciones > numeroFactura.getNumerosDisponibles()){
			Rango siguienteRango = rangosRep.findSiguienteRango(rangoFacturacion.getLimiteFinal());
			if(null == siguienteRango){
				throw new ExcepcionNegocio(Constantes.ERR_SIN_RANGO);
			}
			numeroFactura.setConsecutivoSiguienteRango(siguienteRango.getLimiteInicial());
		}

	}
	
	private Rango getRangoFacturacion() {
		Rango rango = rangosRep.findByEstado(Constantes.VIGENTE);
		if (null != rango){
			return rango;
		}
		rango = rangosRep.findNextDisponible();
		if(null!=rango){
			rango.setEstado(Constantes.VIGENTE);
			rangosRep.save(rango);
			return rango;
		}
		throw new ExcepcionNegocio(Constantes.ERR_SIN_RANGO);
	}

	public NumeroFactura getNumeroFactura() {
		return numeroFactura;
	}

}
