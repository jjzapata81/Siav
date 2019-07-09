package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Asynchronous;
import javax.inject.Inject;

import co.com.siav.exception.TechnicalException;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.pdf.dto.FacturaBD;
import co.com.siav.pdf.dto.FacturaPDF;
import co.com.siav.pdf.dto.FacturacionPDF;
import co.com.siav.pdf.generador.GeneradorPDF;
import co.com.siav.reports.filters.Filter;
import co.com.siav.repository.ConsultaRango;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.UsuarioMail;
import co.com.siav.repository.utility.Util;
import co.com.siav.utility.Constantes;
import co.com.siav.utility.Formatter;
import co.com.siav.utility.Utilidades;

public class EnvioFacturaRepository extends FacturaBase{

	
	@Inject
	private SendMail notifier;


	public void send() {
		sistema = Util.getSistema();
		if(Constantes.SI.equals(sistema.getEnvioFactura())){
			empresa = Util.getEmpresa();
			ciclo = Util.getCicloPorEstado(Constantes.CERRADO);
			resolucion = Util.getParametro(new ConsultaRango(Constantes.ATT_RESOLUCION, Constantes.ESTADO_VIGENTE));
			articulos = getArticulos();
			List<UsuarioMail> usuarios = getUsuarios();
			Filter filter = new Filter();
			filter.setCiclo(ciclo.getCiclo());
			String query = QueryHelper.getFacturaEncabezado(filter);
			ReportBDFactory<FacturaBD> factory = new ReportBDFactory<>();
			List<FacturaBD> instalaciones = factory.getReportResult(FacturaBD.class, query);
			if(null == instalaciones || instalaciones.isEmpty()){
				throw new TechnicalException(Constantes.FACTURA_NO_EXISTE);
			}
			usuarios.stream().forEach(usuario -> get(usuario, getFiltro(instalaciones, usuarios)));
		}
		
	}
	
	public List<FacturaBD> getFiltro(List<FacturaBD> instalaciones, List<UsuarioMail> usuarios) {
		return instalaciones.stream().filter(item -> contains(usuarios, item)).collect(Collectors.toList());
	}

	public boolean contains(List<UsuarioMail> usuarios, FacturaBD factura) {
		return usuarios.stream().filter(item -> item.getCedula().equals(factura.getCedula()) && Utilidades.emailValido(item.getEmail())).findFirst().isPresent();
	}

	private void sendMail(UsuarioMail usuario, List<FacturaPDF> facturasPDF) {
		notifier.send(usuario.getEmail(),String.format(Reporte.FACTURA_EMAIL_ASUNTO, Formatter.getNombreMes(ciclo.getFecha())), 
				String.format(Reporte.FACTURA_EMAIL_CUERPO, 
						Formatter.getNombreMes(ciclo.getFecha()), empresa.getTelefono(), empresa.getDireccion(), empresa.getCiudad()), getFile(usuario.getCedula(), facturasPDF));
	}
	
	private Attachment getFile(String cedula, List<FacturaPDF> facturasPDF){
		return new Attachment(String.format(Reporte.FACTURA_EMAIL_ATACH, cedula, Formatter.getMes(ciclo.getFecha())), new GeneradorPDF(getFactura(facturasPDF), Constantes.FACTURA_JRXML).getStream());
	}
	
	private List<FacturacionPDF> getFactura(List<FacturaPDF> facturasPDF) {
		FacturacionPDF factura = new FacturacionPDF();
		factura.setFacturas(facturasPDF);
		return Arrays.asList(factura);
	}

	@Asynchronous
	private void get(UsuarioMail usuario, List<FacturaBD> facturas){
		List<FacturaPDF> facturasPDF = facturas.stream().filter(factura -> usuario.getCedula().equals(factura.getCedula())).map(this::transform).collect(Collectors.toList());
		sendMail(usuario, facturasPDF);
	}

	private List<UsuarioMail> getUsuarios() {
		String query = QueryHelper.getUsuariosMail();
		ReportBDFactory<UsuarioMail> factory = new ReportBDFactory<>();
		return factory.getReportResult(UsuarioMail.class, query).stream()
				.filter(usuario -> Utilidades.emailValido(usuario.getEmail())).collect(Collectors.toList());
	}

}
