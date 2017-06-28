package co.com.techandsolve.lazy.util;

import co.com.techandsolve.lazy.dto.Tasks;
import co.com.techandsolve.lazy.exception.BusinessException;

public final class Distributor {
	
	private static int viajes;
	private static Double distribution;
	
	private Distributor(){
		super();
	}

	public static int run(Tasks task, int fixedWeight) {
		if(task == null){
			throw new BusinessException(LazyConstants.ERR_LISTA_NULA);
		}
		if(fixedWeight == 0){
			throw new BusinessException(LazyConstants.ERR_PESO_CERO);
		}
		viajes = 0;
		distribution = 0D;
		task.getWeights().stream().sorted((a, b)-> Integer.compare(b, a)).forEachOrdered(item -> distribute(item, task.getWeights().size(), fixedWeight));
		System.out.println(viajes);
		return viajes;
	}
	
	private static void distribute(Integer weight, int size, int fixedWeight){
		distribution += (Math.ceil(fixedWeight / weight.doubleValue()));
		if(distribution.intValue() <= size){
			viajes++;
		}
	}

}
