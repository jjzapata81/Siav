package co.com.techandsolve.lazy.validator;

import co.com.techandsolve.lazy.exception.BusinessException;
import co.com.techandsolve.lazy.util.LazyConstants;

public abstract class RangeValidatorBase{
	
	protected abstract int getMinValue();
	
	protected abstract int getMaxValue();
	
	protected abstract String getType();

	protected void validateRange(int value){
		if(value < getMinValue() || value > getMaxValue()){
			throw new BusinessException(String.format(LazyConstants.ERR_RANGO, getType(), value, getMinValue(), getMaxValue()));
		}
	}
}
