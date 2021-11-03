package br.com.ebix.escola.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaTelefoneUtil {
	public static boolean eUmNumeroDeCelularInvalido(String numero) {
		boolean numeroInvalido = false;
		if (numero != null && numero.length() > 0) {
			String expression = "^\\([0-9]{2}\\)[0-9]{5}-[0-9]{4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(numero);
            if (!matcher.matches()) {
            	numeroInvalido = true;
            }
		}
		return numeroInvalido;
	}
	
	public static boolean eUmNumeroResidencialInvalido(String numero) {
		boolean numeroInvalido = false;
		if (numero != null && numero.length() > 0) {
			String expression = "^\\([0-9]{2}\\)[0-9]{4}-[0-9]{4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(numero);
            if (!matcher.matches()) {
            	numeroInvalido = true;
            }
		}
		return numeroInvalido;
	}
}
