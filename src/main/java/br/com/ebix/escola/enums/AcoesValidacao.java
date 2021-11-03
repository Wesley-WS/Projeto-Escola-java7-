package br.com.ebix.escola.enums;

public enum AcoesValidacao {
	CODIGOINVALIDO("codigo", "Codigo inv�lido."),
	NOMEEMBRANCO("nome", "Campo nome em branco."),
	NOMEMINIMOCHAREXCEDIDO("nome", "O nome precisa ter no minimo 100 characteres."),
	CPFEMBRANCO("cpf", "Campo CPF em branco."),
	CPFINVALIDO("cpf", "O CPF inserido � inv�lido."),
	CPFEXISTENTE("cpf", "O CPF inserido j� est� cadastrado."),
	EMAILEMBRANCO("email", "Campo email em branco."),
	EMAILINVALIDO("email", "O email inserido � inv�lido."),
	TELEFONECELULARINVALIDO("telefoneCelular", "O telefone celular inserido � inv�lido."),
	TELEFONERESIDENCIALINVALIDO("telefoneResidencial", "O telefone residencial inserido � inv�lido"),
	DATANASCIMENTOEMBRANCO("dataNascimento", "Campo data nascimento em branco."),
	DATANASCIMENTOINVALIDA("dataNascimento", "A data de nascimento � inv�lida."),
	DATANASCIMENTOINVALIDAPROFESSOR("dataNascimento", "Idade minima permitida � de 22 anos."),
	DATANASCIMENTOINVALIDAALUNO("dataNascimento", "Idade minima permitida � de 4 anos."),
	SIGLAEMBRANCO("sigla", "Campo sigla em branco."),
	SIGLAMINIMOCHAREXCEDIDO("sigla", "A sigla precisa ter no minimo de 5 caracteres.");
	
	private String campo;
	private String mensagem;
	
	AcoesValidacao(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
}
