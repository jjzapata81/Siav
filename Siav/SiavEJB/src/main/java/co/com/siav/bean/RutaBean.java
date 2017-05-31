package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.dto.ConfiguracionRuta;
import co.com.siav.entities.Instalacion;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.response.MensajeResponse;

@Stateless
public class RutaBean {
	
	private final static Long SIN_ORDEN = 99999L;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;	

	public List<ConfiguracionRuta> consultar() {
		return instalacionesRep.findByOrden(SIN_ORDEN).stream().map(this::transform).collect(Collectors.toList());
	}
	
	public ConfiguracionRuta consultarPorNumero(Long numeroInstalacion) {
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
		// TODO Auto-generated method stub
		return null;
	}


}
