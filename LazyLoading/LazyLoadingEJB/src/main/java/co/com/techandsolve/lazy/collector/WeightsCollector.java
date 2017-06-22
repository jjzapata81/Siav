package co.com.techandsolve.lazy.collector;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import co.com.techandsolve.lazy.dto.Tasks;

public class WeightsCollector implements Collector<Integer, WeightCreator, List<Tasks>>{
	
	@Override
	public Supplier<WeightCreator> supplier() {
		return () -> new WeightCreator();
	}

	@Override
	public BiConsumer<WeightCreator, Integer> accumulator() {
		return null;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return EnumSet.of(Characteristics.UNORDERED);
	}

	@Override
	public BinaryOperator<WeightCreator> combiner() {
		return null;
	}

	@Override
	public Function<WeightCreator, List<Tasks>> finisher() {
		return null;
	}

	

}
