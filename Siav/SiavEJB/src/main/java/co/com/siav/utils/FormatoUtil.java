package co.com.siav.utils;

import co.com.siav.entities.FormatoRecaudo;
import co.com.siav.entities.Pago;
import co.com.siav.exception.ExcepcionNegocio;

public final class FormatoUtil {
	
	private FormatoUtil(){
		super();
	}

	public static Pago convert(String linea, FormatoRecaudo formato, String codigoCuenta, String rutaFinal, String usuario) {
		validar(codigoCuenta);
		try{
			String[] atributos = linea.split(formato.getSeparador());
			Pago pago = new Pago();
			pago.setFecha(Utilidades.setFechaPago(atributos[formato.getFecha()].trim(), formato.getFormatoFecha()));
			pago.setNumeroFactura(getValorAuxiliar(atributos[formato.getReferencia()], formato));
			pago.setValor(getValor(atributos, formato.getValor()));
			pago.setCodigoCuenta(codigoCuenta);
			pago.setUsuario(usuario);
			pago.setError(false);
			pago.setRutaPago(rutaFinal);
			pago.setEsCredito(false);
			return pago;
		}catch(Exception e){
			throw new ExcepcionNegocio(Constantes.ERR_ESTRUCTURA_ARCHIVO);
		}
	}
	
	private static Long getValor(String[] atributos, int valor) {
		return Long.valueOf(atributos[valor].trim());
	}

	private static String getValorAuxiliar(String valor, FormatoRecaudo formato) {
		String referencia = valor.split(formato.getSeparadorAux())[formato.getPosicionAux()].trim();
		return referencia.substring(formato.getPosicionInicialFactura(), formato.getPosicionFinalFactura());
	}
	
	private static void validar(String codigoCuenta) {
		if(codigoCuenta == null || codigoCuenta.trim() == ""){
			throw new ExcepcionNegocio(Constantes.ERR_SIN_CODIGO_CUENTA);
		}
	}

}
