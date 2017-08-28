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
		if(request.getNombre() != null && !articuloRep.findByNombre(request.getNombre().trim()).isEmpty()){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ARTICULO_NOMBRE_DUPLICADO);
		}
		try{
			Articulo articulo = new Articulo();
			articulo.setNombre(request.getNombre());
			articulo.setPorcentajeIva(request.getPorcentajeIva());
			articulo.setTieneIva(request.getTieneIva());
			articulo.setObservacion(request.getObservacion());
			articulo.setUnidad(request.getUnidad().getCodigo());
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
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ARTICULO_NO_EXISTE);
		}
		try{
			articuloBD.setNombre(request.getNombre());
			articuloBD.setPorcentajeIva(request.getPorcentajeIva());
			articuloBD.setTieneIva(request.getTieneIva());
			articuloBD.setObservacion(request.getObservacion());
			articuloBD.setUnidad(request.getUnidad().getCodigo());
			articuloBD.setActivo(request.getActivo());
			articuloRep.save(articuloBD);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
}
