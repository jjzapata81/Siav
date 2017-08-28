package co.com.siav.bean;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.EntradaMaestro;
import co.com.siav.entities.SalidaMaestro;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryEntradas;
import co.com.siav.repositories.IRepositorySalidas;
import co.com.siav.request.MaterialRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class MaterialesBean {
	
	@Inject
	private IRepositoryEntradas entradasRep;
	
	@Inject
	private IRepositorySalidas salidasRep;
	
	@Inject
	private KardexBean kardexBean;
	
	@Inject
	private IRepositoryCiclos ciclosRep;

	public MensajeResponse crearEntrada(MaterialRequest request) {
		try{
			Long codigo = entradasRep.findMaxEntrada() + 1L;
			Long ciclo = ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO);
			EntradaMaestro entrada = new EntradaMaestro();
			entrada.setCodigo(codigo);
			entrada.setCiclo(ciclo);
			entrada.setCodFacturaCompra(request.getFactura());
			entrada.setCodProveedor(request.getProveedor().getCodigo());
			entrada.setFechaFacturaCompra(request.getFecha());
			entradasRep.save(entrada);
			kardexBean.grabarEntrada(request.getDetalles(), codigo);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

	public MensajeResponse crearSalida(MaterialRequest request) {
		try{
			Long codigo = salidasRep.findMaxSalida() + 1L;
			Long ciclo = ciclosRep.findMaximoCicloPorEstado(Constantes.ABIERTO);
			SalidaMaestro salida = new SalidaMaestro();
			salida.setCodigo(codigo);
			salida.setCiclo(ciclo);
			salida.setCodOrdenSalida(request.getFactura());
			salida.setFechaOrdenSalida(request.getFecha());
			salidasRep.save(salida);
			kardexBean.grabarSalida(request.getDetalles(), codigo);
			return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
	
}
