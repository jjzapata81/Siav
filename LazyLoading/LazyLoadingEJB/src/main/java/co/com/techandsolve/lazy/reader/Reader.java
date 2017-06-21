package co.com.techandsolve.lazy.reader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class Reader {
	
	public List<Integer> read(File file){
		try {
			return Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).stream().map(Integer::parseInt).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void extractor(List<Integer> lines){
		int days = lines.stream().findFirst().orElse(0);
	}

}
