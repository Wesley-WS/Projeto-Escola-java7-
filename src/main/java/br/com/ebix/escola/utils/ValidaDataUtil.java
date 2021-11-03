package br.com.ebix.escola.utils;

import java.util.Calendar;

public class ValidaDataUtil {
	public static boolean eUmaDataInvalida(Calendar nascimento) {
		Calendar agora = Calendar.getInstance();

		int idade = agora.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
		/*if (nascimento.get(Calendar.MONTH) > agora.get(Calendar.MONTH)
				|| (nascimento.get(Calendar.MONTH) == agora.get(Calendar.MONTH)
						&& nascimento.get(Calendar.DATE) > agora.get(Calendar.DATE))) {
			age--;
		}*/
		return (idade <= 0);
	}
	
}
