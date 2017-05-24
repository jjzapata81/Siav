package co.com.siav.facturacion;


public class EstratoManager {
	
	public static IValorEstrato getInstance(boolean esPorEstrato) {
		if(esPorEstrato){
			return new ConEstrato();
		}
		return new SinEstrato();
	}

}
