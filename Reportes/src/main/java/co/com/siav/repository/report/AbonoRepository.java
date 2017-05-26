package co.com.siav.repository.report;

import java.util.Date;

import co.com.siav.pdf.dto.AbonoFacturaBD;
import co.com.siav.pdf.dto.AbonoPDF;
import co.com.siav.pdf.generador.GeneradorAbono;
import co.com.siav.reports.filters.Comprobante;
import co.com.siav.repository.ConsultaRango;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.entities.Ciclo;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.entities.Parametro;
import co.com.siav.repository.utility.Util;
import co.com.siav.rest.request.AbonoRequest;

public class AbonoRepository {
	
	private static final String CERRADO = "CERRADO";
	private static final String CICLO_DEFECTO = "999";
	private Empresa empresa;
	private Ciclo ciclo;
	private String resolucion;

	public byte[] getPDF(String usuario, AbonoRequest request) {
		getValoresGenerales();
		Comprobante comprobante = generarComprobante(usuario, request);
		GeneradorAbono generador = new GeneradorAbono(getAbono(comprobante));
		return generador.generarPDFStream();
	}

	private Comprobante generarComprobante(String usuario, AbonoRequest request) {
		Comprobante comprobante = new Comprobante();
		comprobante.setComprobante(getComprobante());
		comprobante.setInstalacion(request.getNumeroInstalacion());
		comprobante.setCedula(request.getCedula());
		comprobante.setValor(request.getValor());
		comprobante.setFecha(new Date());
		comprobante.setUsuario(usuario);
		comprobante.setCredito(request.getNumeroCredito());
		guardarBD(comprobante);
		return comprobante;
	}

	private Long getComprobante() {
		String query = QueryHelper.getComprobante();
		ReportFactory<Parametro> factory = new ReportFactory<>();
		String parametro = factory.getReportResult(Parametro.class, query).get(0).getParametro();
		return null == parametro ? 990001L : Long.valueOf(parametro) + 1L;
	}

	private void guardarBD(Comprobante comprobante) {
		String query = QueryHelper.saveComprobante(comprobante);
		ReportFactory<AbonoFacturaBD> factory = new ReportFactory<>();
		factory.save(query);
	}

	private void getValoresGenerales() {
		empresa = Util.getEmpresa();
		ciclo = Util.getCicloPorEstado(CERRADO);
		resolucion = Util.getParametro(new ConsultaRango("resolucion", "VIGENTE"));
	}
	
	private AbonoPDF getAbono(Comprobante comprobante) {
		AbonoPDF abono = new AbonoPDF();
		AbonoFacturaBD factura = getFactura(comprobante);
		abono.setCiclo(String.valueOf(ciclo.getCiclo()));
		abono.setDireccion(factura.getDireccion());
		abono.setEstrato(factura.getEstrato());
		abono.setValorTotal(comprobante.getValor());
		abono.setVereda(factura.getVereda());
		abono.setNombre(factura.getNombre());
		abono.setFePagoRecargo(ciclo.getFeconrecargo());
		abono.setFePagoSinRecargo(ciclo.getFesinrecargo());
		abono.setInstalacion(String.valueOf(comprobante.getInstalacion()));
		abono.setMensajePrincipal(ciclo.getMensaje());
		abono.setMensajePuntoPago(ciclo.getMensajePuntoPago());
		abono.setMensajeReclamo(ciclo.getMensajeReclamo());
		abono.setNit(empresa.getNit());
		abono.setNombreAcueducto(empresa.getNombreCorto());
		abono.setNumeroFactura(String.valueOf(comprobante.getComprobante()));
		abono.setReferente(getReferente(comprobante.getInstalacion(), comprobante.getComprobante()));
		abono.setResolucion(resolucion);
		abono.setCodigoBarras(Util.getCodigoBarras(abono.getReferente(), abono.getValorTotal(), abono.getFePagoRecargo() == null ? abono.getFePagoSinRecargo() : abono.getFePagoRecargo()));
		return abono;
	}
	
	private AbonoFacturaBD getFactura(Comprobante comprobante) {
		String query = QueryHelper.getFacturaAbono(comprobante.getInstalacion());
		ReportFactory<AbonoFacturaBD> factory = new ReportFactory<>();
		return factory.getReportResult(AbonoFacturaBD.class, query).get(0);
	}

	private String getReferente(Long numeroInstalacion, Long numeroFactura) {
		return String.format("%s%s%s", numeroInstalacion, numeroFactura, CICLO_DEFECTO);
	}

}
