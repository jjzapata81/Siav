package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.entities.Articulo;
import co.com.siav.entities.Kardex;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryArticulo;
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
	
	@Inject
	private IRepositoryArticulo articuloRep;
	
	public void grabarEntrada(List<MaterialDetalleRequest> detalles, Long codigo, Long ciclo) {
		detalles.stream().forEach(item-> crearEntrada(item, codigo, ciclo));
	}
	
	public void grabarSalida(List<MaterialDetalleRequest> detalles, Long codigo, Long ciclo) {
		detalles.stream().forEach(item-> crearSalida(item, codigo, ciclo));
	}
	
	public void grabarArticulo(Articulo articulo) {
		Kardex kardex = new Kardex();
		kardex.setCodArticulo(articulo.getCodigo());
		kardex.setTipo(getTipo(Constantes.INICIAL));
		kardexRep.save(kardex);
	}
	
	private void crearEntrada(MaterialDetalleRequest requestDetalle, Long codigo, Long ciclo){
		Kardex kardexAnterior = getAnterior(requestDetalle.getArticulo().getCodigo());
		Kardex kardex = new Kardex();
		kardex.setCiclo(ciclo);
		kardex.setTipo(getTipo(Constantes.ENTRADA));
		kardex.setCodArticulo(requestDetalle.getArticulo().getCodigo());
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
	
	private void crearSalida(MaterialDetalleRequest requestDetalle, Long codigo, Long ciclo){
		Kardex kardexAnterior = getAnterior(requestDetalle.getArticulo().getCodigo());
		Kardex kardex = new Kardex();
		kardex.setCiclo(ciclo);
		kardex.setTipo(getTipo(Constantes.SALIDA));
		kardex.setCodArticulo(requestDetalle.getArticulo().getCodigo());
		kardex.setCodSalida(codigo);
		kardex.setCantidadSalida(requestDetalle.getCantidad());
		kardex.setSaldoAnterior(kardexAnterior.getSaldoActual());
		kardex.setSaldoActual(kardex.getSaldoAnterior() - kardex.getCantidadSalida());
		kardex.setPrecioComercial(requestDetalle.getPrecio());
		kardex.setPrecioUnitario(kardexAnterior.getPrecioUnitario());
		kardex.setIvaPrecioUnitario(kardexAnterior.getIvaPrecioUnitario());
		kardexRep.save(kardex);
	}
	
	private String getTipo(String tipo) {
		return maestrosRep.findByGrupoAndValor(Constantes.TIPO_KARDEX, tipo).getCodigo();
	}

	public Kardex getKardex(Long codigo) {
		Kardex kardex = kardexRep.findFirstByCodArticuloOrderByCodigoDesc(codigo);
		if(kardex == null){
			throw new ExcepcionNegocio(Constantes.getMensaje(Constantes.ERR_ARTICULO_SIN_KARDEX, codigo));
		}
		return kardex;
	}
	
	private Kardex getAnterior(Long codigoArticulo) {
		Kardex kardex = getKardex(codigoArticulo);
		if(kardex==null){
			kardex = new Kardex();
		}
		return kardex;
	}

	public void cerrar(Long ciclo) {
		articuloRep.findByActivo(Constantes.SI).stream().forEach(item -> buscarKardex(item, ciclo));
	}
	
	private void buscarKardex(Articulo articulo, Long ciclo) {
		Kardex kardex = getKardex(articulo.getCodigo());
		duplicarRegistro(kardex, getTipo(Constantes.FINAL), ciclo);
		duplicarRegistro(kardex, getTipo(Constantes.INICIAL), ciclo + 1L);
	}

	private void duplicarRegistro(Kardex kardex, String tipo, Long ciclo){
		Kardex nuevoKardex = new Kardex();
		BeanUtils.copyProperties(kardex, nuevoKardex, "codigo");
		nuevoKardex.setTipo(tipo);
		nuevoKardex.setCantidadEntrada(0L);
		nuevoKardex.setCantidadSalida(0L);
		nuevoKardex.setSaldoAnterior(kardex.getSaldoActual());
		nuevoKardex.setCiclo(ciclo);
		kardexRep.save(nuevoKardex);
	}
}
