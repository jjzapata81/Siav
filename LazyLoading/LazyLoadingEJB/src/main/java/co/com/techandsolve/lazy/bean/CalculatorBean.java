package co.com.techandsolve.lazy.bean;

import java.io.File;

import javax.inject.Inject;

import co.com.techandsolve.lazy.reader.Reader;

public class CalculatorBean {
	
	@Inject
	private Reader reader;
	
	public void execute(){
		File file = null;
		reader.read(file);
	}

}
