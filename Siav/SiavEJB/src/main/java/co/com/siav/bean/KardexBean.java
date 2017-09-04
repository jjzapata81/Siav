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
		detalles.stream().forEach(item-> crearEntrada(item, codigo));
	}
	
	public void grabarSalida(List<MaterialDetalleRequest> detalles, Long codigo) {
		detalles.stream().forEach(item-> crearSalida(item, codigo));
	}
	
	public void grabarArticulo(Articulo articulo) {
		Kardex kardex = new Kardex();
		kardex.setCodArticulo(articulo.getCodigo());
		kardex.setTipo(getTipo(Constantes.INICIAL));
		kardexRep.save(kardex);
	}
	
	private void crearEntrada(MaterialDetalleRequest requestDetalle, Long codigo){
		Kardex kardex = new Kardex();
		kardex.setTipo(getTipo(Constantes.ENTRADA));
		kardex.setCodArticulo(requestDetalle.getArticulo().getCodigo());
		Kardex kardexAnterior = getAnterior(requestDetalle.getArticulo().getCodigo());
		kardex.setCodEntrada(codigo);
		kardex.setCantidadEntrada(requestDetalle.getCantidad());
		kardex.setSaldoAnterior(kardexAnterior.getSaldoActual());
		kardex.setSaldoActual(kardex.getCantidadEntrada() + kardex.getSaldoAnterior());
		kardex.setPrecioCompra(requestDetalle.getPrecio());
		kardex.setIvaPrecioCompra(requestDetalle.getValorIva());
		Double precioUnitario = ((kardexAnterior.getSaldoActual() * kardexAnterior.getPrecioUnitario()) + kardex.getPrecioCompra())/kardex.getSaldoActual();
		kardex.setPrecioUnitario(precioUnitario);
		kardex.setIvaPrecioUnitario(precioUnitario * requestDetalle.getArticulo().getPorcentajeIva());
		kardexRep.save(kardex);
	}
	
	private void crearSalida(MaterialDetalleRequest requestDetalle, Long codigo){
		Kardex kardexAnterior = getAnterior(requestDetalle.getArticulo().getCodigo());
		Kardex kardex = new Kardex();
		kardex.setTipo(getTipo(Constantes.SALIDA));
		kardex.setCodArticulo(requestDetalle.getArticulo().getCodigo());
		kardex.setCodSalida(codigo);
		kardex.setCantidadSalida(requestDetalle.getCantidad());
		kardex.setSaldoAnterior(kardexAnterior.getSaldoActual());
		kardex.setSaldoActual(kardex.getSaldoAnterior() - kardex.getCantidadSalida());
		kardex.setPrecioComercial(requestDetalle.getPrecio());
		kardexRep.save(kardex);
	}
	
	private String getTipo(String tipo) {
		return maestrosRep.findByGrupoAndValor(Constantes.TIPO_KARDEX, tipo).getCodigo();
	}

	public Kardex getKardex(Long codigo) {
		return kardexRep.findFirstByCodArticuloOrderByCodigoDesc(codigo);
	}
	
	private Kardex getAnterior(Long codigo) {
		Kardex kardex = kardexRep.findFirstByCodArticuloOrderByCodigoDesc(codigo);
		if(kardex==null){
			kardex = new Kardex();
		}
		return kardex;
	}
}
