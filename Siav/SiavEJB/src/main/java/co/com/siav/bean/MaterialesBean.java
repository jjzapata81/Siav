package co.com.siav.bean;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.bean.builders.EntradaBuilder;
import co.com.siav.bean.builders.MaterialBuilder;
import co.com.siav.bean.builders.NovedadBuilder;
import co.com.siav.bean.builders.SalidaBuilder;
import co.com.siav.entities.EntradaMaestro;
import co.com.siav.entities.Material;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.NovedadPK;
import co.com.siav.entities.SalidaMaestro;
import co.com.siav.repositories.IRepositoryEntradas;
import co.com.siav.repositories.IRepositoryInstalaciones;
import co.com.siav.repositories.IRepositoryMaterial;
import co.com.siav.repositories.IRepositoryNovedades;
import co.com.siav.repositories.IRepositorySalidas;
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
	private CiclosBean ciclosBean;
	
	@Inject
	private IRepositoryMaterial materialRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;
	
	@Inject
	private IRepositoryNovedades novedadesRep;
	
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
			EntradaMaestro entrada = EntradaBuilder.crear(request, codigo, ciclo);
			entradasRep.save(entrada);
			kardexBean.grabarEntrada(request.getDetalles(), codigo, ciclo);
			return new EntradaResponse(Constantes.ACTUALIZACION_EXITO, codigo);
		}catch(Exception e){
			return new EntradaResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

	public MensajeResponse crearSalida(MaterialRequest request) {
		try{
			Long codigo = salidasRep.findMaxSalida() + 1L;
			Long ciclo = ciclosBean.getPorEstado(Constantes.ABIERTO).getCiclo();
			if(Constantes.DESTINO_INSTALACION.equals(request.getDestinoSeleccionado())){
				return crearSalidaInstalacion(request, codigo, ciclo);
			}else{
				return crearSalidaAcueducto(request, codigo, ciclo);
			}
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}
	
	private MensajeResponse crearSalidaInstalacion(MaterialRequest request, Long codigo, Long ciclo) {
		if(request.getInstalacion()!=null && !instalacionesRep.exists(request.getInstalacion())){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.INSTALACION_NO_EXISTE, request.getInstalacion()));
		}
		SalidaMaestro salida = SalidaBuilder.crearSalidaBD(request, codigo, ciclo);
		salidasRep.save(salida);
		kardexBean.grabarSalida(request.getDetalles(), codigo, ciclo);
		registrarMateriales(ciclo, codigo, request);
		registrarNovedadIva(ciclo, request);
		return new MensajeResponse(Constantes.getMensaje(Constantes.SALIDA_ORDEN_EXITO, codigo));
	}

	private MensajeResponse crearSalidaAcueducto(MaterialRequest request, Long codigo, Long ciclo) {
		SalidaMaestro salida = SalidaBuilder.crearSalidaBD(request, codigo, ciclo);
		salidasRep.save(salida);
		kardexBean.grabarSalida(request.getDetalles(), codigo, ciclo);
		return new MensajeResponse(Constantes.getMensaje(Constantes.SALIDA_ORDEN_EXITO, codigo));
	}

	private void registrarNovedadIva(Long ciclo, MaterialRequest request) {
		NovedadPK novedadPK = NovedadBuilder.crearPK(ciclo, request.getInstalacion(), Constantes.CODIGO_CONCEPTO_IVA_VENTAS);
		Novedad novedad = novedadesRep.findOne(novedadPK);
		Double valor = request.getDetalles().stream().mapToDouble(item->item.getPrecio() * item.getArticulo().getPorcentajeIva()).sum();
		if(null != novedad){
			novedad.setValor(novedad.getValor() + Math.round(valor));
		}else{
			novedad = NovedadBuilder.crear(novedadPK, valor, false);
		}
		novedadesRep.save(novedad);
		
	}

	private void registrarMateriales(Long ciclo, Long idSalida, MaterialRequest request) {
		request.getDetalles().stream().forEach(item-> grabarMaterial(item, ciclo, idSalida, request.getInstalacion()));
	}

	private void grabarMaterial(MaterialDetalleRequest item, Long ciclo, Long idSalida, Long instalacion) {
		Material material = materialRep.findByInstalacionAndCicloAndCodigo(instalacion, ciclo, item.getArticulo().getCodigoContable());
		if(material != null){
			material = MaterialBuilder.actualizar(material, item);
		}else{
			material = MaterialBuilder.crear(item, ciclo, idSalida, instalacion);
		}
		
		materialRep.save(material);
	}

}
