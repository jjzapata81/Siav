package co.com.siav.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;

import co.com.siav.entities.Exceso;
import co.com.siav.entities.IEstrato;
import co.com.siav.entities.Sistema;
import co.com.siav.entities.Tarifa;
import co.com.siav.repositories.IRepositoryExcesos;
import co.com.siav.repositories.IRepositorySistema;
import co.com.siav.repositories.IRepositoryTarifas;
import co.com.siav.response.EstadoEnum;
import co.com.siav.response.MensajeResponse;
import co.com.siav.utils.Constantes;

@Stateless
public class ContabilidadBean {
	
	@Inject
	private IRepositorySistema sistemaRep;
	
	@Inject
	private IRepositoryTarifas tarifasRep;
	
	@Inject
	private IRepositoryExcesos excesosRep;

	public Sistema consultar() {
		return sistemaRep.findOne(1L);
	}

	public MensajeResponse guardar(Sistema request) {
		try{
			Sistema sistema = consultar();
			StringBuilder sb = new StringBuilder();
			if(sistema.getEsPorEstrato() != request.getEsPorEstrato()){
				sb.append(Constantes.ACTUALIZACION_POR_ESTRATO);
				eliminarValores();
			}
			actualizarCodigos(sistema, request);
			BeanUtils.copyProperties(request, sistema);
			sistemaRep.save(sistema);
			sb.append(Constantes.ACTUALIZACION_EXITO);
			return new MensajeResponse(sb.toString());
		}catch(Exception e){
			return new MensajeResponse(EstadoEnum.ERROR, Constantes.ACTUALIZACION_FALLO);
		}
	}

	private void eliminarValores() {
		List<Exceso> excesos = excesosRep.findAll();
		excesos.stream().forEach(exceso -> resetExceso(exceso));
		List<Tarifa> tarifas = tarifasRep.findAll();
		tarifas.stream().forEach(tarifa -> resetTarifa(tarifa));
	}

	private void resetExceso(Exceso exceso) {
		excesosRep.save((Exceso)resetValor(exceso));
	}
	
	private void resetTarifa(Tarifa tarifa) {
		tarifasRep.save((Tarifa)resetValor(tarifa));
	}

	private IEstrato resetValor(IEstrato concepto) {
		concepto.setEstrato0(0L);
		concepto.setEstrato1(0L);
		concepto.setEstrato2(0L);
		concepto.setEstrato3(0L);
		concepto.setEstrato4(0L);
		concepto.setEstrato5(0L);
		concepto.setEstrato6(0L);		
		return concepto;
	}

	private void actualizarCodigos(Sistema sistemaBD, Sistema request) {
		compararValorExceso(sistemaBD.getIdBasico(), request.getIdBasico());
		compararValorExceso(sistemaBD.getIdComplementario(), request.getIdComplementario());
		compararValorExceso(sistemaBD.getIdSuntuario(), request.getIdSuntuario());
		compararValor(sistemaBD.getIdCargoFijo(), request.getIdCargoFijo());
		compararValor(sistemaBD.getIdReconexion(), request.getIdReconexion());
		compararValor(sistemaBD.getIdRecargo(), request.getIdRecargo());
		compararValor(sistemaBD.getIdMoroso(), request.getIdMoroso());
		compararValor(sistemaBD.getIdDerecho(), request.getIdDerecho());
		compararValor(sistemaBD.getIdInteres(), request.getIdInteres());
		compararValor(sistemaBD.getIdSaldoFavor(), request.getIdSaldoFavor());
		
	}

	private void compararValorExceso(String codigoBD, String codigoRequest) {
		if(!codigoBD.equalsIgnoreCase(codigoRequest)){
			Exceso exceso = excesosRep.findByCodigo(codigoBD);
			exceso.setCodigo(codigoRequest);
			excesosRep.save(exceso);
		}
		
	}

	private void compararValor(String codigoBD, String codigoRequest) {
		if(!codigoBD.equalsIgnoreCase(codigoRequest)){
			Tarifa tarifa = tarifasRep.findOne(codigoBD);
			tarifa.setCodigo(codigoRequest);
			tarifasRep.save(tarifa);
		}
		
	}

}
