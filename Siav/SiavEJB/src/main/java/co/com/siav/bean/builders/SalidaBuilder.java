package co.com.siav.bean.builders;

import co.com.siav.entities.SalidaMaestro;
import co.com.siav.request.MaterialRequest;

public class SalidaBuilder {
	
	public static SalidaMaestro crearSalidaBD(MaterialRequest request, Long codigo, Long ciclo) {
		SalidaMaestro salida = new SalidaMaestro();
		salida.setCodigo(codigo);
		salida.setCiclo(ciclo);
		salida.setCodDestino(request.getInstalacion());
		salida.setFechaOrdenSalida(request.getFecha());
		return salida;
	}

}
