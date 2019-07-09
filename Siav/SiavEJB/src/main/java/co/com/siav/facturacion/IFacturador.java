package co.com.siav.facturacion;

import java.util.List;

import co.com.siav.entities.CreditoDetalle;
import co.com.siav.entities.CreditoMaestro;
import co.com.siav.entities.Exceso;
import co.com.siav.entities.Factura;
import co.com.siav.entities.IEstrato;
import co.com.siav.entities.Material;
import co.com.siav.entities.Novedad;
import co.com.siav.entities.Tarifa;

public interface IFacturador {
	
	void setTarifas(Exceso basico, Exceso complementario, Exceso suntuario);
	
	void setEstrato(IValorEstrato valorEstrato);
	
	void setConceptosFacturacion(Tarifa cargoFijo, Tarifa conceptoInteres, Tarifa cuentaVencida, List<Tarifa> tarifas);
	
	List<Concepto> generar(Long consumoPeriodo, String estrato);
	
	Long getValor(IEstrato tarifa, String estrato);

	Concepto getCargoFijo(String estrato);

	ConceptoCredito getCredito(CreditoDetalle creditoDetalle, String codigoConcepto);
	
	ConceptoCredito getInteres(CreditoMaestro credito);

	Concepto getNovedad(Novedad novedad, String estrato);
	
	Concepto getMateriales(Material material);

	void setCuentasVencidas(Factura facturaAnterior);

	List<Concepto> getOtrosVencidos();

}
