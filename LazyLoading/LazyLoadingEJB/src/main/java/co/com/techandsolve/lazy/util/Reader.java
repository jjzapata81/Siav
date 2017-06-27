package co.com.techandsolve.lazy.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import co.com.techandsolve.lazy.collector.DaysCollector;
import co.com.techandsolve.lazy.dto.Schedule;
import co.com.techandsolve.lazy.dto.Tasks;
import co.com.techandsolve.lazy.exception.BusinessException;

public final class Reader {
	
	private Reader(){
		super();
	}
	
	public static Schedule getSchedule(File inputFile) {
		List<Integer> lines = read(inputFile);
		int days = lines.stream().findFirst().orElseThrow(()-> new BusinessException(LazyConstants.ERR_SIN_CONTENIDO));
		List<Tasks> tasks = lines.stream().skip(1).collect(new DaysCollector());
		return new Schedule(days, tasks);
	}

	public static byte[] getOutputFile(List<Integer> list) {
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
	
	private static List<Integer> read(File file){
		try {
			return Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).stream().map(Integer::parseInt).collect(Collectors.toList());
		} catch (IOException e) {
			throw new BusinessException(LazyConstants.ERR_LECTURA);
		}
	}

}
