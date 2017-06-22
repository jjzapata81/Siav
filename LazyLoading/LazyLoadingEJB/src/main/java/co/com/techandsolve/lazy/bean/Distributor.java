package co.com.techandsolve.lazy.bean;

import co.com.techandsolve.lazy.dto.Tasks;

public class Distributor {
	
	public final static int MINIMO = 5;

	public String run(Tasks task) {
		task.getWeights().stream().map(this::select);
		return "";
	}
	
	private String select(Integer weight){
		return "";
	}

}
