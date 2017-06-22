package co.com.techandsolve.lazy.bean;

import java.util.List;

import co.com.techandsolve.lazy.dto.Tasks;

public class Distributor {
	
	public final static int MINIMO = 5;

	public String run(Tasks task) {
		task.getWeights().stream().sorted().collect(new WeightsCollector())
		return "";
	}
	
	private List<Integer> get(Integer item, List<Integer> weights) {
		
		return null;
	}

}
