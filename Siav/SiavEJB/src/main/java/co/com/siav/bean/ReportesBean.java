package co.com.siav.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.com.siav.dto.ReporteLecturaConsumo;
import co.com.siav.entities.Consumo;
import co.com.siav.entities.Instalacion;
import co.com.siav.repositories.IRepositoryConsumos;
import co.com.siav.repositories.IRepositoryInstalaciones;

@Stateless
public class ReportesBean {
	
	@Inject
	private IRepositoryConsumos consumosRep;
	
	@Inject
	private IRepositoryInstalaciones instalacionesRep;

	public List<ReporteLecturaConsumo> consultar(Long ciclo) {
		List<ReporteLecturaConsumo> response = new ArrayList<ReporteLecturaConsumo>();
		List<Consumo> consumos = consumosRep.findByIdCiclo(ciclo);
		consumos.stream().forEach(consumo -> response.add(crearRespuesta(consumo)));
		return response;
	}

	private ReporteLecturaConsumo crearRespuesta(Consumo consumo) {
		ReporteLecturaConsumo reporte = new ReporteLecturaConsumo();
		reporte.setInstalacion(consumo.getId().getInstalacion());
		reporte.setPropietario(getPropietario(consumo.getId().getInstalacion()));
		reporte.setLecturaAnterior(consumo.getLecturaAnterior());
		reporte.setLecturaActual(consumo.getLecturaActual());
		reporte.setConsumo(consumo.getConsumoMes());
		reporte.setConsumoPromedio(consumo.getConsumoPromedio());
		return reporte;
	}

	private String getPropietario(Long numeroInstalacion) {
		Instalacion instalacion = instalacionesRep.findOne(numeroInstalacion);
		return instalacion.getUsuario().getNombres() + " " + instalacion.getUsuario().getApellidos();
	}

}
