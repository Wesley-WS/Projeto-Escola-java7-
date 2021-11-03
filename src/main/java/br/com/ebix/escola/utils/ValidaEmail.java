package br.com.ebix.escola.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEmail {
	public static boolean eUmEmailInvalido(String email) {
        boolean emailInvalido = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
            	emailInvalido = true;
            }
        }
        return emailInvalido;
    }
}
