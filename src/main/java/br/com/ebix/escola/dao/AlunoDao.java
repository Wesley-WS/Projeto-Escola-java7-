package br.com.ebix.escola.dao;

import java.util.List;

import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.Materia;

public interface AlunoDao {
	List<Aluno> getAll();
	List<Long> getAllCodMatByCod(Aluno aluno);
	Aluno get(Aluno aluno);
	void add(Aluno aluno);
	void update(Aluno aluno);
	void delete(Aluno aluno);
	boolean cpfCadastrado(Aluno aluno);
}
