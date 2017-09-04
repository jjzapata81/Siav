package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Articulo;
import co.com.siav.entities.Kardex;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryArticulo;
import co.com.siav.request.ArticuloRequest;
import co.com.siav.response.ArticuloResponse;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class ArticuloBean {
	
	@Inject
	private IRepositoryArticulo articuloRep;
	
	@Inject
	private KardexBean kardexBean;

	public MensajeResponse crear(ArticuloRequest request) {
		if(request.getNombre() != null && !articuloRep.findByNombre(request.getNombre().trim()).isEmpty()){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ARTICULO_NOMBRE_DUPLICADO);
		}
		try{
			Long codigo = articuloRep.findMaxArticulo() + 1L;
			Articulo articulo = new Articulo();
			articulo.setCodigo(codigo);
			articulo.setNombre(request.getNombre().trim());
			articulo.setPorcentajeIva(request.getPorcentajeIva()/100);
			articulo.setTieneIva(request.getTieneIva());
			articulo.setObservacion(request.getObservacion());
			articulo.setUnidad(request.getUnidad().getCodigo());
			articulo.setActivo(true);
			articuloRep.save(articulo);
			kardexBean.grabarArticulo(articulo);
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

	public List<ArticuloResponse> consultarSalidas() {
		return articuloRep.findByActivo(Constantes.SI).stream().filter(articulo-> !articulo.getNombre().trim().equals(Constantes.MEDIDOR))
				.map(this::transform).collect(Collectors.toList());
	}
	
	private ArticuloResponse transform(Articulo articulo){
		Kardex kardex = kardexBean.getKardex(articulo.getCodigo());
		if(kardex == null){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_ARTICULO_SIN_KARDEX, articulo.getNombre()));
		}
		ArticuloResponse response = new ArticuloResponse();
		response.setCodigo(articulo.getCodigo());
		response.setNombre(articulo.getNombre());
		response.setUnidad(articulo.getUnidad());
		response.setCantidadDisponible(kardex.getSaldoActual());
		response.setPrecioUnitario(kardex.getPrecioUnitario());
		response.setIvaUnitario(kardex.getIvaPrecioUnitario());
		response.setPrecioComercial(kardex.getPrecioComercial());
		return response;
	}
}
