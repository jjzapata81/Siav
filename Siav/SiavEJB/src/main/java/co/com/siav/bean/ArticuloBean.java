package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.bean.builders.ArticuloBuilder;
import co.com.siav.entities.Articulo;
import co.com.siav.entities.Kardex;
import co.com.siav.repositories.IRepositoryArticulo;
import co.com.siav.repositories.IRepositoryTarifas;
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
	private IRepositoryTarifas tarifasRep;
	
	@Inject
	private KardexBean kardexBean;

	public MensajeResponse crear(ArticuloRequest request) {
		MensajeResponse validar = validar(request);
		if(validar!=null){
			return validar;
		}
		try{
			Articulo articulo = ArticuloBuilder.create(request);
			articuloRep.save(articulo);
			kardexBean.grabarArticulo(articulo);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

	public List<ArticuloResponse> consultarEntradas() {
		return articuloRep.findByActivo(Constantes.SI).stream().map(this::convert).collect(Collectors.toList());
	}
	
	public List<ArticuloResponse> consultar() {
		return articuloRep.findAll().stream().map(this::convert).collect(Collectors.toList());
	}

	public List<String> consultarNombres() {
		return articuloRep.findAll().stream().map(Articulo::getNombre).collect(Collectors.toList());
	}

	public MensajeResponse actualizar(ArticuloRequest request) {
		Articulo articuloBD = articuloRep.findOne(request.getCodigo());
		if(articuloBD != null){
			try{
				articuloRep.save(ArticuloBuilder.update(articuloBD, request));
				return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
			}catch(Exception e){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
			}
		}else{
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ARTICULO_NO_EXISTE);
		}
		
	}

	public List<ArticuloResponse> consultarSalidas() {
		return articuloRep.findByActivo(Constantes.SI).stream().filter(articulo-> !articulo.getNombre().trim().equals(Constantes.MEDIDOR))
				.map(this::transform).collect(Collectors.toList());
	}
	
	private MensajeResponse validar(ArticuloRequest request) {
		if(request.getCodigo() != null && articuloRep.exists(request.getCodigo())){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ARTICULO_EXISTE);
		}
		if(request.getNombre() != null && !articuloRep.findByNombre(request.getNombre().trim()).isEmpty()){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ARTICULO_NOMBRE_DUPLICADO);
		}
		if(request.getCodigoContable() != null && tarifasRep.exists(request.getCodigoContable().trim())){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ARTICULO_CODIGO_CONTABLE);
		}
		if(request.getCodigoContable() != null && articuloRep.findByCodigoContable(request.getCodigoContable().trim())!=null){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_ARTICULO_CODIGO_CONTABLE_DUPLICADO);
		}
		return null;
	}
	
	private ArticuloResponse transform(Articulo articulo){
		Kardex kardex = kardexBean.getKardex(articulo.getCodigo());
		return ArticuloBuilder.toResponse(articulo, kardex);
	}
	
	private ArticuloResponse convert(Articulo articulo){
		Kardex kardex = kardexBean.getKardex(articulo.getCodigo());
		return ArticuloBuilder.convert(articulo, kardex);
	}

}
