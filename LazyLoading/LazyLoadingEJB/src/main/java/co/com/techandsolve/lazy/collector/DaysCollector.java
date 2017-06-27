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
		return (creator, item)-> creator.add(item);
	}

	@Override
	public Function<Creator, List<Tasks>> finisher() {
		return (data)->{
			return data.getElements().stream().map(item -> new Tasks(item)).collect(Collectors.toList());
		};
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		 return EnumSet.of(Characteristics.UNORDERED);
	}
	
	@Override
	public BinaryOperator<Creator> combiner() {
		return null;
	}
	
}
