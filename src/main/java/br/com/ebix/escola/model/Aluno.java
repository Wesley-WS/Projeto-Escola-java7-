package br.com.ebix.escola.model;

public class Aluno extends Pessoa {
	private Long cod_aluno;

	public Long getCod_aluno() {
		return cod_aluno;
	}

	public void setCod_aluno(Long cod_aluno) {
		this.cod_aluno = cod_aluno;
	}

	@Override
	public String toString() {
		return "Aluno [cod_aluno=" + cod_aluno + ", getNome()=" + getNome() + ", getCpf()=" + getCpf() + ", getEmail()="
				+ getEmail() + ", getTelefoneCelular()=" + getTelefoneCelular() + ", getTelefoneResidencial()="
				+ getTelefoneResidencial() + ", getDataNascimento()=" + getDataNascimento() + "]";
	}
	
	
}
