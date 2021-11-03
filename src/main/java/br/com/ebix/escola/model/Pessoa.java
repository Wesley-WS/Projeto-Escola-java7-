package br.com.ebix.escola.model;

import java.util.Calendar;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private String email;
	private String telefoneCelular;
	private String telefoneResidencial;
	private Calendar dataNascimento;

	public int obterIdade() {
		Calendar agora = Calendar.getInstance();
		
		int idade = agora.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
		if (dataNascimento.get(Calendar.MONTH) > agora.get(Calendar.MONTH)
				|| (dataNascimento.get(Calendar.MONTH) == agora.get(Calendar.MONTH)
						&& dataNascimento.get(Calendar.DATE) > agora.get(Calendar.DATE))) {
			idade--;
		}
		return idade;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
