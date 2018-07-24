package co.com.siav.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtil {
	
	private static final String SIN_FECHA = "SIN_FECHA";

	private FileUtil() {
		super();
	}
	
	private static String moverArchivo(String rutaArchivo, String rutaDestino, String nombreArchivo) {
		File origen = new File(rutaArchivo);
		File destino = new File(rutaDestino);
		int items= 0;
		if (destino.exists()) {
			items = destino.list(new Filtro(nombreArchivo)).length;
		}else{
			destino.mkdir();
		}
		destino = new File(crearPathDestino(destino.getAbsolutePath(), nombreArchivo, items));
		try {
			Files.copy(Paths.get(origen.getAbsolutePath()), Paths.get(destino.getAbsolutePath()));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return destino.getAbsolutePath();

	}
	
	private static String crearPathDestino(String path, String nombre, int numeroCopia) {
		if (numeroCopia == 0) {
			return path + "/" + nombre;
		}
		return path + "/" + nombre.substring(0, nombre.length() - 4) + "(" + numeroCopia + ").csv";
	}

	private static String getDirectorio(Long numeroCiclo) {
		return null == numeroCiclo ? SIN_FECHA : String.valueOf(numeroCiclo);
	}

	public static String moverArchivo(String absolutePath, String rutaArchivo, Long numeroCiclo, String nombreArchivo) {
		return moverArchivo(absolutePath, rutaArchivo + getDirectorio(numeroCiclo) + "\\", nombreArchivo);
	}

}
