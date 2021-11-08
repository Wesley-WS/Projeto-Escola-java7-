package br.com.ebix.escola.model;

public class AlunoMateria {
	private Long cod_aluno;
	private Long cod_materia;
	private String nome;
	private String sigla;

	public Long getCod_aluno() {
		return cod_aluno;
	}

	public void setCod_aluno(Long cod_aluno) {
		this.cod_aluno = cod_aluno;
	}

	public Long getCod_materia() {
		return cod_materia;
	}

	public void setCod_materia(Long cod_materia) {
		this.cod_materia = cod_materia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
