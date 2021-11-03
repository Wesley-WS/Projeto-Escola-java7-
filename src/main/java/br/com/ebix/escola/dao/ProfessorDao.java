package br.com.ebix.escola.dao;

import java.util.List;

import br.com.ebix.escola.model.Professor;

public interface ProfessorDao {
	List<Professor> getAll();
	Professor get(Professor professor);
	void add(Professor professor);
	void update(Professor professor);
	void delete(Professor professor);
	boolean cpfCadastrado(Professor professor);
}
