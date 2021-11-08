package br.com.ebix.escola.dao;

import java.util.List;

import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.AlunoMateria;
import br.com.ebix.escola.model.Materia;

public interface AlunoMateriaDao {
//	List<AlunoMateria> selecionaMateriasAluno(long codigo);
	
	void associar(Aluno aluno, Materia materia);
	void desassociar(Aluno aluno);
	
//	List<AlunoMateria> getAllMaterias();
	List<AlunoMateria> getAll();
	List<Long> getAllCodMatByCod(Aluno aluno);

	
}
