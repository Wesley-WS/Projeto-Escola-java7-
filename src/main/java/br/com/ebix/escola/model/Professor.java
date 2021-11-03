package br.com.ebix.escola.model;

public class Professor extends Pessoa {
	private Long cod_professor;

	public Long getCod_professor() {
		return cod_professor;
	}

	public void setCod_professor(Long cod_professor) {
		this.cod_professor = cod_professor;
	}

	@Override
	public String toString() {
		return "Professor [cod_professor=" + cod_professor + ", getNome()=" + getNome() + ", getCpf()=" + getCpf()
				+ ", getEmail()=" + getEmail() + ", getTelefoneCelular()=" + getTelefoneCelular()
				+ ", getTelefoneResidencial()=" + getTelefoneResidencial() + ", getDataNascimento()="
				+ getDataNascimento() + "]";
	}
	
}
