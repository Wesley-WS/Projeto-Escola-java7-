package br.com.ebix.escola.model;

public class Materia {
	private Long cod_materia;
	private Long cod_professor;
	private String nome;
	private String sigla;
	
	public Materia() {
		
	}

	public Materia(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public Long getCod_professor() {
		return cod_professor;
	}

	public void setCod_professor(Long cod_professor) {
		this.cod_professor = cod_professor;
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
