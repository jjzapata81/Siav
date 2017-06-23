package co.com.techandsolve.lazy.reader;

import co.com.techandsolve.lazy.exception.BusinessException;
import co.com.techandsolve.lazy.util.LazyConstants;

public final class Validator {
	private Validator(){
		super();
	}

	public static void days(int tasks, int days) {
		if(tasks != days)
			throw new BusinessException(String.format(LazyConstants.ERR_TAMANIO_DIAS, days, tasks));
		
	}

}
