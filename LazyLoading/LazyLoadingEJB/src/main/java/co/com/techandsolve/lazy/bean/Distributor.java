package co.com.techandsolve.lazy.bean;

import co.com.techandsolve.lazy.dto.Tasks;

public final class Distributor {
	
	private static int viajes;
	private static Double distribution;
	
	private Distributor(){
		super();
	}

	public static int run(Tasks task, int fixedWeight) {
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
