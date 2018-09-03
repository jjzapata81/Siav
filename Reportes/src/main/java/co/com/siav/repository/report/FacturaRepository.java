package co.com.siav.repository.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.dto.CobroPDF;
import co.com.siav.pdf.dto.ConsumoPDF;
import co.com.siav.pdf.dto.CreditoPDF;
import co.com.siav.pdf.dto.FacturaBD;
import co.com.siav.pdf.dto.FacturaDetalleBD;
import co.com.siav.pdf.dto.FacturaPDF;
import co.com.siav.pdf.dto.FacturacionPDF;
import co.com.siav.pdf.dto.ValoresFacturados;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.ConsultaRango;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Ciclo;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.entities.Sistema;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;

public class FacturaRepository implements IReportType{

	private Empresa empresa;
	private Ciclo ciclo;
	private Sistema sistema;
	private String resolucion;
	
	@Inject
	private SendMail notifier;

	@Override
	public byte[] getPDF(Filter filter) {
		getValoresGenerales(filter);
		if(ciclo.getSnestado().equals(Constantes.ABIERTO)){
			throw new TechnicalException(Constantes.ERR_CICLO_ABIERTO);
		}
		return new GeneradorPDF(getFactura(filter), Constantes.FACTURA_JRXML).getStream();
	}

	@Override
	public byte[] download(Filter filter) {
		return null;
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.FACTURACION_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return String.format(Reporte.FACTURACION_CUERPO, ciclo);
	}
	
	private Attachment getFile(Filter filter){
		return new Attachment(String.format(Reporte.FACTURACION, filter.getCiclo()), getPDF(filter));
	}
	
	private void getValoresGenerales(Filter filter) {
		empresa = Util.getEmpresa();
		ciclo = Util.getCiclo(filter);
		sistema = Util.getSistema();
		resolucion = Util.getParametro(new ConsultaRango(Constantes.ATT_RESOLUCION, Constantes.ESTADO_VIGENTE));
	}
	
	private List<FacturacionPDF> getFactura(Filter filter) {
		FacturacionPDF factura = new FacturacionPDF();
		factura.setFacturas(getFacturas(filter));
		return Arrays.asList(factura);
	}

	private List<FacturaPDF> getFacturas(Filter filter) {
		String query = QueryHelper.getFacturaEncabezado(filter);
		ReportBDFactory<FacturaBD> factory = new ReportBDFactory<>();
		List<FacturaBD> instalaciones = factory.getReportResult(FacturaBD.class, query);
		if(null == instalaciones || instalaciones.isEmpty()){
			throw new TechnicalException(Constantes.FACTURA_NO_EXISTE);
		}
		return instalaciones.stream().map(this::transform).collect(Collectors.toList());
	}
	
	private FacturaPDF transform(FacturaBD facturaBD){
		List<FacturaDetalleBD> detalles = getDetalles(facturaBD.getNumeroFactura());
		FacturaPDF facturaPDF = new FacturaPDF();
		facturaPDF.setCiclo(facturaBD.getCiclo());
		facturaPDF.setNombre(facturaBD.getNombre());
		facturaPDF.setInstalacion(facturaBD.getInstalacion());
		facturaPDF.setDireccion(facturaBD.getDireccion());
		facturaPDF.setVereda(facturaBD.getVereda());
		facturaPDF.setNumeroFactura(facturaBD.getNumeroFactura());
		facturaPDF.setConsumoActual(facturaBD.getConsumoActual());
		facturaPDF.setPromedioConsumo(facturaBD.getPromedioConsumo());
		facturaPDF.setCuentasVencidas(facturaBD.getCuentasVencidas());
		facturaPDF.setDiasConsumo(facturaBD.getDiasConsumo());
		facturaPDF.setLecturaActual(facturaBD.getLecturaActual());
		facturaPDF.setLecturaAnterior(facturaBD.getLecturaAnterior());
		facturaPDF.setReferente(facturaBD.getInstalacion() + facturaBD.getNumeroFactura() + facturaBD.getCiclo());
		facturaPDF.setFePagoRecargo(ciclo.getFeconrecargo());
		facturaPDF.setFePagoSinRecargo(ciclo.getFesinrecargo());
		facturaPDF.setMensajePrincipal(ciclo.getMensaje());
		facturaPDF.setNit(empresa.getNit());
		facturaPDF.setNombreAcueducto(empresa.getNombreCorto());
		facturaPDF.setValoresFacturados(getValoresFacturados(detalles));
		facturaPDF.setTotalVencido((detalles.stream().mapToLong(FacturaDetalleBD::getSaldo)).sum());
		facturaPDF.setValorTotal((detalles.stream().mapToLong(FacturaDetalleBD::getValor)).sum() + facturaPDF.getTotalVencido());
		facturaPDF.setCodigoBarras(Util.getCodigoBarras(facturaPDF.getReferente(), facturaPDF.getValorTotal(), facturaPDF.getFePagoRecargo() == null ? facturaPDF.getFePagoSinRecargo() : facturaPDF.getFePagoRecargo()));
		facturaPDF.setResolucion(resolucion);
		facturaPDF.setConsumoAnterior(facturaBD.getConsumoAnterior());
		facturaPDF.setConsumos(getConsumos(facturaBD.getHistoricoConsumo(), facturaBD.getInstalacion()));
		facturaPDF.setEstrato(facturaBD.getEstrato());
		facturaPDF.setFechaFacturacion(ciclo.getFeFactura());
		facturaPDF.setFechaActual(facturaBD.getFechaActual());
		facturaPDF.setFechaAnterior(facturaBD.getFechaAnterior());
		facturaPDF.setMedidor(facturaBD.getMedidor());
		facturaPDF.setFechaUltimoPago(facturaBD.getFechaUltimoPago());
		facturaPDF.setMensajePuntoPago(ciclo.getMensajePuntoPago());
		facturaPDF.setMensajeReclamo(ciclo.getMensajeReclamo());
		facturaPDF.setMensajeCorte(sistema.getCuentasCorte().equals(facturaBD.getCuentasVencidas()) ? ciclo.getMensajeCorte() : Constantes.EMPTY);
		facturaPDF.setOtrosCobros(getOtrosCobros(detalles));
		facturaPDF.setValorMesAnterior(facturaBD.getValorMesAnterior());
		facturaPDF.setCreditos(getCreditos(facturaBD.getInstalacion()));
		return facturaPDF;
	}

