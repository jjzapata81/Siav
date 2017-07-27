package co.com.siav.repository.report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.com.siav.file.excel.ExcelReportGeneratorXLSX;
import co.com.siav.file.excel.descriptor.UsuariosExcelDescriptor;
import co.com.siav.notifier.SendMail;
import co.com.siav.notifier.config.Attachment;
import co.com.siav.notifier.reports.name.Reporte;
import co.com.siav.reports.factory.IReportType;
import co.com.siav.reports.filters.Filter;
import co.com.siav.reports.response.UsuariosExcel;
import co.com.siav.reports.response.UsuariosInstalaciones;
import co.com.siav.repository.QueryHelper;
import co.com.siav.repository.ReportBDFactory;
import co.com.siav.repository.entities.Instalacion;
import co.com.siav.repository.entities.UsuariosBD;

public class UsuarioRepository implements IReportType{
	
	@Inject
	private SendMail notifier;

	public List<UsuariosInstalaciones> getUsuarios(Filter filtro) {
		 return getUsuariosBD(filtro).stream().map(this::transform).collect(Collectors.toList());
	}
	
	private UsuariosInstalaciones transform(UsuariosBD userBD){
		UsuariosInstalaciones usuario = new UsuariosInstalaciones();
		usuario.setNombres(userBD.getNombres());
		usuario.setApellidos(userBD.getApellidos());
		usuario.setCedula(userBD.getCedula());
		usuario.setCelular(userBD.getCelular());
		usuario.setTelefono(userBD.getTelefono());
		usuario.setEmail(userBD.getEmail());
		usuario.setInstalaciones(getInstalaciones(userBD.getCedula()));
		return usuario;
	}
	
	private List<UsuariosBD> getUsuariosBD(Filter filtro) {
		String query = QueryHelper.getUsuarios(filtro);
		ReportBDFactory<UsuariosBD> factory = new ReportBDFactory<>();
		return factory.getReportResult(UsuariosBD.class, query);
	}
	
	private List<Instalacion> getInstalaciones(String cedula) {
		String query = QueryHelper.getInstalaciones(cedula);
		ReportBDFactory<Instalacion> factory = new ReportBDFactory<>();
		return factory.getReportResult(Instalacion.class, query);
	}
	
	private List<UsuariosExcel> getUsuariosExcel(Filter filtro) {
		String query = QueryHelper.getUsuariosExcel(filtro);
		ReportBDFactory<UsuariosExcel> factory = new ReportBDFactory<>();
		return factory.getReportResult(UsuariosExcel.class, query);
	}

	@Override
	public byte[] getPDF(Filter filter) {
		return null;
	}

	@Override
	public byte[] download(Filter filter) {
		ExcelReportGeneratorXLSX<UsuariosExcel> generator = new ExcelReportGeneratorXLSX<UsuariosExcel>();
		return generator.generate(getUsuariosExcel(filter), Arrays.asList(UsuariosExcelDescriptor.values()));
	}

	@Override
	public void send(Filter filter) {
		notifier.send(filter.getEmail(),Reporte.USUARIO_INSTALACIONES_ASUNTO, getTextoMensaje(filter.getCiclo()), getFile(filter));
	}
	
	private String getTextoMensaje(Long ciclo) {
		return Reporte.USUARIO_INSTALACIONES_CUERPO;
	}

	private Attachment getFile(Filter filter){
		return new Attachment(Reporte.USUARIO_INSTALACIONES, download(filter));
	}

}
