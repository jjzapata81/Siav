package co.com.siav.bean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.entities.Ciclo;
import co.com.siav.entities.DetalleFactura;
import co.com.siav.entities.Factura;
import co.com.siav.entities.FormatoRecaudo;
import co.com.siav.entities.Pago;
import co.com.siav.exception.ExcepcionNegocio;
import co.com.siav.repositories.IRepositoryCiclos;
import co.com.siav.repositories.IRepositoryFacturas;
import co.com.siav.repositories.IRepositoryFormatoRecaudo;
import co.com.siav.repositories.IRepositoryParametros;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.FacturaResponse;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;
import co.com.siav.utils.Filtro;

@Stateless
public class PagosBean {
	
	private static final String DIGITADO = "DIGITADO";
	private static final String RECAUDO_BANCO = "1";
	private static final String CONSIGNACION_FAX = "2";

	@Inject
	private IRepositoryFacturas facturasRep;
	
	@Inject
	private IRepositoryParametros parametrosRep;
	
	@Inject
	private IRepositoryCiclos ciclosRep;
	
	@Inject
	private PagosManager manager;
	
	@Inject
	private IRepositoryFormatoRecaudo formatoRep;
	
	private FormatoRecaudo formatoRecaudo;
	
	private Long numeracionCredito;

	public MensajeResponse guardar(Pago pago, String usuario) {
		manager.run(CONSIGNACION_FAX);
		pago.setRutaPago(DIGITADO);
		pago.setUsuario(usuario);
		getFactura(pago);
		return new MensajeResponse(Constantes.ACTUALIZACION_EXITO);
	}
	
	public FacturaResponse buscar(Long numeroInstalacion){
		Factura factura = buscarFactura(numeroInstalacion);
		if(null == factura){
			return new FacturaResponse(EstadoEnum.ERROR, Constantes.FACTURA_NO_EXISTE);
		}
		if(factura.getCancelado()){
			return new FacturaResponse(EstadoEnum.ERROR, Constantes.getMensaje(Constantes.FACTURA_YA_CANCELADA, factura.getNumeroFactura()));
		}
		FacturaResponse response = new FacturaResponse();
		response.setNombres(factura.getNombres());
		response.setNumeroFactura(factura.getNumeroFactura());
		response.setNumeroInstalacion(factura.getNumeroInstalacion());
		response.setValor(getValorTotalFactura(factura));
		return response;
	}
	
	private Long getValorTotalFactura(Factura factura) {
		Long totalValor = factura.getDetalles().stream().mapToLong(DetalleFactura::getValor).sum();
		Long totalSaldo = factura.getDetalles().stream().mapToLong(DetalleFactura::getSaldo).sum();
		return totalValor + totalSaldo;
	}

	private Factura buscarFactura(Long numeroInstalacion){
		Ciclo cicloAnterior = ciclosRep.findFirstByEstadoOrderByCicloDesc(Constantes.CERRADO);
		return facturasRep.findByNumeroInstalacionAndCiclo(numeroInstalacion, cicloAnterior.getCiclo());
	}

	public void guardarArchivo(String nombreArchivo, String codigoCuenta, String usuario, File archivoPagos) {
		validar(codigoCuenta);
		String numeroFormato = parametrosRep.findByCdParametro(Constantes.FORMATO_RECAUDO).getDsValor();
		numeracionCredito = Long.valueOf(parametrosRep.findByCdParametro(Constantes.NUMERACION_CREDITO).getDsValor());
		
		System.out.println("Numero fact" + numeracionCredito);
		formatoRecaudo = formatoRep.findOne(numeroFormato);
		try {
			String rutaArchivo = parametrosRep.findByCdParametro(Constantes.RUTA_ARCHIVO_PAGO).getDsValor();
			List<String> lines = Files.readAllLines(Paths.get(archivoPagos.getAbsolutePath()), Charset.defaultCharset());
			List<Pago> pagos = lines.stream().skip(3L).map(this::convert).collect(Collectors.toList());
			String directorio = getDirectorio(pagos.get(0));
			String rutaFinal = moverArchivo(archivoPagos.getAbsolutePath(), rutaArchivo + directorio + "\\", nombreArchivo);
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

	private Factura getFactura(Pago pago){
		if(0 < pago.getNumeroFactura().compareTo(numeracionCredito)){
			manager.addDirtectPay(pago);
			return new Factura();
		}
		Factura factura = facturasRep.findOne(pago.getNumeroFactura());
		if(factura == null){
			manager.addFail(pago, Constantes.getMensaje(Constantes.FACTURA_NO_EXISTE, pago.getNumeroFactura()));
			factura = new Factura();
		}else if(factura.getCancelado()){
			manager.addFail(pago, Constantes.getMensaje(Constantes.FACTURA_YA_CANCELADA, pago.getNumeroFactura()));
			factura = new Factura();
		}else{
			factura = manager.process(factura, pago);
			facturasRep.save(factura);
		}
		return factura;
	}
	
	private Pago convert(String linea){
		try{
			String[] atributos = linea.split(formatoRecaudo.getSeparador());
			Pago pago = new Pago();
			pago.setFecha(setFecha(atributos[formatoRecaudo.getFecha()].trim(), formatoRecaudo.getFormatoFecha()));
			pago.setValor(atributos[5].trim());
			pago.setNumeroFactura(getValorAuxiliar(atributos[formatoRecaudo.getReferencia()], formatoRecaudo.getSeparadorAux(), formatoRecaudo.getPosicionAux()));
			return pago;
		}catch(Exception e){
			throw new ExcepcionNegocio(Constantes.ERR_ESTRUCTURA_ARCHIVO);
		}
	}
	
	public Date setFecha(String fecha, String formato) {
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			return formatter.parse(fecha);
		} catch (ParseException e) {
			return new Date();
		}
	}

	private String getValorAuxiliar(String valor, String separador, int posicion) {
		return valor.split(separador)[posicion].trim().substring(formatoRecaudo.getPosicionInicialFactura(), formatoRecaudo.getPosicionFinalFactura());
	}
	
	private String moverArchivo(String rutaArchivo, String rutaDestino, String nombreArchivo) {
		File origen = new File(rutaArchivo);
		File destino = new File(rutaDestino);
		int items= 0;
		if (destino.exists()) {
			items = destino.list(new Filtro(nombreArchivo)).length;
		}else{
			destino.mkdir();
		}
		destino = new File(crearPathDestino(destino.getAbsolutePath(), nombreArchivo, items));
		try {
			Files.copy(Paths.get(origen.getAbsolutePath()), Paths.get(destino.getAbsolutePath()));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return destino.getAbsolutePath();

	}
	
	private String crearPathDestino(String path, String nombre, int numeroCopia) {
		if (numeroCopia == 0) {
			return path + "/" + nombre;
		}
		return path + "/" + nombre.substring(0, nombre.length() - 4) + "(" + numeroCopia + ").csv";
	}
	
	private void validar(String codigoCuenta) {
		if(codigoCuenta == null || codigoCuenta.trim() == ""){
			throw new ExcepcionNegocio(Constantes.ERR_SIN_CODIGO_CUENTA);
		}
	}
	
	private String getDirectorio(Pago pago) {
		Factura factura = facturasRep.findOne(pago.getNumeroFactura());
		if (null == factura || null == factura.getCiclo()){
			return Constantes.SIN_FECHA;
		}
		return String.valueOf(factura.getCiclo());
	}

}
