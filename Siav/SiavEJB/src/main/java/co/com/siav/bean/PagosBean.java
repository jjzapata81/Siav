package co.com.siav.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Factura;
import co.com.siav.entities.FormatoRecaudo;
import co.com.siav.entities.Pago;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryFormatoRecaudo;
import co.com.siav.repositories.IRepositoryPago;
import co.com.siav.repositories.IRepositoryParametros;
import co.com.siav.request.FiltroRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.FacturaResponse;
import co.com.siav.response.PagoResponse;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.FacturaUtil;
import co.com.siav.utils.FileUtil;
import co.com.siav.utils.FormatoUtil;

@Stateless
public class PagosBean {
	
	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryParametros parametrosRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private PagosManager manager;
	
	@Inject
	private IRepositoryPago pagosRep;
	
	@Inject
	private IRepositoryFormatoRecaudo formatoRep;
		
	public List<PagoResponse> consultar(FiltroRequest request) {
		List<Pago> pagos = pagosRep.findByFechaBetween(
				request.getFechaDesde() == null ? new Date() : request.getFechaDesde(),
						request.getFechaHasta() == null ? new Date() : request.getFechaHasta());
		if(pagos.isEmpty()){
			throw new ExcepcionNegocio(Constantes.ERR_CONSULTA);
		}
		List<PagoResponse> response = new ArrayList<PagoResponse>();
		pagos.stream().collect(Collectors.groupingBy(Pago::getFecha)).forEach((key, value) -> response.add(new PagoResponse(key, value)));
		return response;
	}
	
	public FacturaResponse buscar(Long numeroInstalacion){
		Factura factura = buscarFactura(numeroInstalacion);
		if(null == factura){
			return new FacturaResponse(EstadoEnum.ERROR, Constantes.FACTURA_NO_EXISTE);
		}
		if(factura.getCancelado() && !factura.getAbono()){
			return new FacturaResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.FACTURA_YA_CANCELADA, factura.getNumeroFactura()));
		}
		FacturaResponse response = new FacturaResponse();
		response.setNombres(factura.getNombres());
		response.setNumeroFactura(factura.getNumeroFactura());
		response.setNumeroInstalacion(factura.getNumeroInstalacion());
		response.setCedula(factura.getCedula());
		response.setValor(FacturaUtil.getNeto(factura));
		return response;
	}
	
	private Factura buscarFactura(Long numeroInstalacion){
		return facturasRep.findByNumeroInstalacionAndCiclo(numeroInstalacion, getCiclo());
	}
	
	private Long getCiclo() {
		return ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO).getCiclo();
	}

	public List<Pago> guardarArchivo(String nombreArchivo, String codigoCuenta, String usuario, File archivoPagos) {
		List<String> lines = FileUtil.readFile(archivoPagos);
		String rutaFinal = FileUtil.moverArchivo(archivoPagos.getAbsolutePath(), parametrosRep.findByCdParametro(Constantes.RUTA_ARCHIVO_PAGO).getDsValor(), getCiclo(), nombreArchivo);
		List<Pago> pagos = convertToPay(lines, codigoCuenta, rutaFinal, usuario);
		return pagos.stream().map(item-> manager.getPago(item).process(item)).collect(Collectors.toList());
	}

	private List<Pago> convertToPay(List<String> lines, String codigoCuenta, String rutaFinal, String usuario) {
		String numeroFormato = parametrosRep.findByCdParametro(Constantes.FORMATO_RECAUDO).getDsValor();
		FormatoRecaudo formato = formatoRep.findOne(numeroFormato);
		return lines.stream().skip(3L).map(item -> FormatoUtil.convert(item, formato, codigoCuenta, rutaFinal, usuario)).collect(Collectors.toList());
	}	

}
