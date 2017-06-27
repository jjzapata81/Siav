package co.com.techandsolve.lazy.reader;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.techandsolve.lazy.bean.CalculatorBean;
import co.com.techandsolve.lazy.exception.BusinessException;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorBeanTest {
	
	@InjectMocks
	private CalculatorBean bean;
	
	private String INPUT_FILE = "lazy_loading_example_input.txt";
	private String FILE_DAYS_OUT_OF_RANGE = "input_day_out_of_range.txt";
	private String FILE_LOAD_OUT_OF_RANGE = "input_load_out_of_range.txt";
	private String FILE_WEIGHT_OUT_OF_RANGE = "input_weight_out_of_range.txt";
	
	@Test
	public void debeHacerAlgo(){
		File inputFile = new File(getClass().getClassLoader().getResource(INPUT_FILE).getPath());
		byte[] extractor = bean.getFile(inputFile);
		Assert.assertNotNull(extractor);
	}
	
	@Test(expected=BusinessException.class)
	public void debeValidarRangoDias(){
		File inputFile = new File(getClass().getClassLoader().getResource(FILE_DAYS_OUT_OF_RANGE).getPath());
		bean.getFile(inputFile);
	}
	
	@Test(expected=BusinessException.class)
	public void debeValidarRangoCargas(){
		File inputFile = new File(getClass().getClassLoader().getResource(FILE_LOAD_OUT_OF_RANGE).getPath());
		bean.getFile(inputFile);
	}
	
	@Test(expected=BusinessException.class)
	public void debeValidarRangoPeso(){
		File inputFile = new File(getClass().getClassLoader().getResource(FILE_WEIGHT_OUT_OF_RANGE).getPath());
		bean.getFile(inputFile);
	}

}
