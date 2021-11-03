package br.com.ebix.escola.dao;

import java.util.List;

import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.model.Professor;

public interface ProfessorMateriaDao {
	List<Materia> getAllMateriasFromProfessor(Professor professor);
	Materia getMateriaByCodProfessor(Professor professor);
}
