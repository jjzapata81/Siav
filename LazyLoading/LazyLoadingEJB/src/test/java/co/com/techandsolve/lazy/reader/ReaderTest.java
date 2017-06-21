package co.com.techandsolve.lazy.reader;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ReaderTest {
	
	@Test
	public void debeHacerAlgo(){
		Reader reader = new Reader();
//		List<Integer> lines = Arrays.asList(2, 4, 5, 6, 8, 9, 2, 6, 7);
//		reader.extractor(lines);
		List<Integer> lines = Arrays.asList(5, 4, 30, 30, 1, 1, 3, 20, 20, 20, 11, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 6, 9, 19, 29, 39, 49, 59, 10, 32, 56, 76, 8, 44, 60, 47, 85, 71, 91);
		reader.extractor(lines);
	}

}
