package co.com.siav.response;

import co.com.siav.utils.Constantes;


public class ConsultaFacturasResponse extends MensajeResponse{

	public ConsultaFacturasResponse(EstadoEnum estado, String mensaje) {
		super(estado, mensaje);
	}
	
	public ConsultaFacturasResponse(String mensaje) {
		super(mensaje);
	}
	
	public ConsultaFacturasResponse() {
	}

	public ConsultaFacturasResponse(Long ciclo, Long cantidadFisico, Long cantidadMail) {
		super.estado = EstadoEnum.INFO;
		super.mensaje = String.format(Constantes.FACTURA_CONSULTA, ciclo, cantidadFisico + cantidadMail, cantidadFisico, cantidadMail);
	}

	public ConsultaFacturasResponse(int cantidadFisico) {
		super.estado = EstadoEnum.INFO;
		super.mensaje = String.format(Constantes.FACTURA_SOLO_FISICO, cantidadFisico);
	}
		
}
