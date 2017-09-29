package co.com.siav.reports.factory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import co.com.siav.repository.report.CarteraRepository;
import co.com.siav.repository.report.ConsolidadoConceptoRepository;
import co.com.siav.repository.report.ConsumosNoFacturadoRepository;
import co.com.siav.repository.report.CuentasVencidasRepository;
import co.com.siav.repository.report.DetalleRecaudoRepository;
import co.com.siav.repository.report.EstadisticasRepository;
import co.com.siav.repository.report.FacturaCicloRepository;
import co.com.siav.repository.report.FacturaRepository;
import co.com.siav.repository.report.InstalacionesRutaRepository;
import co.com.siav.repository.report.LecturasConsumosRepository;
import co.com.siav.repository.report.PrefacturaRepository;
import co.com.siav.repository.report.UsuarioRepository;
import co.com.siav.repository.report.VariacionConsumoRepository;

public class ReportFactory {
	
	@Inject
	private CuentasVencidasRepository cuentasVencidasRep;
	
	@Inject
	private PrefacturaRepository prefacturaRep;
	
	@Inject
	private FacturaRepository facturaRep;
	
	@Inject
	private FacturaCicloRepository facturaCicloRep;
	
	@Inject
	private UsuarioRepository usuarioRep;
	
	@Inject
	private VariacionConsumoRepository variacionRep;
	
	@Inject
	private LecturasConsumosRepository lecturasConsumosRep;
	
	@Inject
	private ConsumosNoFacturadoRepository consumosNoFacturadoRep;
	
	@Inject
	private EstadisticasRepository estadisticasRep;
	
	@Inject
	private CarteraRepository carteraRep;
	
	@Inject
	private ConsolidadoConceptoRepository consolidadoConceptoRep;
	
	@Inject
	private DetalleRecaudoRepository detalleRecaudoRep;
	
	@Inject
	private InstalacionesRutaRepository instalacionesRutaRep;
	
	private Map<String, IReportType> typeReport;

	public IReportType getInstance(String reporte) {
		createMap();
		return typeReport.get(reporte);
	}

	private void createMap() {
		typeReport = new HashMap<String, IReportType>();
		typeReport.put("cuentas-vencidas", cuentasVencidasRep);
		typeReport.put("prefactura", prefacturaRep);
		typeReport.put("factura", facturaRep);
		typeReport.put("usuarios-instalaciones", usuarioRep);
		typeReport.put("variacion-consumo", variacionRep);
		typeReport.put("lecturas-consumos", lecturasConsumosRep);
		typeReport.put("instalaciones-ruta", instalacionesRutaRep);
		typeReport.put("consumo-no-facturado", consumosNoFacturadoRep);
		typeReport.put("estadisticas", estadisticasRep);
		typeReport.put("consolidado-concepto", consolidadoConceptoRep);
		typeReport.put("detalle-recaudo", detalleRecaudoRep);
		typeReport.put("cartera", carteraRep);
		typeReport.put("factura-ciclo", facturaCicloRep);
	}

}
