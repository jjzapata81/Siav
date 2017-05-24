package co.com.siav.utils;

import java.io.File;
import java.io.FilenameFilter;

public class Filtro implements FilenameFilter{
	
	private String nombre;
	
	public Filtro(String nombre){
		this.nombre = nombre.substring(0, nombre.length()-4);
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.startsWith(nombre);
	}

}
