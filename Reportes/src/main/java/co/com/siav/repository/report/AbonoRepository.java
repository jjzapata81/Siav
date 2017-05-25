package co.com.siav.repository.report;

import co.com.siav.pdf.dto.AbonoFacturaBD;
import co.com.siav.pdf.dto.AbonoPDF;
import co.com.siav.pdf.generador.GeneradorAbono;
import co.com.siav.repository.ConsultaRango;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportFactory;
import co.com.siav.repository.entities.Ciclo;
import co.com.siav.repository.entities.Empresa;
import co.com.siav.repository.utility.Util;
import co.com.siav.rest.request.AbonoRequest;

public class AbonoRepository {
	
	private static final String CERRADO = "CERRADO";
	private static final String CICLO_DEFECTO = "999";
	private Empresa empresa;
	private Ciclo ciclo;
	private String resolucion;

	public byte[] getPDF(AbonoRequest request) {
		getValoresGenerales();
		GeneradorAbono generador = new GeneradorAbono(getAbono(request));
		return generador.generarPDFStream();
	}

	private void getValoresGenerales() {
		empresa = Util.getEmpresa();
		ciclo = Util.getCicloPorEstado(CERRADO);
		resolucion = Util.getParametro(new ConsultaRango("resolucion", "VIGENTE"));
	}
	
	private AbonoPDF getAbono(AbonoRequest request) {
		AbonoPDF abono = new AbonoPDF();
		AbonoFacturaBD factura = getFactura(request);
		abono.setCiclo(String.valueOf(ciclo.getCiclo()));
		abono.setDireccion(factura.getDireccion());
		abono.setEstrato(factura.getEstrato());
		abono.setValorTotal(request.getValor());
		abono.setVereda(factura.getVereda());
		abono.setNombre(factura.getNombre());
		abono.setFePagoRecargo(ciclo.getFeconrecargo());
		abono.setFePagoSinRecargo(ciclo.getFesinrecargo());
		abono.setInstalacion(String.valueOf(request.getNumeroInstalacion()));
		abono.setMensajePrincipal(ciclo.getMensaje());
		abono.setMensajePuntoPago(ciclo.getMensajePuntoPago());
		abono.setMensajeReclamo(ciclo.getMensajeReclamo());
		abono.setNit(empresa.getNit());
		abono.setNombreAcueducto(empresa.getNombreCorto());
		abono.setNumeroFactura(String.valueOf(request.getNumeroFactura()));
		abono.setReferente(getReferente(request.getNumeroInstalacion(), request.getNumeroFactura()));
		abono.setResolucion(resolucion);
		abono.setCodigoBarras(Util.getCodigoBarras(abono.getReferente(), abono.getValorTotal(), abono.getFePagoRecargo() == null ? abono.getFePagoSinRecargo() : abono.getFePagoRecargo()));
		return abono;
	}
	
	private AbonoFacturaBD getFactura(AbonoRequest request) {
		String query = QueryHelper.getFacturaAbono(request.getNumeroInstalacion());
		ReportFactory<AbonoFacturaBD> factory = new ReportFactory<>();
		return factory.getReportResult(AbonoFacturaBD.class, query).get(0);
	}

	private String getReferente(Long numeroInstalacion, Long numeroFactura) {
		return String.format("%s%s%s", numeroInstalacion, numeroFactura, CICLO_DEFECTO);
	}

}
