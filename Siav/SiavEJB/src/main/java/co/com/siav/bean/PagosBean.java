package co.com.siav.bean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Ciclo;
import co.com.siav.entities.Comprobante;
import co.com.siav.entities.Factura;
import co.com.siav.entities.FormatoRecaudo;
import co.com.siav.entities.Pago;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryComprobante;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryFormatoRecaudo;
import co.com.siav.repositories.IRepositoryPago;
import co.com.siav.repositories.IRepositoryParametros;
import co.com.siav.request.FiltroRequest;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.FacturaResponse;
import co.com.siav.response.MensajeResponse;
import co.com.siav.response.PagoResponse;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.FacturaUtil;
import co.com.siav.utils.FileUtil;
import co.com.siav.utils.Utilidades;

@Stateless
public class PagosBean {
	
	private static final String DIGITADO = "DIGITADO";
	private static final String RECAUDO_BANCO = "1";
	private static final String CONSIGNACION_FAX = "2";

	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryComprobante comprobanteRep;
	
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
	
	private FormatoRecaudo formatoRecaudo;
	
	private Long numeroCiclo;
	
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
		response.setValor(FacturaUtil.getValorTotalFactura(factura));
		return response;
	}

	public MensajeResponse guardar(Pago pago, String usuario) {
		manager.run(CONSIGNACION_FAX);
		pago.setRutaPago(DIGITADO);
		pago.setValor(pago.getValor() == null ? 0L : pago.getValor());
		pago.setUsuario(usuario);
		Ciclo cicloAnterior = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO);
		numeroCiclo = cicloAnterior.getCiclo();
		getFactura(pago);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}
	
	public void guardarArchivo(String nombreArchivo, String codigoCuenta, String usuario, File archivoPagos) {
		validar(codigoCuenta);
		String numeroFormato = parametrosRep.findByCdParametro(Constantes.FORMATO_RECAUDO).getDsValor();
		formatoRecaudo = formatoRep.findOne(numeroFormato);
		try {
			String rutaArchivo = parametrosRep.findByCdParametro(Constantes.RUTA_ARCHIVO_PAGO).getDsValor();
			List<String> lines = Files.readAllLines(Paths.get(archivoPagos.getAbsolutePath()), Charset.defaultCharset());
			List<Pago> pagos = lines.stream().skip(3L).map(this::convert).collect(Collectors.toList());
			String rutaFinal = FileUtil.moverArchivo(archivoPagos.getAbsolutePath(), rutaArchivo, numeroCiclo, nombreArchivo);
			pagos.stream().forEach(
					pago-> {
						pago.setCodigoCuenta(codigoCuenta);
						pago.setRutaPago(rutaFinal);
						pago.setUsuario(usuario);
					});
			manager.run(RECAUDO_BANCO);
			pagos.stream().map(this::getFactura).filter(item -> item.getNumeroFactura() != null).collect(Collectors.toList());
		} catch (IOException e) {
			throw new ExcepcionNegocio(Constantes.ERR_LECTURA_ARCHIVO);
		}
		
	}
	
	private Factura buscarFactura(Long numeroInstalacion){
		Ciclo cicloAnterior = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO);
		return facturasRep.findByNumeroInstalacionAndCiclo(numeroInstalacion, cicloAnterior.getCiclo());
	}

	private Factura getFactura(Pago pago){
		Factura factura = find(pago);
		if(factura == null){
			manager.addFail(pago, Constantes.getMensaje(Constantes.FACTURA_NO_EXISTE, pago.getNumeroFactura()));
			factura = new Factura();
		}else if(factura.getCancelado()&&!factura.getAbono()){
			manager.addFail(pago, Constantes.getMensaje(Constantes.FACTURA_YA_CANCELADA, pago.getNumeroFactura()));
			factura = new Factura();
		}else{
			factura = manager.process(factura, pago);
			facturasRep.save(factura);
		}
		return factura;
	}

	private Factura find(Pago pago) {
		Factura factura = facturasRep.findOne(pago.getNumeroFactura());
		if(null == factura){
			Comprobante comprobante = comprobanteRep.findOne(pago.getNumeroFactura());
			if(null == comprobante){
				return null;
			}
			if(null==comprobante.getIdCredito() && !comprobante.getEsMatricula() && !comprobante.getCancelado()){
				comprobante.setCancelado(true);
				comprobanteRep.save(comprobante);
				return facturasRep.findByNumeroInstalacionAndCiclo(comprobante.getInstalacion(), Long.valueOf(numeroCiclo));
			}else{
				manager.addOtherPay(pago, comprobante);
				comprobante.setCancelado(true);
				comprobanteRep.save(comprobante);
			}
		}
		return factura;
	}

	private Pago convert(String linea){
		try{
			String[] atributos = linea.split(formatoRecaudo.getSeparador());
			Pago pago = new Pago();
			pago.setFecha(Utilidades.setFechaPago(atributos[formatoRecaudo.getFecha()].trim(), formatoRecaudo.getFormatoFecha()));
			pago.setValor(getValor(atributos));
			pago.setNumeroFactura(getValorAuxiliar(atributos[formatoRecaudo.getReferencia()], formatoRecaudo.getSeparadorAux(), formatoRecaudo.getPosicionAux()));
			return pago;
		}catch(Exception e){
			throw new ExcepcionNegocio(Constantes.ERR_ESTRUCTURA_ARCHIVO);
		}
	}

	private Long getValor(String[] atributos) {
		return Long.valueOf(atributos[formatoRecaudo.getValor()].trim());
	}
	

	private String getValorAuxiliar(String valor, String separador, int posicion) {
		String referencia = valor.split(separador)[posicion].trim();
		if(null == numeroCiclo){
			numeroCiclo = Long.valueOf(referencia.substring(formatoRecaudo.getPosicionInicialCiclo(), formatoRecaudo.getPosicionFinalCiclo()));
		}
		return referencia.substring(formatoRecaudo.getPosicionInicialFactura(), formatoRecaudo.getPosicionFinalFactura());
	}
	
	private void validar(String codigoCuenta) {
		if(codigoCuenta == null || codigoCuenta.trim() == ""){
			throw new ExcepcionNegocio(Constantes.ERR_SIN_CODIGO_CUENTA);
		}
	}

	

}
