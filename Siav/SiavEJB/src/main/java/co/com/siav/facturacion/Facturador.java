package co.com.siav.facturacion;

import java.util.ArrayList;
import java.util.List;

import co.com.siav.exception.ExcepcionTecnica;

public class Facturador extends FacturadorBase implements IFacturador {
	
	public List<Concepto> generar(Long consumoPeriodo, String estrato) {
		if(null == basico || null == complementario || null == suntuario){
			throw new ExcepcionTecnica("No se han asignado las tarifas de consumos");
		}
		
		Long consumoBasico = 0L;
		Long consumoComplementario = 0L;
		Long consumoSuntuario = 0L;
		Long valorBasico = 0L;
		Long valorComplementario = 0L;
		Long valorSuntuario = 0L;
				
		if(consumoPeriodo <= basico.getLimFinal()){
			valorBasico = valorEstrato.getValor(basico, estrato);
			consumoBasico = consumoPeriodo;
		}
		else if(consumoPeriodo <= complementario.getLimFinal()){
			valorBasico = valorEstrato.getValor(basico, estrato);
			consumoBasico = basico.getLimFinal();
			valorComplementario = valorEstrato.getValor(complementario, estrato);
			consumoComplementario = consumoPeriodo - consumoBasico;
		}
		else if(consumoPeriodo <= suntuario.getLimFinal()){
			valorBasico = valorEstrato.getValor(basico, estrato);
			consumoBasico = basico.getLimFinal();
			valorComplementario = valorEstrato.getValor(complementario, estrato);
			consumoComplementario = complementario.getLimFinal() - consumoBasico;
			valorSuntuario = valorEstrato.getValor(suntuario, estrato);
			consumoSuntuario = consumoPeriodo - consumoComplementario - consumoBasico;
		}
		
		conceptos = new ArrayList<Concepto>();
		conceptos.add(crearConcepto(basico.getCodigo(), basico.getDescripcion(), consumoBasico, valorBasico, getVencido(basico.getCodigo())));
		conceptos.add(crearConcepto(complementario.getCodigo(), complementario.getDescripcion(), consumoComplementario, valorComplementario, getVencido(complementario.getCodigo())));
		conceptos.add(crearConcepto(suntuario.getCodigo(), suntuario.getDescripcion(), consumoSuntuario, valorSuntuario, getVencido(suntuario.getCodigo())));
		return conceptos;
	}

}
