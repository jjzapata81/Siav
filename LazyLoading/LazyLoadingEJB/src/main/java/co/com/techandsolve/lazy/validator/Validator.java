package co.com.techandsolve.lazy.validator;

import java.util.ArrayList;
import java.util.List;

import co.com.techandsolve.lazy.dto.Schedule;

public final class Validator {
	
	private Validator(){
		super();
	}

	public static void validate(Schedule schedule) {
		getValidator().stream().forEach(item -> item.execute(schedule));
	}
	
	private static List<IValidator> getValidator() {
		List<IValidator> validator = new ArrayList<IValidator>();
		validator.add(new DaysValidator());
		validator.add(new MaxLoadValidator());
		validator.add(new MaxWeightValidator());
		return validator;		
	}

}
