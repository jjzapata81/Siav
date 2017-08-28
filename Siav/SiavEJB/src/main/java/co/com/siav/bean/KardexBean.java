package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
	
	private void crearDetalle(MaterialDetalleRequest requestDetalle, Long codigo, String tipo, boolean esEntrada){
		Kardex kardex = new Kardex();
		kardex.setTipo(tipo);
		kardex.setCodArticulo(requestDetalle.getArticulo().getCodigo());
		if(esEntrada){
			kardex.setCodEntrada(codigo);
			kardex.setCantidadEntrada(requestDetalle.getCantidad());
			kardex.setPrecioCompra(requestDetalle.getPrecio());
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
