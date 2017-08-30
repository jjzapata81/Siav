package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Articulo;
import co.com.siav.entities.Kardex;
import co.com.siav.repositories.IRepositoryKardex;
import co.com.siav.repositories.IRepositoryMaestros;
import co.com.siav.request.MaterialDetalleRequest;
import co.com.siav.utils.Constantes;


@Stateless
public class KardexBean {
	
	@Inject
	private IRepositoryKardex kardexRep;
	
	@Inject
	private IRepositoryMaestros maestrosRep;
	
	public void grabarEntrada(List<MaterialDetalleRequest> detalles, Long codigo) {
		detalles.stream().forEach(item-> crearDetalle(item, codigo, getTipo(Constantes.ENTRADA), true));
	}
	
	public void grabarSalida(List<MaterialDetalleRequest> detalles, Long codigo) {
		detalles.stream().forEach(item-> crearDetalle(item, codigo, getTipo(Constantes.SALIDA), false));
	}
	
	public void grabarArticulo(Articulo articulo) {
		Kardex kardex = new Kardex();
		kardex.setCodArticulo(articulo.getCodigo());
		kardex.setTipo(getTipo(Constantes.INICIAL));
		kardex.setValorizado(true);
		kardexRep.save(kardex);
		
	}
	
	private void crearDetalle(MaterialDetalleRequest requestDetalle, Long codigo, String tipo, boolean esEntrada){
		Kardex kardex = new Kardex();
		kardex.setTipo(tipo);
		kardex.setCodArticulo(requestDetalle.getArticulo().getCodigo());
		if(esEntrada){
			Kardex kardexAnterior = kardexRep.findFirstByCodArticuloOrderByCodigoDesc(requestDetalle.getArticulo().getCodigo());
			kardex.setCodEntrada(codigo);
			kardex.setCantidadEntrada(requestDetalle.getCantidad());
			kardex.setSaldoAnterior(kardexAnterior.getSaldoActual());
			kardex.setSaldoActual(kardex.getCantidadEntrada() + kardex.getSaldoAnterior());
			kardex.setPrecioCompra(requestDetalle.getPrecio());
			kardex.setIvaPrecioCompra(kardex.getPrecioCompra() * requestDetalle.getArticulo().getPorcentajeIva());
		}else{
			kardex.setCodSalida(codigo);
			kardex.setCantidadSalida(requestDetalle.getCantidad());
			kardex.setPrecioComercial(requestDetalle.getPrecio());
		}
		kardexRep.save(kardex);
	}
	
	private String getTipo(String tipo) {
		return maestrosRep.findByGrupoAndValor(Constantes.TIPO_KARDEX, tipo).getCodigo();
	}

}
