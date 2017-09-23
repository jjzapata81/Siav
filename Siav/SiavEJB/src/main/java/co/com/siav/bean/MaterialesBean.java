package co.com.siav.bean;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.EntradaMaestro;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;
import co.com.siav.entities.SalidaMaestro;
import co.com.siav.entities.Sistema;
import co.com.siav.repositories.IRepositoryEntradas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositorySalidas;
import co.com.siav.repositories.IRepositorySistema;
import co.com.siav.request.MaterialDetalleRequest;
import co.com.siav.request.MaterialRequest;
import co.com.siav.response.EntradaResponse;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.Utilidades;

@Stateless
public class MaterialesBean {
	
	@Inject
	private IRepositoryEntradas entradasRep;
	
	@Inject
	private IRepositorySalidas salidasRep;
	
	@Inject
	private KardexBean kardexBean;
	
	@Inject
	private NovedadesBean novedadesBean;
	
	@Inject
	private CiclosBean ciclosBean;
	
	@Inject
	private IRepositorySistema sistemaRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;

	public EntradaResponse crearEntrada(MaterialRequest request) {
		if(Utilidades.validateDate(request.getFecha())){
			return new EntradaResponse(EstadoEnum.ERROR, Constantes.ERR_FECHA_POSTERIOR);
		}
		EntradaMaestro entradaBD = entradasRep.findByCodFacturaCompraAndFechaFacturaCompra(request.getFactura(), request.getFecha());
		if(entradaBD!=null){
			return new EntradaResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.ERR_ENTRADA_EXISTE, String.valueOf(request.getFactura()), Utilidades.dateToString(request.getFecha())));
		}
		try{
			Long codigo = entradasRep.findMaxEntrada() + 1L;
			Long ciclo = ciclosBean.getPorEstado(Constantes.ABIERTO).getCiclo();
			EntradaMaestro entrada = new EntradaMaestro();
			entrada.setCodigo(codigo);
			entrada.setCiclo(ciclo);
			entrada.setCodFacturaCompra(request.getFactura());
			entrada.setCodProveedor(request.getProveedor().getCodigo());
			entrada.setFechaFacturaCompra(request.getFecha());
			entradasRep.save(entrada);
			kardexBean.grabarEntrada(request.getDetalles(), codigo, ciclo);
			return new EntradaResponse(Constantes.ACTUALIZACION_EXITO, codigo);
		}catch(Exception e){
			return new EntradaResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

	private Novedad getNovedad(MaterialRequest request) {
		Sistema sistema = sistemaRep.findOne(1L);
		Novedad novedad = new Novedad();
		novedad.setId(new NovedadPK(request.getInstalacion(), sistema.getIdMateriales()));
		Double sum = request.getDetalles().stream().mapToDouble(MaterialDetalleRequest::getPrecio).sum();
		novedad.setValor(sum.longValue());
		return novedad;
	}

	public MensajeResponse crearSalida(MaterialRequest request) {
		try{
			if(request.getInstalacion()!=null && !instalacionesRep.exists(request.getInstalacion())){
				return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, request.getInstalacion()));
			}
			MensajeResponse guardarNovedad = novedadesBean.guardar(getNovedad(request));
			if(guardarNovedad.getEstado().equals(EstadoEnum.ERROR)){
				return guardarNovedad;
			}
			Long codigo = salidasRep.findMaxSalida() + 1L;
			Long ciclo = ciclosBean.getPorEstado(Constantes.ABIERTO).getCiclo();
			SalidaMaestro salida = new SalidaMaestro();
			salida.setCodigo(codigo);
			salida.setCiclo(ciclo);
			salida.setFechaOrdenSalida(request.getFecha());
			salidasRep.save(salida);
			kardexBean.grabarSalida(request.getDetalles(), codigo, ciclo);
			return new MensajeResponse(Constantes.getMensaje(Constantes.SALIDA_ORDEN_EXITO, codigo));
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

}
