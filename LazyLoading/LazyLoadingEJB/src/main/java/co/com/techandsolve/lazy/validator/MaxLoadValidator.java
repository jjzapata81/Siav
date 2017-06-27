package co.com.techandsolve.lazy.validator;

import co.com.techandsolve.lazy.dto.Schedule;

public class MaxLoadValidator extends RangeValidatorBase implements IValidator {
	

	@Override
	public void execute(Schedule schedule) {
		schedule.getTasks().forEach(item -> validateRange(item.getWeights().size()));
	}

	@Override
	protected int getMinValue() {
		return 1;
	}

	@Override
	protected int getMaxValue() {
		return 100;
	}

	@Override
	protected String getType() {
		return "cargas";
	}

}
