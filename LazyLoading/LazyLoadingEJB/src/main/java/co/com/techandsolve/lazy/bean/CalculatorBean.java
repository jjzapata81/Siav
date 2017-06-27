package co.com.techandsolve.lazy.bean;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import co.com.techandsolve.lazy.dto.Schedule;
import co.com.techandsolve.lazy.util.Reader;
import co.com.techandsolve.lazy.validator.Validator;


public class CalculatorBean {
	
	
	private final static int WEIGHT = 50;
	
	public byte[] getFile(File inputFile) {
		Schedule schedule = Reader.getSchedule(inputFile);
		Validator.validate(schedule);
		List<Integer> list = schedule.getTasks().stream().map(item -> Distributor.run(item, WEIGHT)).collect(Collectors.toList());
		return Reader.getOutputFile(list);
	}

	

}
