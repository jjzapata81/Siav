package co.com.siav.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utilidades {

	private Utilidades(){	}
	
	public static boolean emailValido(String email) {
		if(email == null || email.trim().isEmpty())
			return false;
		Pattern pattern = Pattern.compile(Constantes.PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();	
	}

}
