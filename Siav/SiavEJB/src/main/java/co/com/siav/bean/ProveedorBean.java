package co.com.siav.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Proveedor;
import co.com.siav.repositories.IRepositoryProveedor;
import co.com.siav.request.ProveedorRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.Utilidades;

@Stateless
public class ProveedorBean {
	
	@Inject
	private IRepositoryProveedor proveedorRep;
	

	public MensajeResponse crear(ProveedorRequest request) {
		try{
			if(Utilidades.emailNoValido(request.getCorreo())){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.EMAIL_NO_VALIDO);
			}
			Proveedor proveedor = new Proveedor();
			proveedor.setNit(request.getNit());
			proveedor.setRazonSocial(request.getRazonSocial());
			proveedor.setDireccion(request.getDireccion());
			proveedor.setTelefono(request.getTelefono());
			proveedor.setCorreo(request.getCorreo());
			proveedor.setObservacion(request.getObservacion());
			proveedorRep.save(proveedor);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

	public List<Proveedor> consultar() {
		return proveedorRep.findAll();
	}

	public List<String> consultarNombres() {
		return proveedorRep.findAll().stream().map(Proveedor::getRazonSocial).collect(Collectors.toList());
	}

	public MensajeResponse actualizar(ProveedorRequest request) {
		Proveedor proveedorBD = proveedorRep.findOne(request.getCodigo());
		if(proveedorBD == null){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ERR_PROVEEDOR_NO_EXISTE);
		}
		try{
			if(Utilidades.emailNoValido(request.getCorreo())){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.EMAIL_NO_VALIDO);
			}
			proveedorBD.setNit(request.getNit());
			proveedorBD.setRazonSocial(request.getRazonSocial());
			proveedorBD.setDireccion(request.getDireccion());
			proveedorBD.setTelefono(request.getTelefono());
			proveedorBD.setCorreo(request.getCorreo());
			proveedorBD.setObservacion(request.getObservacion());
			proveedorRep.save(proveedorBD);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
}
