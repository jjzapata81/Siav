package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.com.siav.exception.TechnicalException;
import co.com.siav.pdf.dto.FacturaBD;
import co.com.siav.pdf.dto.FacturaPDF;
import co.com.siav.pdf.dto.FacturacionPDF;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.ConsultaRango;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.UsuarioMail;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;
import co.com.siav.utility.Utilidades;

public class FacturaCicloRepository extends FacturaBase implements IReportType{

	

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
		//TODO: Cuando definan el envío automático por email, aca se debe agregar la logica
//		notifier.send(filter.getEmail(),Reporte.FACTURACION_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private List<FacturacionPDF> getFactura() {
		FacturacionPDF factura = new FacturacionPDF();
		factura.setFacturas(getFacturas());
		return Arrays.asList(factura);
	}
	
	
	private void getValoresGenerales() {
		empresa = Util.getEmpresa();
		ciclo = Util.getCicloPorEstado(Constantes.CERRADO);
		sistema = Util.getSistema();
		resolucion = Util.getParametro(new ConsultaRango(Constantes.ATT_RESOLUCION, Constantes.ESTADO_VIGENTE));
		articulos = getArticulos();
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
			return instalaciones.stream().filter(item -> !contains(usuarios, item)).map(this::transform).collect(Collectors.toList());
		}else{
			return instalaciones.stream().map(this::transform).collect(Collectors.toList());
		}
	}


	private boolean contains(List<UsuarioMail> usuarios, FacturaBD factura) {
		return usuarios.stream().filter(item -> item.getCedula().equals(factura.getCedula()) && Utilidades.emailValido(item.getEmail())).findFirst().isPresent();
	}

	private List<UsuarioMail> getUsuarios() {
		String query = QueryHelper.getUsuariosMail();
		ReportBDFactory<UsuarioMail> factory = new ReportBDFactory<>();
		return factory.getReportResult(UsuarioMail.class, query).stream()
				.filter(usuario -> Utilidades.emailValido(usuario.getEmail())).collect(Collectors.toList());
	}

}
