package co.com.techandsolve.lazy.reader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import co.com.techandsolve.lazy.collector.DaysCollector;
import co.com.techandsolve.lazy.dto.Carga;
import co.com.techandsolve.lazy.exception.BusinessException;
import co.com.techandsolve.lazy.util.LazyConstants;


public class Reader {
	
	public List<Integer> read(File file){
		try {
			return Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).stream().map(Integer::parseInt).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void extractor(List<Integer> lines){
		int days = lines.stream().findFirst().orElseThrow(()-> new BusinessException(LazyConstants.ERR_SIN_CONTENIDO));
		System.out.println("Dias: " + days);
		List<List<Carga>> collect = lines.stream().skip(1).collect(new DaysCollector());
		collect.stream().forEach(item -> escribir2(item));
//		lines.stream().skip(1).
//		List<Integer> lista1 = lines.subList(2, 2 + lines.get(1));
//		List<Integer> lista2 = lines.subList(2 + lista1.size(), lines.size());
//		System.out.println("Lista 1");
//		escribir(lista1);
//		System.out.println("Lista 2");
//		escribir(lista2);
	}

	private void escribir2(List<Carga> lista) {
		System.out.println("--------------------------------------------------");
		lista.stream().forEach(item -> System.out.println("Prueba: " + item.getPeso()));
	}

	private void escribir(List<Integer> lista) {
		lista.stream().forEach(item -> System.out.println("Item: " + item));
		
	}

}
