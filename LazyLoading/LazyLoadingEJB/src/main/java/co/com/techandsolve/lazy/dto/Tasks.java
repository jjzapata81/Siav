package co.com.techandsolve.lazy.dto;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
	
	private List<Integer> weights;
	
	public List<Integer> getWeights() {
		return weights == null ? new ArrayList<Integer>() : weights;
	}

	public void setWeights(List<Integer> weights) {
		this.weights = weights;
	}
	
}
