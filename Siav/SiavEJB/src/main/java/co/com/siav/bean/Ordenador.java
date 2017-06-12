package co.com.siav.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.siav.entities.IEstrato;
import co.com.siav.repositories.IRepositoryExcesos;
import co.com.siav.repositories.IRepositoryTarifas;


public class Ordenador {
	
	@Inject
	private IRepositoryTarifas tarifasRep;
	
	@Inject
	private IRepositoryExcesos excesosRep;
	
	private List<OrdenPago> orden;
	
	public List<OrdenPago> get(){
		if(orden==null){
			prepare();
		}
		return orden;
	}

	private void add(IEstrato item) {
		orden.add(new OrdenPago(item.getOrden(), item.getCodigo()));
	}

	public void prepare() {
		orden = new ArrayList<OrdenPago>();
		tarifasRep.findAllByOrden().stream().forEachOrdered(item -> add(item));
		excesosRep.findAllByOrden().stream().forEachOrdered(item -> add(item));
	}

}
