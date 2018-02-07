package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.com.siav.exception.TechnicalException;
import co.com.siav.pdf.dto.CreditoPDF;
import co.com.siav.pdf.dto.FacturaBD;
import co.com.siav.pdf.dto.FacturaDetalleBD;
import co.com.siav.pdf.dto.FacturaPDF;
import co.com.siav.pdf.dto.FacturacionPDF;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.ConsultaRango;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Ciclo;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.entities.Sistema;
import co.com.siav.repository.entities.UsuarioMail;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;
import co.com.siav.utility.FacturaUtil;
import co.com.siav.utility.Utilidades;

public class FacturaCicloRepository implements IReportType{

	private Empresa empresa;
	private Ciclo ciclo;
	private Sistema sistema;
	private String resolucion;
//	
//	@Inject
//	private SendMail notifier;

	@Override
	public byte[] getPDF(Filter filter) {
		getValoresGenerales();
		return new GeneradorPDF(getFactura(), Constantes.FACTURA_JRXML).getStream();
	}

	@Override
	public byte[] download(Filter filter) {
		return null;
	}

	@Override
	public void send(Filter filter) {
//		notifier.send(filter.getEmail(),Reporte.FACTURACION_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
//	private String getTextoMensaje(Long ciclo) {
//		return String.format(Reporte.FACTURACION_CUERPO, ciclo);
//	}
//	
//	private Attachment getFile(Filter filter){
//		return new Attachment(String.format(Reporte.FACTURACION, filter.getCiclo()), getPDF(filter));
//	}
	
	private void getValoresGenerales() {
		empresa = Util.getEmpresa();
		ciclo = Util.getCicloPorEstado(Constantes.CERRADO);
		sistema = Util.getSistema();
		resolucion = Util.getParametro(new ConsultaRango(Constantes.ATT_RESOLUCION, Constantes.ESTADO_VIGENTE));
	}
	
	private List<FacturacionPDF> getFactura() {
		FacturacionPDF factura = new FacturacionPDF();
		factura.setFacturas(getFacturas());
		return Arrays.asList(factura);
	}

	private List<FacturaPDF> getFacturas() {
		Filter filter = new Filter();
		filter.setCiclo(ciclo.getCiclo());
		String query = QueryHelper.getFacturaEncabezado(filter);
		ReportBDFactory<FacturaBD> factory = new ReportBDFactory<>();
		List<FacturaBD> instalaciones = factory.getReportResult(FacturaBD.class, query);
		if(null == instalaciones || instalaciones.isEmpty()){
			throw new TechnicalException(Constantes.FACTURA_NO_EXISTE);
		}
		if(Constantes.SI.equals(sistema.getEnvioFactura())){
			List<UsuarioMail> usuarios = getUsuarios();
//			usuarios.stream().forEach(usuario -> get(usuario, getFiltro(instalaciones, usuarios)));
			return instalaciones.stream().filter(item -> !contains(usuarios, item)).map(this::transform).collect(Collectors.toList());
		}else{
			return instalaciones.stream().map(this::transform).collect(Collectors.toList());
		}
	}

//	private List<FacturaBD> getFiltro(List<FacturaBD> instalaciones, List<UsuarioMail> usuarios) {
//		return instalaciones.stream().filter(item -> contains(usuarios, item)).collect(Collectors.toList());
//	}

	private boolean contains(List<UsuarioMail> usuarios, FacturaBD factura) {
		return usuarios.stream().filter(item -> item.getCedula().equals(factura.getCedula()) && Utilidades.emailValido(item.getEmail())).findFirst().isPresent();
	}

//	private void sendMail(UsuarioMail usuario, List<FacturaPDF> facturasPDF) {
//		notifier.send(usuario.getEmail(),String.format(Reporte.FACTURA_EMAIL_ASUNTO, Formatter.getNombreMes(ciclo.getFecha())), 
//				String.format(Reporte.FACTURA_EMAIL_CUERPO, 
//						Formatter.getNombreMes(ciclo.getFecha()), empresa.getTelefono(), empresa.getDireccion(), empresa.getCiudad()), getFile(usuario.getCedula(), facturasPDF));
//	}
	
//	private Attachment getFile(String cedula, List<FacturaPDF> facturasPDF){
//		return new Attachment(String.format(Reporte.FACTURA_EMAIL_ATACH, cedula, Formatter.getMes(ciclo.getFecha())), new GeneradorPDF(getFactura(facturasPDF), Constantes.FACTURA_JRXML).getStream());
//	}
	
//	private List<FacturacionPDF> getFactura(List<FacturaPDF> facturasPDF) {
//		FacturacionPDF factura = new FacturacionPDF();
//		factura.setFacturas(facturasPDF);
//		return Arrays.asList(factura);
//	}

//	private void get(UsuarioMail usuario, List<FacturaBD> facturas){
//		List<FacturaPDF> facturasPDF = facturas.stream().filter(factura -> usuario.getCedula().equals(factura.getCedula())).map(this::transform).collect(Collectors.toList());
//		sendMail(usuario, facturasPDF);
//	}

	private List<UsuarioMail> getUsuarios() {
		String query = QueryHelper.getUsuariosMail();
		ReportBDFactory<UsuarioMail> factory = new ReportBDFactory<>();
		return factory.getReportResult(UsuarioMail.class, query).stream()
				.filter(usuario -> Utilidades.emailValido(usuario.getEmail())).collect(Collectors.toList());
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
		facturaPDF.setValoresFacturados(FacturaUtil.getValoresFacturados(detalles, sistema));
		facturaPDF.setTotalVencido((detalles.stream().mapToLong(FacturaDetalleBD::getSaldo)).sum());
		facturaPDF.setValorTotal((detalles.stream().mapToLong(FacturaDetalleBD::getValor)).sum() + facturaPDF.getTotalVencido());
		facturaPDF.setCodigoBarras(Util.getCodigoBarras(facturaPDF.getReferente(), facturaPDF.getValorTotal(), facturaPDF.getFePagoRecargo() == null ? facturaPDF.getFePagoSinRecargo() : facturaPDF.getFePagoRecargo()));
		facturaPDF.setResolucion(resolucion);
		facturaPDF.setConsumoAnterior(facturaBD.getConsumoAnterior());
		facturaPDF.setConsumos(FacturaUtil.getConsumos(facturaBD.getHistoricoConsumo(), facturaBD.getInstalacion()));
		facturaPDF.setEstrato(facturaBD.getEstrato());
		facturaPDF.setFechaFacturacion(ciclo.getFeFactura());
		facturaPDF.setFechaActual(facturaBD.getFechaActual());
		facturaPDF.setFechaAnterior(facturaBD.getFechaAnterior());
		facturaPDF.setMedidor(facturaBD.getMedidor());
		facturaPDF.setFechaUltimoPago(facturaBD.getFechaUltimoPago());
		facturaPDF.setMensajePuntoPago(ciclo.getMensajePuntoPago());
		facturaPDF.setMensajeReclamo(ciclo.getMensajeReclamo());
		facturaPDF.setOtrosCobros(FacturaUtil.getOtrosCobros(detalles, sistema));
		facturaPDF.setValorMesAnterior(facturaBD.getValorMesAnterior());
		facturaPDF.setCreditos(getCreditos(facturaBD.getInstalacion()));
		return facturaPDF;
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


	

}
