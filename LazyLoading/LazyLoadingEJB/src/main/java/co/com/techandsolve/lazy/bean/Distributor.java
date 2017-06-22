package co.com.techandsolve.lazy.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import co.com.techandsolve.lazy.dto.Tasks;

public class Distributor {
	
	public final static int PESO = 50;

	public String run(Tasks task) {
//		task.getWeights().stream().sorted().collect(new WeightsCollector());
		List<Integer> orderedWeights = task.getWeights().stream().sorted().collect(Collectors.toList());
		Integer maxValue = task.getWeights().stream().reduce((a, b) -> a > b ? a : b).orElse(0);
		List<Integer> reverseWeights = new ArrayList<Integer>(orderedWeights);
		Collections.reverse(reverseWeights);
		System.out.println("Ascendente: " + orderedWeights);
		System.out.println("Descendente: " + reverseWeights);
		System.out.println("Valor maximo: " + maxValue);
		
		return "";
	}

}
