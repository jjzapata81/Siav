package co.com.techandsolve.lazy.reader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	
	public byte[] extractor(List<Integer> lines){
		int days = lines.stream().findFirst().orElseThrow(()-> new BusinessException(LazyConstants.ERR_SIN_CONTENIDO));
		List<Tasks> tasks = lines.stream().skip(1).collect(new DaysCollector());
		Validator.days(tasks.size(), days);
		Distributor distributor = new Distributor();
		List<String> list = tasks.stream().map(item -> distributor.run(item)).collect(Collectors.toList());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(list);
		    return bos.toByteArray();
		} catch (IOException e) {
			throw new BusinessException(LazyConstants.ERR_ESCRITURA_ARCHIVO);
		}
	    
	}

}
