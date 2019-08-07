package co.com.siav.bean.builders;

import co.com.siav.entities.Articulo;
import co.com.siav.entities.Kardex;
import co.com.siav.request.ArticuloRequest;
import co.com.siav.response.ArticuloResponse;

public final class ArticuloBuilder {

	private ArticuloBuilder(){
		super();
	}

	public static Articulo create(ArticuloRequest request) {
		Articulo articulo = new Articulo();
		articulo.setCodigo(request.getCodigo());
		articulo.setCodigoContable(request.getCodigoContable().trim());
		articulo.setNombre(request.getNombre().trim());
		articulo.setPorcentajeIva(request.getPorcentajeIva()/100);
		articulo.setTieneIva(request.getTieneIva());
		articulo.setObservacion(request.getObservacion());
		articulo.setUnidad(request.getUnidad().getCodigo());
		articulo.setActivo(true);
		return articulo;
	}

	public static ArticuloResponse toResponse(Articulo articulo, Kardex kardex) {
		ArticuloResponse response = build(articulo, kardex);
		response.setPrecioUnitario(kardex.getPrecioUnitario());
		response.setIvaUnitario(kardex.getIvaPrecioUnitario());
		return response;
	}

	public static ArticuloResponse convert(Articulo articulo, Kardex kardex) {
		ArticuloResponse response = build(articulo, kardex);
		response.setObservacion(articulo.getObservacion());
		response.setActivo(articulo.getActivo());
		return response;
	}
	
	private static ArticuloResponse build(Articulo articulo, Kardex kardex) {
		ArticuloResponse response = new ArticuloResponse();
		response.setCodigo(articulo.getCodigo());
		response.setCodigoContable(articulo.getCodigoContable());
		response.setNombre(articulo.getNombre());
		response.setUnidad(articulo.getUnidad());
		response.setPorcentajeIva(articulo.getPorcentajeIva());
		response.setTieneIva(articulo.getTieneIva());
		response.setCantidadDisponible(kardex.getSaldoActual());
		return response;
	}

	public static Articulo update(Articulo articulo, ArticuloRequest request) {
		articulo.setNombre(request.getNombre());
		articulo.setCodigoContable(request.getCodigoContable().trim());
		articulo.setPorcentajeIva(request.getPorcentajeIva()/100);
		articulo.setTieneIva(request.getTieneIva());
		articulo.setObservacion(request.getObservacion());
		articulo.setUnidad(request.getUnidad().getCodigo());
		articulo.setActivo(request.getActivo());
		return articulo;
	}
}
