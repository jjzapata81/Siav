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

import co.com.techandsolve.lazy.dto.Carga;
import co.com.techandsolve.lazy.exception.BusinessException;

public class DaysCollector implements Collector<Integer, Creator, List<List<Carga>>>{

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
	public Function<Creator, List<List<Carga>>> finisher() {
		return (data)->{
			return data.getElements().stream().map(this::transform).collect(Collectors.toList());
		};
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		 return EnumSet.of(Characteristics.UNORDERED);
	}
	
	private List<Carga> transform(List<Integer> data){
		return data.stream().map(this::transformCarga).collect(Collectors.toList());
	}
	
	private Carga transformCarga(Integer data){
		Carga carga = new Carga();
		carga.setPeso(data);
		return carga;
	}

}
