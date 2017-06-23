package co.com.techandsolve.lazy.bean;

import co.com.techandsolve.lazy.dto.Tasks;

public class Distributor {
	
	private final static int PESO = 50;
	private int viajes;
	private Double distribution;

	public String run(Tasks task) {
		viajes = 0;
		distribution = 0D;
		task.getWeights().stream().sorted((a, b)-> Integer.compare(b, a)).forEachOrdered(item -> calculo(item, task.getWeights().size()));
		return "case # : " + viajes;
	}
	
	private void calculo(Integer weight, int size){
		distribution += (Math.ceil(PESO / weight.doubleValue()));
		if(distribution.intValue() <= size){
			viajes++;
		}
	}

}
