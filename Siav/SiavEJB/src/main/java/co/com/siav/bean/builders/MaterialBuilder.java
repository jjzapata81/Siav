package co.com.siav.bean.builders;

import co.com.siav.entities.Material;
import co.com.siav.request.MaterialDetalleRequest;

public final class MaterialBuilder {
	
	private MaterialBuilder(){
		super();
	}
	
	public static Material crear(MaterialDetalleRequest item, Long ciclo, Long idSalida, Long instalacion) {
		Material material = new Material();
		material.setCiclo(ciclo);
		material.setCodigo(item.getArticulo().getCodigoContable());
		material.setDescripcion(item.getArticulo().getNombre());
		material.setIdSalida(idSalida);
		material.setValorSinIva(item.getPrecio());
		material.setInstalacion(instalacion);
		material.setValorIva(item.getArticulo().getPorcentajeIva());
		material.setCantidad(item.getCantidad());
		return material;
			
	}
	
	public static Material actualizar(Material material, MaterialDetalleRequest item){
		material.setValorSinIva(material.getValorSinIva() + item.getPrecio());
		material.setCantidad(material.getCantidad() + item.getCantidad());
		return material;
	}

}
