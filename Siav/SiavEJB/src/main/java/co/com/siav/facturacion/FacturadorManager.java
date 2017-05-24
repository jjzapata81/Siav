package co.com.siav.facturacion;


public class FacturadorManager {
	
	public static IFacturador getInstance(boolean esPorRango) {
		if(esPorRango){
			return new FacturadorRango();
		}
		return new Facturador();
	}
}
