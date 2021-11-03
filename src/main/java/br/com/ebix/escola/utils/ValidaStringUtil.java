package br.com.ebix.escola.utils;

public class ValidaStringUtil {
	public static boolean eNuloVazioOuHaApenasEspaco(Object object) {
		if(object == null) {
			return true;
		} else if(object.toString().trim().length() <= 0 || object.toString().isEmpty() || object.toString().trim().isEmpty()) {
			return true;
		} else {
			return false;
		}		
	}
}
