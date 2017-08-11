package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import co.com.siav.pdf.dto.AbonoFacturaBD;
import co.com.siav.pdf.dto.AbonoPDF;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.filters.Comprobante;
import co.com.siav.repository.ConsultaRango;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Ciclo;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.entities.Parametro;
import co.com.siav.repository.utility.Util;
import co.com.siav.rest.request.AbonoRequest;
import co.com.siav.rest.request.MatriculaRequest;
import co.com.siav.utility.Constantes;

public class AbonoRepository {
	
	private static final String CERRADO = "CERRADO";
	private Empresa empresa;
	private Ciclo ciclo;
	private String resolucion;
	private static final String SIN_INFO = "SIN INFO"; 

	public byte[] getPDF(AbonoRequest request) {
		getValoresGenerales();
		Comprobante comprobante = generarComprobante(request);
		return new GeneradorPDF(getData(comprobante), Constantes.ABONO_JRXML).getStream();
	}
	
	public byte[] getMatriculaPDF(MatriculaRequest request) {
		getValoresGenerales();
		if(request.isNuevo()){
			guardarUsuario(request);
		}
		request.setEsMatricula("S");
		Comprobante comprobante = generarComprobante(request);
		return new GeneradorPDF(getAbonoMatricula(request, comprobante), Constantes.MATRICULA_JRXML).getStream();
	}

	private List<AbonoPDF> getAbonoMatricula(MatriculaRequest request,Comprobante comprobante) {
		AbonoPDF abono = new AbonoPDF();
		abono.setCiclo(String.valueOf(ciclo.getCiclo()));
		abono.setDireccion(request.getDireccion());
		abono.setEstrato(SIN_INFO);
		abono.setValorTotal(request.getValor());
		abono.setVereda(SIN_INFO);
		abono.setNombre(null == request.getNombres() ? request.getApellidos().toUpperCase() : request.getNombres().toUpperCase() + " " + request.getApellidos().toUpperCase());
		abono.setFePagoRecargo(ciclo.getFeconrecargo());
		abono.setFePagoSinRecargo(ciclo.getFesinrecargo());
		abono.setInstalacion("0000");
		abono.setMensajePrincipal(ciclo.getMensaje());
		abono.setMensajePuntoPago(ciclo.getMensajePuntoPago());
		abono.setMensajeReclamo(ciclo.getMensajeReclamo());
		abono.setNit(empresa.getNit());
		abono.setNombreAcueducto(empresa.getNombreCorto());
		abono.setNumeroFactura(String.valueOf(comprobante.getComprobante()));
		abono.setReferente(getReferente(0L, comprobante.getComprobante(), ciclo.getCiclo()));
		abono.setResolucion(resolucion);
		abono.setCodigoBarras(Util.getCodigoBarras(abono.getReferente(), abono.getValorTotal(), abono.getFePagoRecargo() == null ? abono.getFePagoSinRecargo() : abono.getFePagoRecargo()));
		return Arrays.asList(abono);
	}

	private Comprobante generarComprobante(AbonoRequest request) {
		Comprobante comprobante = new Comprobante();
		comprobante.setComprobante(getComprobante());
		comprobante.setInstalacion(request.getNumeroInstalacion());
		comprobante.setCedula(request.getCedula());
		comprobante.setValor(request.getValor());
		comprobante.setFecha(new Date());
		comprobante.setUsuario(request.getUsuario());
		comprobante.setCredito(request.getNumeroCredito());
		comprobante.setEsMatricula(request.getEsMatricula());
		guardarBD(comprobante);
		return comprobante;
	}

	private Long getComprobante() {
		String query = QueryHelper.getComprobante();
		ReportBDFactory<Parametro> factory = new ReportBDFactory<>();
		String parametro = factory.getReportResult(Parametro.class, query).get(0).getParametro();
		return null == parametro ? 990001L : Long.valueOf(parametro) + 1L;
	}

	private void guardarBD(Comprobante comprobante) {
		String query = QueryHelper.saveComprobante(comprobante);
		ReportBDFactory<AbonoFacturaBD> factory = new ReportBDFactory<>();
		factory.save(query);
	}

	private void getValoresGenerales() {
		empresa = Util.getEmpresa();
		ciclo = Util.getCicloPorEstado(CERRADO);
		resolucion = Util.getParametro(new ConsultaRango("resolucion", "VIGENTE"));
	}
	
	private List<AbonoPDF> getData(Comprobante comprobante) {
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
		abono.setReferente(getReferente(comprobante.getInstalacion(), comprobante.getComprobante(), ciclo.getCiclo()));
		abono.setResolucion(resolucion);
		abono.setCodigoBarras(Util.getCodigoBarras(abono.getReferente(), abono.getValorTotal(), abono.getFePagoRecargo() == null ? abono.getFePagoSinRecargo() : abono.getFePagoRecargo()));
		return Arrays.asList(abono);
	}
	
	private AbonoFacturaBD getFactura(Comprobante comprobante) {
		String query = QueryHelper.getFacturaAbono(comprobante.getInstalacion());
		ReportBDFactory<AbonoFacturaBD> factory = new ReportBDFactory<>();
		return factory.getReportResult(AbonoFacturaBD.class, query).get(0);
	}

	private String getReferente(Long numeroInstalacion, Long numeroFactura, Long ciclo) {
		if(numeroInstalacion.equals(0L)){
			return String.format("000%s%s", numeroFactura, ciclo);
		}
		return String.format("%s%s%s", numeroInstalacion, numeroFactura, ciclo);
	}

	private void guardarUsuario(MatriculaRequest request) {
		String query = QueryHelper.saveUsuario(request);
		ReportBDFactory<AbonoFacturaBD> factory = new ReportBDFactory<>();
		factory.save(query);
		
	}

}
