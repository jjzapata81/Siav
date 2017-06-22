package co.com.techandsolve.lazy.collector;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import co.com.techandsolve.lazy.dto.Tasks;

public class DaysCollector implements Collector<Integer, Creator, List<Tasks>>{

	@Override
	public Supplier<Creator> supplier() {
		return () -> new Creator();
	}

	@Override
	public BiConsumer<Creator, Integer> accumulator() {
		return(creator, item)->{
			if(creator.isFirstElement()){
				creator.setNumberOfElements(item);
				creator.setFirstElement(false);
			}else{
				creator.add(item);
				if(creator.size() ==  creator.getNumberOfElements()){
					creator.init();
				}
			}
		};
	}

	@Override
	public BinaryOperator<Creator> combiner() {
		return null;
	}

	@Override
	public Function<Creator, List<Tasks>> finisher() {
		return (data)->{
			return data.getElements().stream().map(this::transform).collect(Collectors.toList());
		};
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		 return EnumSet.of(Characteristics.UNORDERED);
	}
	
	private Tasks transform(List<Integer> data){
		Tasks schedule = new Tasks();
		schedule.setWeights(data);
		return schedule;
	}
	
}