	private List<CobroPDF> getOtrosCobros(List<FacturaDetalleBD> detalles) {
		return detalles.stream().filter(detalle -> 
			!sistema.getCargoFijo().equals(detalle.getCodigoConcepto()) &&
			!sistema.getBasico().equals(detalle.getCodigoConcepto()) &&
			!sistema.getComplementario().equals(detalle.getCodigoConcepto()) &&
			!sistema.getSuntuario().equals(detalle.getCodigoConcepto()) &&
			detalle.getCodigoCredito() == null &&
			detalle.getValor() != 0L).map(this::getCobro).collect(Collectors.toList());
	}
	
	private CobroPDF getCobro(FacturaDetalleBD detalle){
		CobroPDF cobro = new CobroPDF();
		cobro.setDetalle(detalle.getNombreConcepto());
		cobro.setValor(detalle.getValor());
		return cobro;
	}

	private List<ConsumoPDF> getConsumos(String historicoConsumos, String instalacion) {
		try{
			if(null != historicoConsumos){
				List<String> historico = Arrays.asList(historicoConsumos.split(";"));
				return historico.stream().map(this::getConsumo).collect(Collectors.toList());
			}
			return new ArrayList<ConsumoPDF>();
		}catch(Exception e){
			throw new TechnicalException(Constantes.ERR_HISTORICO_CONSUMOS + instalacion);
		}
	}
	
	private ConsumoPDF getConsumo(String valor){
		return new ConsumoPDF(valor);
	}

	private List<CreditoPDF> getCreditos(String numeroInstalacion) {
		String query = QueryHelper.getCreditos(numeroInstalacion);
		ReportBDFactory<CreditoPDF> factory = new ReportBDFactory<>();
		return factory.getReportResult(CreditoPDF.class, query);
	}

	private List<FacturaDetalleBD> getDetalles(String numeroFactura){
		try{
			String query = QueryHelper.getFacturaDetalle(numeroFactura);
			ReportBDFactory<FacturaDetalleBD> factory = new ReportBDFactory<>();
			return factory.getReportResult(FacturaDetalleBD.class, query);
		}catch(Exception e){
			throw new TechnicalException(Constantes.ERR_DETALLE_FACTURA + numeroFactura + ". " + e.getMessage());
		}
	}


	private List<ValoresFacturados> getValoresFacturados(List<FacturaDetalleBD> detalles) {
		List<ValoresFacturados> valores = new ArrayList<ValoresFacturados>();
		ValoresFacturados item = detalles.stream().filter(detalle -> 
			sistema.getCargoFijo().equals(detalle.getCodigoConcepto())).findFirst().map(this::crearValorFacturado).orElse(null);
		if(null!=item)
			valores.add(item);
		item = detalles.stream().filter(detalle -> 
			sistema.getBasico().equals(detalle.getCodigoConcepto())).findFirst().map(this::crearValorFacturado).orElse(null);
		if(null!=item)
			valores.add(item);
		item = detalles.stream().filter(detalle -> 
			sistema.getComplementario().equals(detalle.getCodigoConcepto())).findFirst().map(this::crearValorFacturado).orElse(null);
		if(null!=item)
			valores.add(item);
		item = detalles.stream().filter(detalle -> 
			sistema.getSuntuario().equals(detalle.getCodigoConcepto())).findFirst().map(this::crearValorFacturado).orElse(null);
		if(null!=item)
		valores.add(item);
		return valores;
	}

	private ValoresFacturados crearValorFacturado(FacturaDetalleBD detalle) {
		try{
			ValoresFacturados vf = new ValoresFacturados();
			vf.setConcepto(detalle.getNombreConcepto());
			vf.setValor(detalle.getValor());
			if(null != detalle.getMetros() && detalle.getMetros()>0L){
				vf.setM3(detalle.getMetros());
				vf.setTarifa(getTarifa(detalle.getValor(), detalle.getMetros()));
			}
			return vf;
		}catch (Exception e){
			throw new TechnicalException(Constantes.ERR_VALORES_FACTURADOS + e.getMessage());
		}
	}

	private Long getTarifa(Long valor, Long metros) {
		Double total = valor.doubleValue() / metros;
		return total.longValue();
	}

}
