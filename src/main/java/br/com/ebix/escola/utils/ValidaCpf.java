package br.com.ebix.escola.utils;

public class ValidaCpf {
	private static String cpf;

	public static boolean cpfEInvalido(String cpfrecebido) {
		if (cpfrecebido == null) {
			return true;
		} else {
			String cpfGerado = "";
			cpf = cpfrecebido;

			removerCaracteres();
			if (verificarSeTamanhoInvalido(cpf))
				return true;

			if (verificarSeDigIguais(cpf))
				return true;

			cpfGerado = cpf.substring(0, 9);
			cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
			cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));

			if (!cpfGerado.equals(cpf))
				return true;
		}
		return false;
	}

	private static void removerCaracteres() {
		cpf = cpf.replace("-", "");
		cpf = cpf.replace(".", "");
	}

	private static boolean verificarSeTamanhoInvalido(String cpf) {
		if (cpf.length() != 11)
			return true;
		return false;
	}

	private static boolean verificarSeDigIguais(String cpf) {
		// char primDig = cpf.charAt(0); caso queira verificar qualquer sequencia.
		char primDig = '0';
		char[] charCpf = cpf.toCharArray();
		for (char c : charCpf)
			if (c != primDig)
				return false;
		return true;
	}

	private static String calculoComCpf(String cpf) {
		int digGerado = 0;
		int mult = cpf.length() + 1;
		char[] charCpf = cpf.toCharArray();
		for (int i = 0; i < cpf.length(); i++)
			digGerado += (charCpf[i] - 48) * mult--;
		if (digGerado % 11 < 2)
			digGerado = 0;
		else
			digGerado = 11 - digGerado % 11;
		return String.valueOf(digGerado);
	}
}
