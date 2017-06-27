package co.com.techandsolve.lazy.validator;

import co.com.techandsolve.lazy.dto.Schedule;
import co.com.techandsolve.lazy.exception.BusinessException;
import co.com.techandsolve.lazy.util.LazyConstants;

public class DaysValidator extends RangeValidatorBase implements IValidator{

	@Override
	public void execute(Schedule schedule) {
		if(schedule.getDays() != schedule.getTasks().size())
			throw new BusinessException(String.format(LazyConstants.ERR_TAMANIO_DIAS, schedule.getDays(), schedule.getTasks().size()));
		validateRange(schedule.getTasks().size());
	}
	
	@Override
	protected int getMinValue() {
		return 1;
	}

	@Override
	protected int getMaxValue() {
		return 500;
	}

	@Override
	protected String getType() {
		return "días";
	}

}
