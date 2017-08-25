package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Articulo;
import co.com.siav.repositories.IRepositoryArticulo;
import co.com.siav.request.ArticuloRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class ArticuloBean {
	
	@Inject
	private IRepositoryArticulo articuloRep;
	

	public MensajeResponse crear(ArticuloRequest request) {
		try{
			Articulo articulo = new Articulo();
			articulo.setNombre(request.getNombre());
			articulo.setPorcentajeGanancia(request.getPorcentajeGanancia());
			articulo.setPrecioComercial(request.getPrecioComercial());
			articulo.setPrecioInventario(request.getPrecioInventario());
			articulo.setPrecioUnitario(request.getPrecioUnitario());
			articulo.setObservacion(request.getObservacion());
			articulo.setActivo(true);
			articuloRep.save(articulo);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

	public List<Articulo> consultar() {
		return articuloRep.findAll();
	}

	public List<String> consultarNombres() {
		return articuloRep.findAll().stream().map(Articulo::getNombre).collect(Collectors.toList());
	}

	public MensajeResponse actualizar(ArticuloRequest request) {
		Articulo articuloBD = articuloRep.findOne(request.getCodigo());
		if(articuloBD == null){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ARTICULO_NO_EXISTE);
		}
		try{
			articuloBD.setNombre(request.getNombre());
			articuloBD.setPorcentajeGanancia(request.getPorcentajeGanancia());
			articuloBD.setPrecioComercial(request.getPrecioComercial());
			articuloBD.setPrecioInventario(request.getPrecioInventario());
			articuloBD.setPrecioUnitario(request.getPrecioUnitario());
			articuloBD.setObservacion(request.getObservacion());
			articuloBD.setActivo(true);
			articuloRep.save(articuloBD);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
}
