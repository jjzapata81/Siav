package co.com.techandsolve.lazy.reader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import co.com.techandsolve.lazy.bean.Distributor;
import co.com.techandsolve.lazy.collector.DaysCollector;
import co.com.techandsolve.lazy.dto.Tasks;
import co.com.techandsolve.lazy.exception.BusinessException;
import co.com.techandsolve.lazy.util.LazyConstants;


public class Reader {
	
	public List<Integer> read(File file){
		try {
			return Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).stream().map(Integer::parseInt).collect(Collectors.toList());
		} catch (IOException e) {
			throw new BusinessException(LazyConstants.ERR_LECTURA);
		}
	}
	
	public void extractor(List<Integer> lines){
		int days = lines.stream().findFirst().orElseThrow(()-> new BusinessException(LazyConstants.ERR_SIN_CONTENIDO));
		System.out.println("Dias: " + days);
		List<Tasks> tasks = lines.stream().skip(1).collect(new DaysCollector());
		tasks.stream().forEach(item -> escribir2(item));
		Distributor distributor = new Distributor();
		tasks.stream().forEach(item -> distributor.run(item));
	}

	private void escribir2(Tasks schedule) {
		System.out.println("--------------------------------------------------");
		schedule.getWeights().stream().forEach(System.out::println);
	}



}
